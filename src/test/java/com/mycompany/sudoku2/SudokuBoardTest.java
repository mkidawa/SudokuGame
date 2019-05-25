/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sudoku2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static java.lang.Character.UNASSIGNED;

/**
 * @author aleks
 */
public class SudokuBoardTest {

    public SudokuBoardTest() {
    }

    @Test
    public void testGetField() {
        SudokuBoard board = new SudokuBoard();
        assertEquals(UNASSIGNED, board.get(0, 0));
    }

    @Test
    public void testSetField() {
        SudokuBoard board = new SudokuBoard();
        board.set(0, 0, 8);
        assertEquals(8, board.get(0, 0));
    }

    @Test
    public void testDifferentBoards() {
        System.out.println("Different Boards");
        SudokuBoard instance = new SudokuBoard();
        SudokuBoard instance2 = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(instance);
        solver.solve(instance2);
        assertFalse(instance.equals(instance2));
    }

}
    
