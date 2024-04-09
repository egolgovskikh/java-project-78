package hexlet.code.schemas;

import hexlet.code.states.State;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class BaseSchema {

    List<State> states = new ArrayList<>();

    public boolean isValid(Object obj) {
        return states.stream().allMatch(s -> s.isValid(obj));
    }

}
