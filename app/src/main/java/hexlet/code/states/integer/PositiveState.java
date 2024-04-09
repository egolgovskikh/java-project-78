package hexlet.code.states.integer;

import hexlet.code.states.State;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PositiveState implements State {

    @Override
    public boolean isValid(Object obj) {
        Integer i = (Integer) obj;
        if (i == null) {
            return false;
        }
        return i > 0;
    }
}
