package hexlet.code.schemas;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class StringSchema {
    private boolean required = false;
    private int minLength = 0;
    private String contains = "";

    public StringSchema required() {
        required = true;
        return this;
    }

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
}
