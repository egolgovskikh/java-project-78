package hexlet.code.schemas;

import hexlet.code.states.string.ContainsState;
import hexlet.code.states.string.MinLengthState;
import hexlet.code.states.string.RequiredState;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        states.add(new RequiredState());
        return this;
    }

    public StringSchema minLength(int length) {
        states.removeIf(s -> s.getClass().equals(MinLengthState.class));
        states.add(new MinLengthState(length));
        return this;
    }

    public StringSchema contains(String substring) {
        states.removeIf(s -> s.getClass().equals(ContainsState.class));
        states.add(new ContainsState(substring));
        return this;
    }
}
