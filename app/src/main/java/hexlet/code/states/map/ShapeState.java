package hexlet.code.states.map;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.states.State;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class ShapeState implements State {

    private BaseSchema schema;

    @Override
    public boolean isValid(Object obj) {
        Map<Object, Object> map = (Map<Object, Object>) obj;
        return map.entrySet().stream().allMatch(kvEntry -> schema.isValid(map.get(kvEntry.getKey())));
    }
}
