package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {

    Map<String, Predicate<T>> states = new HashMap<>();

    public boolean isValid(T obj) {
        return states.entrySet().stream().allMatch(s -> s.getValue().test(obj));
    }

}
