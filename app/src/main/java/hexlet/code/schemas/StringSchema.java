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
        if (required) {
            if (s == null || s.isEmpty()) {
                return false;
            }
        }
        if (s == null) {
            if (minLength != 0) {
                return false;
            }
        }
        if (s != null && s.length() < minLength) {
            return false;
        }
        if (s == null && !contains.isEmpty()) {
            return false;
        }
        if (s != null) {
            return s.contains(contains);
        }
        return true;
    }
}
