package hexlet.code.schemas;

import java.util.Objects;

public class StringSchema extends BaseSchema<StringSchema> {

    private int minLength = 0;
    private String contains = "";

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        contains = substring;
        return this;
    }

    public boolean isValid(String s) {
        return checkContains(s) && checkRequired(s) && checkMinLength(s);
    }

    private boolean checkRequired(String s) {
        if (required) {
            return s != null && !s.isEmpty();
        }
        return true;
    }

    private boolean checkMinLength(String s) {
        if (s == null) {
            if (minLength != 0) {
                return false;
            }
        }
        return s == null || s.length() >= minLength;
    }

    private boolean checkContains(String s) {
        if (s == null && !contains.isEmpty()) {
            return false;
        }
        if (s != null) {
            return s.contains(contains);
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
        StringSchema that = (StringSchema) object;
        return required == that.required && minLength == that.minLength && Objects.equals(contains, that.contains);
    }

    @Override
    public int hashCode() {
        return Objects.hash(required, minLength, contains);
    }
}
