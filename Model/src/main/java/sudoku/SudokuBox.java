package sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class SudokuBox extends SudokuPart implements Cloneable{
    public String displayBox() {
        String box = new String();
        List<SudokuField> newBox = getContainer();
        for(int i = 0; i < SIZE; i++) {
            box += newBox.get(i).getFieldValue();
            if((i+1) % 3 == 0) {
                box += "\n";
            }
        }
        return box;
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
