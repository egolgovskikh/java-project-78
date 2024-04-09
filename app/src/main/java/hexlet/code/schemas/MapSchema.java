package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<MapSchema> {

    private int sizeOf = 0;

    public <K, V> boolean isValid(Map<K, V> map) {
        return checkRequired(map) && checkSizeOf(map);
    }

    public MapSchema sizeOf(int size) {
        sizeOf = size;
        return this;
    }


    private <K, V> boolean checkRequired(Map<K, V> map) {
        if (required) {
            return map != null;
        }
        return true;
    }


    private <K, V> boolean checkSizeOf(Map<K, V> map) {
        if (sizeOf != 0) {
            if (map == null) {
                return false;
            }
            return map.size() == sizeOf;
        }
        return true;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        MapSchema that = (MapSchema) object;
        return required == that.required && sizeOf == that.sizeOf;
    }

    @Override
    public int hashCode() {
        return Objects.hash(required, sizeOf);
    }
}
