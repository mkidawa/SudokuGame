package sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FieldPosition {

    private int fieldX;
    private int fieldY;
    public FieldPosition(int fieldX, int fieldY){
        this.fieldX = fieldX;
        this.fieldY = fieldY;
    }


    public int getFieldX() {
        return fieldX;
    }

    public int getFieldY() {
        return fieldY;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fieldX", fieldX)
                .append("fieldY", fieldY)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FieldPosition that = (FieldPosition) o;

        return new EqualsBuilder()
                .append(fieldX, that.fieldX)
                .append(fieldY, that.fieldY)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(fieldX)
                .append(fieldY)
                .toHashCode();
    }
}
