package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        states.put("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        states.put("sizeof", x -> x != null && x.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        states.put(
                "shape",
                map -> schemas.entrySet().stream().allMatch(e -> {
                    String v = (String) map.get(e.getKey());
                    BaseSchema<String> schema = e.getValue();
                    return schema.isValid(v);
                })
        );
        return this;
    }
}
