package sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuBoardDaoFactoryTest {
    @Test
    public void getFileDaoTest(){
    SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    assertNotNull(factory.getFileDao("abc"));
    }
}