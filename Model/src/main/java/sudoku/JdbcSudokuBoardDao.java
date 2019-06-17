package sudoku;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>,AutoCloseable{
    final static Logger logger = Logger.getLogger(JdbcSudokuBoardDao.class);
    private static final String URL = "jdbc:derby:Sudoku;create=true";
    private static String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private Statement JDBC_STATEMENT;
    private String fileName;
    private Connection connection;
    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.dao");

    public String getFileName() {
        return fileName;
    }
    public JdbcSudokuBoardDao(String fileName) throws JdbcDaoException {
        this.fileName=fileName;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL);
            logger.debug(bundle.getString("connectionPositive"));
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(bundle.getString("connectionError"));
            throw new JdbcDaoException(bundle.getString("connectionError"),e);
        }
    }

    public void write(SudokuBoard sudokuBoard){
        PreparedStatement preparedStatement;
        try {
            JDBC_STATEMENT =connection.createStatement();
            JDBC_STATEMENT.execute("CREATE TABLE SudokuBoards(name varchar(25), fields varchar(81))");
            logger.debug(bundle.getString("tabCreation"));
        } catch (SQLException e) {
            logger.warn(bundle.getString("tabError"));
        }
        try {
            JDBC_STATEMENT = connection.createStatement();
            preparedStatement = connection.prepareStatement("UPDATE SudokuBoards SET fields =? WHERE name=?");
            preparedStatement.setString(1,sudokuBoard.boardToConcatString());
            preparedStatement.setString(2,fileName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(bundle.getString("sqlError"), e);
        }
        try {
            JDBC_STATEMENT = connection.createStatement();
            preparedStatement = connection.prepareStatement("INSERT INTO SudokuBoards values(?, ?)");
            preparedStatement.setString(1,fileName);
            preparedStatement.setString(2,sudokuBoard.boardToConcatString());
            preparedStatement.executeUpdate();
            logger.debug(bundle.getString("dataInsertion"));
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(bundle.getString("sqlError"), e);
        }
    }
    public SudokuBoard read(){
        PreparedStatement preparedStatement;
        SudokuBoard sudokuBoard = new SudokuBoard();
        String fields;
        ResultSet resultSet;
        int[] tab = new int[81];
        try {
            JDBC_STATEMENT = connection.createStatement();
            logger.debug(bundle.getString("readData"));
            preparedStatement = connection.prepareStatement("SELECT SudokuBoards.name, SudokuBoards.fields from SudokuBoards where SudokuBoards.name=?");
            preparedStatement.setString(1, fileName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                fields = resultSet.getString(2);
            } else {
                logger.error(bundle.getString("inputOutput"));
                throw new IOException(bundle.getString("inputOutput"));
            }
            for (int i = 0; i < 81; i++) {
                tab[i] = (Character.getNumericValue(fields.charAt(i)));
            }
        } catch (SQLException |IOException e) {
            logger.error(bundle.getString("inputOutput"));
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.set(i, j, tab[i * 9 + j]);
            }
        }
        return sudokuBoard;
    }

    public void close(){
        try {
            JDBC_STATEMENT.close();
            connection.close();
        } catch (SQLException e) {
            logger.error(bundle.getString("sqlError"), e);
        }
    }

    @Override
    public void finalize() throws Throwable{
        super.finalize();
    }
}
