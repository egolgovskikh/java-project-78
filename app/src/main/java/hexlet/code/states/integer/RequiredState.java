package hexlet.code.states.integer;

import hexlet.code.states.State;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class RequiredState implements State<Integer> {
    @Override
    public boolean isValid(Integer i) {
        return i != null;
    }
}
