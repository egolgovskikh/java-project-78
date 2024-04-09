package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {

        states.add(Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        states.add(x -> x == null || x > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        states.add(x -> x == null || (x >= min && x <= max));
        return this;
    }
}
