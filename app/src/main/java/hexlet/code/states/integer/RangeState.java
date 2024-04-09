package hexlet.code.states.integer;

import hexlet.code.states.State;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class RangeState implements State {

    private final int min;
    private final int max;

    @Override
    public boolean isValid(Object obj) {
        Integer i = (Integer) obj;
        if (i == null) {
            return false;
        }
        return i > min && i < max;
    }
}
