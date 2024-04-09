package hexlet.code.states.string;

import hexlet.code.states.State;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class ContainsState implements State<String> {

    private final String substring;

    @Override
    public boolean isValid(String str) {
        if (str == null) {
            return false;
        }
        return str.contains(substring);
    }
}
