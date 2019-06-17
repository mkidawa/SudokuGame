package sudoku;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JdbcTest {
    @Test
    public void jdbcTest() throws JdbcDaoException{
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard();
        solver.solve(sudokuBoard);
        SudokuBoard sudokuBoardCopy = new SudokuBoard();
        JdbcSudokuBoardDao jdbcDao = new JdbcSudokuBoardDao("Sudoku");
        jdbcDao.write(sudokuBoard);
        sudokuBoardCopy = jdbcDao.read();
        assertEquals(sudokuBoard,sudokuBoardCopy);
    }
}
