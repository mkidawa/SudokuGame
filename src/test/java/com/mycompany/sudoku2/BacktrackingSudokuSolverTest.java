package com.mycompany.sudoku2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {

    @Test
    void testSolve() {
        SudokuBoard instance = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(instance);
        assertTrue(instance.getFlagCheck());
    }
}

