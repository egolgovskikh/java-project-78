package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

//        stringSchema = stringSchema.required().minLength(10).contains("he").minLength(4);
//        assertTrue(stringSchema.isValid("hello"));

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

    //todo
    private final Validator validator = new Validator();

    @Test
    public void stringSchemaTest() {
        StringSchema schema = validator.string();
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));
        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    public void numberSchemaTest() {
        NumberSchema schema = validator.number();
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));

        schema.range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    public void mapSchemaTest() {
        MapSchema schema = validator.map();
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        HashMap<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        schema.sizeof(2);

        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void shapeTest() {
        MapSchema schema = validator.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));
    }
}
