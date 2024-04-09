package hexlet.code.states.map;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.states.State;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class ShapeState<K, V> implements State<Map<K, V>> {

    private BaseSchema<V> schema;

    @Override
    public boolean isValid(Map<K, V> obj) {
        return obj.entrySet().stream().allMatch(kvEntry -> schema.isValid(obj.get(kvEntry.getKey())));
    }
}
