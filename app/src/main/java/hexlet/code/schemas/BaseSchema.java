package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {

    protected Map<String, Predicate<T>> states = new HashMap<>();

    /**
     *
     * @param obj the checked object
     * @return the result of checking
     */
    public boolean isValid(T obj) {
        return states.entrySet().stream().allMatch(s -> s.getValue().test(obj));
    }

}
