package hexlet.code.states.map;

import hexlet.code.states.State;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode
public class RequiredState implements State {

    @Override
    public boolean isValid(Object obj) {
        Map<Object, Object> map = (Map<Object, Object>) obj;
        return map != null;
    }
}
