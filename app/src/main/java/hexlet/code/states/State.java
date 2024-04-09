package hexlet.code.states;

public interface State<T> {

    boolean isValid(T obj);

}
