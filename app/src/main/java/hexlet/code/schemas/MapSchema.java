package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        states.add(Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        states.add(x -> {
            if (x == null) {
                return false;
            }
            return x.size() == size;
        });
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        schemas.forEach(
                (k, schema) -> states.add(
                        x -> x.entrySet().stream().allMatch(
                                kvEntry -> schema.isValid((String) x.get(kvEntry.getKey()))
                        )
                )
        );
        return this;
    }
}
