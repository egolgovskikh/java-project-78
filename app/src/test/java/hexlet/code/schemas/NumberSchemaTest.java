package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {

    private NumberSchema numberSchema;

    @BeforeEach
    void setUp() {
        Validator validator = new Validator();
        numberSchema = validator.number();
    }

    @Test
    void testIsValid() {
        numberSchema = numberSchema.required().positive().range(1, 9);
        assertTrue(numberSchema.isValid(5));
        assertFalse(numberSchema.isValid(null));
        assertFalse(numberSchema.isValid(-1));
        assertFalse(numberSchema.isValid(10));

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
        assertTrue(numberSchema.isValid(null));
        assertFalse(numberSchema.isValid(0));
        assertFalse(numberSchema.isValid(-1));
    }

    @Test
    void testIsValidOnlyRange() {
        numberSchema = numberSchema.range(1, 9);
        assertTrue(numberSchema.isValid(5));
        assertTrue(numberSchema.isValid(null));
        assertFalse(numberSchema.isValid(0));
    }
}
