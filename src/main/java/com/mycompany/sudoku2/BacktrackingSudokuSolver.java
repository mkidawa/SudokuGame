package com.mycompany.sudoku2;

import java.util.Random;

import static java.lang.Character.UNASSIGNED;

public class BacktrackingSudokuSolver implements SudokuSolver {
    public boolean solve(SudokuBoard board) {
        int[] array = randomize();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.get(row, col) == UNASSIGNED) {
                    for (int i = 0; i < 9; i++) {
                        if (isCorrect(row, col, array[i], board)) {
                            board.set(row, col, array[i]);
                            if (solve(board)) return true;
                            else board.set(row, col, UNASSIGNED);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private int[] randomize() {
        Random rand = new Random();
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < array.length; i++) {
            int randomPosition = rand.nextInt(9);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }
        return array;
    }

    private boolean isCorrect(int row, int col, int number, SudokuBoard board) {
        boolean flagRow = false;
        boolean flagCol = false;
        boolean flagBox = false;
        for (int i = 0; i < 9; i++) {
            if (board.get(row, i) == number) flagRow = true;
            if (board.get(i, col) == number) flagCol = true;
        }
        int r = row - row % 3;
        int c = col - col % 3;
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (board.get(i, j) == number) flagBox = true;
            }
        }
        return !(flagRow || flagCol || flagBox);
    }

}
