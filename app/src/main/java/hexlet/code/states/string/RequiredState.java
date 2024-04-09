package hexlet.code.states.string;

import hexlet.code.states.State;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class RequiredState implements State {

    @Override
    public boolean isValid(Object obj) {
        String str = (String) obj;
        return str != null && !str.isEmpty();
    }
}
