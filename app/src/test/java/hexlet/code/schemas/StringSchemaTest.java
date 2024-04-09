package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StringSchemaTest {

    private StringSchema stringSchema;

    @BeforeEach
    void setUp() {
        Validator validator = new Validator();
        stringSchema = validator.string();
    }

    @Test
    void testIsValid() {
        stringSchema = stringSchema.required().minLength(10).contains("he");
        assertTrue(stringSchema.isValid("hello my friend"));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid("hello"));
        assertFalse(stringSchema.isValid("good morning my friend"));

        stringSchema = new StringSchema();
        assertTrue(stringSchema.isValid(null));
    }

    @Test
    void testIsValidOnlyRequired() {
        stringSchema = stringSchema.required();
        assertTrue(stringSchema.isValid("hello"));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(null));
    }

    @Test
    void testIsValidOnlyMinLength() {
        stringSchema = stringSchema.minLength(3);
        assertTrue(stringSchema.isValid("hello"));
        assertTrue(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid("hi"));
        assertFalse(stringSchema.isValid(""));
    }

    @Test
    void testIsValidOnlyContains() {
        stringSchema = stringSchema.contains("h");
        assertTrue(stringSchema.isValid("hello"));
        assertTrue(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid("good morning"));
        assertFalse(stringSchema.isValid(""));
    }
}
