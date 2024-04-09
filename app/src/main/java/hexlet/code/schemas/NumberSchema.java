package hexlet.code.schemas;

import hexlet.code.states.integer.PositiveState;
import hexlet.code.states.integer.RangeState;
import hexlet.code.states.integer.RequiredState;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        states.add(new RequiredState());
        return this;
    }

    public NumberSchema positive() {
        states.add(new PositiveState());
        return this;
    }

    public NumberSchema range(int minimum, int maximum) {
        states.removeIf(s -> s.getClass().equals(RangeState.class));
        states.add(new RangeState(minimum, maximum));
        return this;
    }
}
