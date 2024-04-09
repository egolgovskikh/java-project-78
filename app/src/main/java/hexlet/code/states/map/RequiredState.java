package hexlet.code.states.map;

import hexlet.code.states.State;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode
public class RequiredState<K, V> implements State<Map<K, V>> {

    @Override
    public boolean isValid(Map<K, V> map) {
        return map != null;
    }
}
