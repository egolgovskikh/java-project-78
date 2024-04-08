package hexlet.code.schemas;

public class BaseSchema<T> {

    protected boolean required = false;

    @SuppressWarnings("unchecked")
    public T required() {
        required = true;
        return (T) this;
    }
}
