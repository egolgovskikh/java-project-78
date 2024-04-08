package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<NumberSchema> {

    private boolean positive = false;
    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;

    public boolean isValid(Integer i) {
        return checkRequired(i) && checkPositive(i) && checkRange(i);
    }

    public NumberSchema positive() {
        positive = true;
        return this;
    }

    public NumberSchema range(int minimum, int maximum) {
        this.min = minimum;
        this.max = maximum;
        return this;
    }

    private boolean checkPositive(Integer i) {
        if (i == null && positive) {
            return false;
        }
        if (positive) {
            return i > 0;
        }
        return true;
    }

    private boolean checkRange(Integer i) {
        if (i == null) {
            return min == Integer.MIN_VALUE && max == Integer.MAX_VALUE;
        }
        return i > min && i < max;
    }

    private boolean checkRequired(Integer i) {
        if (required) {
            return i != null;
        }
        return true;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        NumberSchema that = (NumberSchema) object;
        return required == that.required && positive == that.positive && min == that.min && max == that.max;
    }

    @Override
    public int hashCode() {
        return Objects.hash(required, positive, min, max);
    }
}
