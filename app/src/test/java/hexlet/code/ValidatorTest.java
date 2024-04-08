package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorTest {

    @Test
    void testString() {
        Validator v = new Validator();
        StringSchema actual = v.string();
        StringSchema expected = new StringSchema();
        assertEquals(expected, actual);
    }
}
