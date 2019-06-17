package sudoku;

import org.apache.log4j.Logger;

import java.util.ResourceBundle;

public class SudokuBoardDaoFactory {
    final static Logger logger = Logger.getLogger(JdbcSudokuBoardDao.class);
    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.language");
    public Dao<SudokuBoard> getFileDao(String filename) {
        return new FileSudokuBoardDao(filename);
    }

    public JdbcSudokuBoardDao getJdbcDao(String filename){
        JdbcSudokuBoardDao jdbcSudokuBoardDao = null;
        try {
            jdbcSudokuBoardDao = new JdbcSudokuBoardDao(filename);
        } catch (JdbcDaoException e) {
            logger.error(bundle.getString("jdbcLoaded"), e);
        }
        logger.debug(bundle.getString("factoryError"));
        return jdbcSudokuBoardDao;
    }
}
