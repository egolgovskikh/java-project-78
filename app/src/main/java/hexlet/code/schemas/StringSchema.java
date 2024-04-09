package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        states.put("required", x -> x != null && !x.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        states.put("minLength", x -> x == null || x.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        states.put("contains", x -> x == null || x.contains(substring));
        return this;
    }
}
