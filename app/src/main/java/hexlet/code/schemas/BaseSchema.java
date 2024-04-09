package hexlet.code.schemas;

import hexlet.code.states.State;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class BaseSchema<T> {

    List<State<T>> states = new ArrayList<>();

    public boolean isValid(T obj) {
        return states.stream().allMatch(s -> s.isValid(obj));
    }

}
