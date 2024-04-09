package hexlet.code.states.integer;

import hexlet.code.states.State;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PositiveState implements State<Integer> {

    @Override
    public boolean isValid(Integer i) {
        if (i == null) {
            return false;
        }
        return i > 0;
    }
}
