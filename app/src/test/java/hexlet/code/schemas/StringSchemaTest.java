package hexlet.code.schemas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class StringSchemaTest {

    private StringSchema stringSchema;

    @BeforeEach
    void beforeEach() {
        stringSchema = new StringSchema();
    }

    @Test
    void testRequired() {
        stringSchema = stringSchema.required();

        StringSchema expected = new StringSchema();
        changeField(expected, "required", true);

        assertEquals(expected, stringSchema);
    }

    @Test
    void testMinLength() {
        stringSchema = stringSchema.minLength(3);

        StringSchema expected = new StringSchema();
        changeField(expected, "minLength", 3);

        assertEquals(expected, stringSchema);
    }

    @Test
    void testContains() {
        stringSchema = stringSchema.contains("h");

        StringSchema expected = new StringSchema();
        changeField(expected, "contains", "h");

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
        changeField(expectedSchema, "required", true);

        assertEquals(expectedSchema.hashCode(), stringSchema.hashCode());
    }

    @Test
    void testEquals() {
        stringSchema = stringSchema.required();

        StringSchema sameSchema = new StringSchema();
        changeField(sameSchema, "required", true);

        assertEquals(stringSchema, sameSchema);

        StringSchema emptySchema = new StringSchema();
        assertNotEquals(stringSchema, emptySchema);

        Object object = new Object();
        assertNotEquals(stringSchema, object);
    }

    public static <T, V> void changeField(T object, String filedName, V newValue) {
        Field field;
        try {
            try {
                field = object.getClass().getDeclaredField(filedName);
            } catch (NoSuchFieldException e) {
                field = object.getClass().getSuperclass().getDeclaredField(filedName);
            }
            field.setAccessible(true);
            field.set(object, newValue);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
