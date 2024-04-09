package hexlet.code.schemas;

import hexlet.code.states.string.ContainsState;
import hexlet.code.states.string.MinLengthState;
import hexlet.code.states.string.RequiredState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static hexlet.code.TestUtil.changeField;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StringSchemaTest {

    private StringSchema stringSchema;

    @BeforeEach
    void setUp() {
        stringSchema = new StringSchema();
    }

    @Test
    void testRequired() {
        stringSchema = stringSchema.required();
        StringSchema expected = new StringSchema();
        changeField(expected, List.of(new RequiredState()));
        assertEquals(expected, stringSchema);
    }

    @Test
    void testMinLength() {
        stringSchema = stringSchema.minLength(3);
        StringSchema expected = new StringSchema();
        changeField(expected, List.of(new MinLengthState(3)));
        assertEquals(expected, stringSchema);
    }

    @Test
    void testContains() {
        stringSchema = stringSchema.contains("h");
        StringSchema expected = new StringSchema();
        changeField(expected, List.of(new ContainsState("h")));
        assertEquals(expected, stringSchema);
    }

    @Test
    void testIsValid() {
        stringSchema = stringSchema.required().minLength(10).contains("he");
        assertTrue(stringSchema.isValid("hello my friend"));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid("hello"));
        assertFalse(stringSchema.isValid("good morning my friend"));

        stringSchema = stringSchema.required().minLength(10).contains("he").minLength(4);
        assertTrue(stringSchema.isValid("hello"));

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
        assertFalse(stringSchema.isValid("hi"));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(null));
    }

    @Test
    void testIsValidOnlyContains() {
        stringSchema = stringSchema.contains("h");
        assertTrue(stringSchema.isValid("hello"));
        assertFalse(stringSchema.isValid("good morning"));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(null));
    }

    @Test
    void testHashCode() {
        stringSchema = stringSchema.required();
        StringSchema expectedSchema = new StringSchema();
        changeField(expectedSchema, List.of(new RequiredState()));
        assertEquals(expectedSchema.hashCode(), stringSchema.hashCode());
    }

    @Test
    void testEquals() {
        stringSchema = stringSchema.required();

        StringSchema sameSchema = new StringSchema();
        changeField(sameSchema, List.of(new RequiredState()));

        assertEquals(stringSchema, sameSchema);

        StringSchema emptySchema = new StringSchema();
        assertNotEquals(stringSchema, emptySchema);

        Object object = new Object();
        assertNotEquals(stringSchema, object);
    }

}
