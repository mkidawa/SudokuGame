package sudoku;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void testFieldSet() throws UnknownLevelException {
        MenuWindowControl menu = new MenuWindowControl();
        menu.setLevel("Noob");
        GameWindowControl game = new GameWindowControl();
        game.solveBoard();
        game.setSudokuBoard(0,0,9);
        game.setSudokuBoard(0,1,9);
        game.setSudokuBoard(0,2,9);
        assertFalse(game.checkSudoku());
    }

    @Test(expected = WrongBoardException.class)
    public void testWrongBoard() throws WrongBoardException, UnknownLevelException {
        MenuWindowControl menu = new MenuWindowControl();
        menu.setLevel("New Level");
        GameWindowControl game = new GameWindowControl();
        game.solveBoard();
        game.setSudokuBoard(0,0,9);
        game.setSudokuBoard(0,1,9);
        game.setSudokuBoard(0,2,9);
        game.checkCompability();
    }
}

