/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sudoku2;

import static java.lang.Character.UNASSIGNED;

import java.util.Random;
import java.util.Arrays;

public class SudokuBoard {
    private SudokuField[] board = new SudokuField[81];

    public int get(int x, int y) {
        return board[x * 9 + y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        board[x * 9 + y].setFieldValue(value);
    }

    public boolean getFlagCheck() {
        return checkBoard();
    }

    private boolean checkBoard() {
        boolean flag1 = true;
        boolean flag2 = true;
        for (int i = 0; i < 9; i++) {
            flag1 = (getRow(i).verify() && getColumn(i).verify());
        }

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                flag2 = getBox(i, j).verify();
            }
        }
        return flag1 && flag2;
    }

    public SudokuBoard() {
        for (int i = 0; i < 81; i++) {
            board[i] = new SudokuField();
        }
    }

    public SudokuRow getRow(int x) {
        SudokuRow row = new SudokuRow();
        SudokuField[] values = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            values[i] = board[x * 9 + i];
        }
        row.setContainer(values);
        return row;
    }

    public SudokuColumn getColumn(int y) {
        SudokuColumn column = new SudokuColumn();
        SudokuField[] values = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            values[i] = board[y * 9 + i];
        }
        column.setContainer(values);
        return column;
    }

    //x i y będą mieć wartości 0,3 lub 6 - pierwsze komórki każdego boxa
    public SudokuBox getBox(int x, int y) {
        SudokuBox box = new SudokuBox();
        SudokuField[] values = new SudokuField[9];
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++, k++) {
                values[k] = board[(x + i) * 9 + y + j];
            }
        }
        box.setContainer(values);
        return box;
    }

    @Override
    public boolean equals(Object obj) {
        int sum = 0;
        SudokuBoard s = (SudokuBoard) obj;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (s.get(i, j) == this.get(i, j)) sum++;
            }
        }
        if (sum < 81) return false;
        return true;
    }
}
