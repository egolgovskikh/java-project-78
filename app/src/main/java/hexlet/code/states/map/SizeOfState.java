package hexlet.code.states.map;

import hexlet.code.states.State;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Map;

@AllArgsConstructor
@EqualsAndHashCode
public class SizeOfState implements State {

    private final int sizeOf;

    @Override
    public boolean isValid(Object obj) {
        Map<Object, Object> map = (Map<Object, Object>) obj;
        if (map == null) {
            return false;
        }
        return map.size() == sizeOf;
    }
}
