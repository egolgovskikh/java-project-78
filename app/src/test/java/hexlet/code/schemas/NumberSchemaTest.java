package hexlet.code.schemas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hexlet.code.schemas.StringSchemaTest.changeField;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NumberSchemaTest {

    private NumberSchema numberSchema;

    @BeforeEach
    void setUp() {
        numberSchema = new NumberSchema();
    }

    @Test
    void testRequired() {
        numberSchema = numberSchema.required();
        NumberSchema expected = new NumberSchema();
        changeField(expected, "required", true);
        assertEquals(expected, numberSchema);
    }

    @Test
    void testPositive() {
        numberSchema = numberSchema.positive();
        NumberSchema expected = new NumberSchema();
        changeField(expected, "positive", true);
        assertEquals(expected, numberSchema);
    }

    @Test
    void testRange() {
        numberSchema = numberSchema.range(1, 10);
        NumberSchema expected = new NumberSchema();
        changeField(expected, "min", 1);
        changeField(expected, "max", 10);
        assertEquals(expected, numberSchema);
    }

    @Test
    void testIsValid() {
        numberSchema = numberSchema.required().positive().range(1, 9);
        assertTrue(numberSchema.isValid(5));
        assertFalse(numberSchema.isValid(null));
        assertFalse(numberSchema.isValid(-1));
        assertFalse(numberSchema.isValid(10));

        numberSchema = numberSchema.required().positive().range(1, 9).range(10, 13);
        assertTrue(numberSchema.isValid(12));

        numberSchema = new NumberSchema();
        assertTrue(numberSchema.isValid(null));
    }

    @Test
    void testIsValidOnlyRequired() {
        numberSchema = numberSchema.required();
        assertTrue(numberSchema.isValid(1));
        assertFalse(numberSchema.isValid(null));
    }

    @Test
    void testIsValidOnlyPositive() {
        numberSchema = numberSchema.positive();
        assertTrue(numberSchema.isValid(1));
        assertFalse(numberSchema.isValid(0));
        assertFalse(numberSchema.isValid(-1));
        assertFalse(numberSchema.isValid(null));
    }

    @Test
    void testIsValidOnlyRange() {
        numberSchema = numberSchema.range(1, 9);
        assertTrue(numberSchema.isValid(5));
        assertFalse(numberSchema.isValid(0));
        assertFalse(numberSchema.isValid(null));
    }

    @Test
    void testHashCode() {
        numberSchema = numberSchema.required();

        NumberSchema expectedSchema = new NumberSchema();
        changeField(expectedSchema, "required", true);

        assertEquals(expectedSchema.hashCode(), numberSchema.hashCode());
    }

    @Test
    void testEquals() {
        numberSchema = numberSchema.required();

        NumberSchema sameSchema = new NumberSchema();
        changeField(sameSchema, "required", true);

        assertEquals(numberSchema, sameSchema);

        NumberSchema emptySchema = new NumberSchema();
        assertNotEquals(numberSchema, emptySchema);

        Object object = new Object();
        assertNotEquals(numberSchema, object);
    }
}