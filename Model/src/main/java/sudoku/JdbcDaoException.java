package sudoku;

public class JdbcDaoException extends Exception {
    public JdbcDaoException(final String message,final Throwable throwable){
        super(message,throwable);
    }
}