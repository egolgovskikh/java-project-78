package hexlet.code.states.string;

import hexlet.code.states.State;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class MinLengthState implements State<String> {

    private final int minLength;

    @Override
    public boolean isValid(String str) {
        if (str == null) {
            return false;
        }
        return str.length() >= minLength;
    }
}
