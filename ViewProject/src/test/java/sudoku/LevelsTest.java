package sudoku;

import org.junit.Test;
import static org.junit.Assert.*;

public class LevelsTest {

    @Test(expected = UnknownLevelException.class)
    public void testUnknownLevel() throws WrongBoardException, UnknownLevelException {
        MenuWindowControl menu = new MenuWindowControl();
        menu.setLevel("New Level");
        GameWindowControl game = new GameWindowControl();
        game.solveBoard();
        game.checkCompability();
        game.eraseFields();
    }

    @Test
    public void testEraser() throws WrongBoardException, UnknownLevelException {
        MenuWindowControl menu = new MenuWindowControl();
        menu.setLevel("Noob");
        GameWindowControl game = new GameWindowControl();
        game.solveBoard();
        game.checkCompability();
        game.eraseFields();
        assertEquals(9,game.getEmptyField());
    }
}

