package hexlet.code.states.integer;

import hexlet.code.states.State;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class RequiredState implements State {

    @Override
    public boolean isValid(Object obj) {
        Integer i = (Integer) obj;
        return i != null;
    }
}
