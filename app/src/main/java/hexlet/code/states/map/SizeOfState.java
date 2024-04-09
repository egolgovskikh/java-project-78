package hexlet.code.states.map;

import hexlet.code.states.State;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Map;

@AllArgsConstructor
@EqualsAndHashCode
public class SizeOfState<K, V> implements State<Map<K, V>> {

    private final int sizeOf;

    @Override
    public boolean isValid(Map<K, V> map) {
        if (map == null) {
            return false;
        }
        return map.size() == sizeOf;
    }
}
