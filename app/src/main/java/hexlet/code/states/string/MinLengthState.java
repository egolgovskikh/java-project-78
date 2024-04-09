package hexlet.code.states.string;

import hexlet.code.states.State;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class MinLengthState implements State {

    private final int minLength;

    @Override
    public boolean isValid(Object obj) {
        String str = (String) obj;
        if (str == null) {
            return false;
        }
        return str.length() >= minLength;
    }
}
