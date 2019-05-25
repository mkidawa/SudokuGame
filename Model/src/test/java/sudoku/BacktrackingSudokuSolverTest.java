package sudoku;

import org.junit.Test;
import static org.junit.Assert.*;

public class BacktrackingSudokuSolverTest {

    @Test
    public void testSolve() {
        SudokuBoard instance = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(instance);
        assertTrue(instance.getFlagCheck());
    }
}