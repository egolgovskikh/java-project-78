package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {

    List<Predicate<T>> states = new ArrayList<>();

    public boolean isValid(T obj) {
        return states.stream().allMatch(s -> s.test(obj));
    }

}
