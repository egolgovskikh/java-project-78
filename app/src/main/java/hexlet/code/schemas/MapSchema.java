package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema<K> extends BaseSchema<Map<?, ?>> {

    public MapSchema<K> required() {
        states.add(Objects::nonNull);
        return this;
    }

    public MapSchema<K> sizeof(int size) {
        states.add(x -> {
            if (x == null) {
                return false;
            }
            return x.size() == size;
        });
        return this;
    }

    public void shape(Map<K, BaseSchema> schemas) {
        schemas.forEach(
                (k, schema) -> states.add(
                        x -> x.entrySet().stream().allMatch(kvEntry -> schema.isValid(x.get(kvEntry.getKey())))
                )
        );
    }
}
