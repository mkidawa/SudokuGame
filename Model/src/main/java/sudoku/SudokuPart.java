package sudoku;

import java.util.Arrays;
import java.util.List;

public class SudokuPart {
    private List<SudokuField> container = Arrays.asList(new SudokuField[9]);
    protected static final int SIZE = 9;

    public SudokuPart() {
        for (int i = 0; i < SIZE; i++) {
            container.set(i, new SudokuField());
        }
    }

    public final List<SudokuField> getContainer() {
        List<SudokuField> newContainer = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < SIZE; i++) {
            newContainer.set(i, container.get(i));
        }
        return newContainer;
    }

    public final void setContainer(final List<SudokuField> fields) {
        for (int i = 0; i < SIZE; i++) {
            container.get(i).setFieldValue(fields.get(i).getFieldValue());
        }
    }

    public final boolean verify() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = i + 1; j < SIZE; j++) {
                if (container.get(i).getFieldValue()
                        == container.get(j).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }

}
