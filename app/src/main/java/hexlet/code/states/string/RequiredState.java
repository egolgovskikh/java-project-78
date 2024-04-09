package hexlet.code.states.string;

import hexlet.code.states.State;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class RequiredState implements State<String> {

    @Override
    public boolean isValid(String str) {
        return str != null && !str.isEmpty();
    }
}
