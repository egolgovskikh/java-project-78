package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    void testString() {
        StringSchema actual = validator.string();
        StringSchema expected = new StringSchema();
        assertEquals(expected, actual);
    }

    @Test
    void number() {
        NumberSchema actual = validator.number();
        NumberSchema expected = new NumberSchema();
        assertEquals(expected, actual);
    }

    @Test
    void map() {
        MapSchema<String, String> actual = validator.map();
        MapSchema<String, String>  expected = new MapSchema<>();
        assertEquals(expected, actual);
    }
}
