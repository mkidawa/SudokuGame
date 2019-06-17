package sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class SudokuColumn extends SudokuPart implements Cloneable {
    private static final int SIZE = 9;

    public String displayColumn() {
        String column = new String();
        List<SudokuField> newColumn = getContainer();
        for (int i = 0; i < SIZE; i += 1) {
            column += newColumn.get(i).getFieldValue() + "\n";
        }
        return column;
    }

    @Override
    public String toString() {
        ToStringBuilder controllerString = new ToStringBuilder(this);
        for(int i=0;i<SIZE;i++)
        {
            controllerString.append(getContainer().get(i).getFieldValue());
        }
        return controllerString.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(getContainer(),((SudokuPart)obj).getContainer()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17,37).
                append(getContainer()).toHashCode();
    }
}
