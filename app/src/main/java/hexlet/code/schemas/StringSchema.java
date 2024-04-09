package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        states.add(x -> x != null && !x.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        states.add(x -> x == null || x.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        states.add(x -> x == null || x.contains(substring));
        return this;
    }
}
