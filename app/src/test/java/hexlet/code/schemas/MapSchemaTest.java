package hexlet.code.schemas;


import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {

    private MapSchema<String> mapSchema;

    @BeforeEach
    void setUp() {
        mapSchema = new MapSchema<>();
    }

    @Test
    void testIsValid() {
        mapSchema = mapSchema.required().sizeof(2);
        Map<String, String> data = new HashMap<>();
        data.put("1", "first");
        data.put("2", "second");
        assertTrue(mapSchema.isValid(data));
        assertFalse(mapSchema.isValid(null));
        assertFalse(mapSchema.isValid(new HashMap<>()));
        data.remove("1");
        assertFalse(mapSchema.isValid(data));
    }

    @Test
    void testIsValidOnlyRequired() {
        mapSchema = mapSchema.required();
        Map<String, String> data = new HashMap<>();
        data.put("3", "third");
        assertTrue(mapSchema.isValid(data));
        assertTrue(mapSchema.isValid(new HashMap<>()));
        assertFalse(mapSchema.isValid(null));
    }

    @Test
    void testIsValidOnlySizeOf() {
        mapSchema = mapSchema.sizeof(3);
        Map<String, String> data = new HashMap<>();
        data.put("1", "first");
        data.put("2", "second");
        data.put("3", "third");
        assertTrue(mapSchema.isValid(data));
        assertFalse(mapSchema.isValid(null));
        assertFalse(mapSchema.isValid(new HashMap<>()));
        data.remove("2");
        assertFalse(mapSchema.isValid(data));
    }

    @Test
    void testShape() {
        var v = new Validator();

        MapSchema<String> schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();

        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

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
