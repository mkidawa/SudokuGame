package sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class SudokuField implements Serializable {
    private int value;

    public final int getFieldValue() {
        return value;
    }

    public final void setFieldValue(final int value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object obj) {
        return new EqualsBuilder().append(value, ((SudokuField) obj).value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("value", value).toString();
    }

}
