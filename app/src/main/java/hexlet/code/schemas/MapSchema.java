package hexlet.code.schemas;

import hexlet.code.states.map.RequiredState;
import hexlet.code.states.map.ShapeState;
import hexlet.code.states.map.SizeOfState;

import java.util.Map;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {

    public MapSchema<K, V> required() {
        states.add(new RequiredState<>());
        return this;
    }

    public MapSchema<K, V> sizeOf(int size) {
        states.removeIf(s -> s.getClass().equals(SizeOfState.class));
        states.add(new SizeOfState<>(size));
        return this;
    }

    public void shape(Map<K, BaseSchema<V>> schemas) {
        schemas.forEach(
                (k, schema) -> states.add(new ShapeState<>(schema))
        );
    }
}
