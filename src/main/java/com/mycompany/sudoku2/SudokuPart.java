package com.mycompany.sudoku2;

public class SudokuPart {
    private SudokuField[] container = new SudokuField[9];
    public SudokuPart() {
        for (int i = 0; i < 9; i++) {
            container[i] = new SudokuField();
        }
    }
    public SudokuField[] getContainer() {
        SudokuField[] newContainer = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            newContainer[i].setFieldValue(container[i].getFieldValue());
        }
        return newContainer;
    }

    public void setContainer(SudokuField[] fields) {
        for (int i = 0; i < 9; i++) {
            container[i].setFieldValue(fields[i].getFieldValue());
        }
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (container[j].getFieldValue() == container[i].getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}
