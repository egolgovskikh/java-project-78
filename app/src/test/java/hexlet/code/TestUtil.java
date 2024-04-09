package hexlet.code;

import hexlet.code.states.State;

import java.lang.reflect.Field;
import java.util.List;

public class TestUtil {
    public static <T, V> void changeField(T obj, List<State<V>> newStates) {
        Field states;
        try {
            states = obj.getClass().getSuperclass().getDeclaredField("states");
            states.setAccessible(true);
            states.set(obj, newStates);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
