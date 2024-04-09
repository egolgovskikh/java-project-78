package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {

        states.put("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        states.put("positive", x -> x == null || x > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        states.put("range", x -> x == null || (x >= min && x <= max));
        return this;
    }
}
