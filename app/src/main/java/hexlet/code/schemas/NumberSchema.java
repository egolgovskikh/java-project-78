package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {

        states.add(Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        states.add(x -> {
            if (x == null) {
                return false;
            }
            return x > 0;
        });
        return this;
    }

    public NumberSchema range(int minimum, int maximum) {
        states.add(x -> {
            if (x == null) {
                return false;
            }
            return x > minimum && x < maximum;
        });
        return this;
    }
}
