/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class SudokuBoard - access to sudoku board.
 */
public class SudokuBoard implements Serializable, Cloneable {
    /**
     * List with 81 sudoku fields.
     */
    private List<SudokuField> board = Arrays.asList(new SudokuField[81]);
    private static final int BOARD_SIZE = 81;
    private static final int SIZE = 9;

    public SudokuBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board.set(i, new SudokuField());
        }
    }

    /**
     * Method that gets the integer value of field in sudoku board list.
     *
     * @param x coordinate
     * @param y coordinate
     * @return int value of field
     */
    public final int get(final int x, final int y) {

        return board.get(x * SIZE + y).getFieldValue();
    }

    /**
     * Method that sets the integer value of field in sudoku board list.
     *
     * @param x     coordinate
     * @param y     coordinate
     * @param value that will be set
     */
    public final void set(final int x, final int y, final int value) {

        board.get(x * SIZE + y).setFieldValue(value);
    }

    /**
     * Method that gives the result of getFlagCheck method.
     *
     * @return the flag
     */
    public final boolean getFlagCheck() {
        return checkBoard();
    }

    /**
     * Method that checks if values are properly placed in fields.
     *
     * @return if value are properly placed
     */
    public boolean checkBoard() {
        boolean flag1 = true;
        boolean flag2 = true;
        for (int i = 0; i < SIZE; i++) {
            flag1 = flag1 && (getRow(i).verify() && getColumn(i).verify());
        }

        for (int i = 0; i < SIZE; i += 3) {
            for (int j = 0; j < SIZE; j += 3) {
                flag2 = flag2 && getBox(i, j).verify();
            }
        }
        return flag1 && flag2;
    }

    /**
     * Method that gets one whole row of sudoku fields.
     *
     * @param x coordinate
     * @return row
     */
    public final SudokuRow getRow(final int x) {
        SudokuRow row = new SudokuRow();
        final List<SudokuField> values = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < SIZE; i++) {
            values.set(i, board.get(x * SIZE + i));
        }
        row.setContainer(values);
        return row;
    }

    /**
     * Method that gets one whole column of sudoku fields.
     *
     * @param y coordinate
     * @return column
     */
    public final SudokuColumn getColumn(final int y) {
        SudokuColumn column = new SudokuColumn();
        final List<SudokuField> values = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < SIZE; i++) {
            values.set(i, board.get(y + i * SIZE));
        }
        column.setContainer(values);
        return column;
    }

    /**
     * Method that gets one whole box of sudoku fields.
     *
     * @param x coordinate
     * @param y coordinate
     * @return box
     */
    //x i y będą mieć wartości 0,3 lub 6 - pierwsze komórki każdego boxa
    public final SudokuBox getBox(final int x, final int y) {
        SudokuBox box = new SudokuBox();
        final List<SudokuField> values = Arrays.asList(new SudokuField[9]);
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++, k++) {
                values.set(k, board.get((x + i) * SIZE + y + j));
            }
        }
        box.setContainer(values);
        return box;
    }

    public String displayBoard() {
        String sudoku = new String();
        for (int i = 0; i < board.size(); i++) {
            sudoku += board.get(i).getFieldValue() + " ";
            if ((i + 1) % 9 == 0) {
                sudoku += "\n";
                if ((i + 1) % 27 == 0 && (i + 1) % 81 != 0) {
                    sudoku += "_______________________\n\n";
                }
            }
            if ((i + 1) % 3 == 0 && (i + 1) % 9 != 0) {
                sudoku += " | ";
            }
        }
        return sudoku;
    }

    public static int getSize(){
        return SIZE;
    }

    public String boardToConcatString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                stringBuilder.append(get(i , j));
            }
        }
        return stringBuilder.toString();
    }
    
    @Override
    public String toString() {
        ToStringBuilder controllerString = new ToStringBuilder(this);
        for (int i = 0; i < BOARD_SIZE; i++) {
            controllerString.append(board.get(i).getFieldValue());
        }
        return controllerString.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        return new EqualsBuilder().append(board, ((SudokuBoard) obj).board).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(board).toHashCode();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
