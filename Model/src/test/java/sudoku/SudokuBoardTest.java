/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import org.junit.Test;

import static java.lang.Character.UNASSIGNED;
import static org.junit.Assert.*;

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
        assertFalse(instance.getColumn(0).equals(instance2.getColumn(0)));
        assertFalse(instance.getRow(0).equals(instance2.getRow(0)));
        assertFalse(instance.getBox(3, 3).equals(instance2.getBox(3, 3)));
        System.out.print(instance.toString());
//        System.out.print(instance.getRow(0).toString());
//        System.out.print(instance.getColumn(0).toString());
//        System.out.print(instance.getBox(0, 0).toString());
    }
}
    
