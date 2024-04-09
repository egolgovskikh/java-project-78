package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorTest {

    private Validator validator;

    @BeforeEach
    void beforeEach() {
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
}
