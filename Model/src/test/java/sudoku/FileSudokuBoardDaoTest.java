package sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileSudokuBoardDaoTest {
    @Test
    public void writeReadTest(){
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuBoard sudokuBoard2;
        Dao<SudokuBoard> fileSudokuBoardDao;

        fileSudokuBoardDao = factory.getFileDao("Case1");
        fileSudokuBoardDao.write(sudokuBoard);
        sudokuBoard2 = fileSudokuBoardDao.read(); // odczytywana instancja SudokuBoarda z pliku o okreslonej nazwie
        assertEquals(sudokuBoard,sudokuBoard2);
    }

    @Test(expected = RuntimeException.class)
    public void readExceptionTest(){
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> fileSudokuBoardDao;
        fileSudokuBoardDao = factory.getFileDao("Case2");
        fileSudokuBoardDao.read();
    }

    @Test(expected = RuntimeException.class)
    public void writeExceptionTest(){
        Dao<SudokuBoard> fileSudokuBoardDao;
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SudokuBoard sudokuBoard = new SudokuBoard();
        fileSudokuBoardDao = factory.getFileDao("/?;:");
        fileSudokuBoardDao.write(sudokuBoard);
    }

}