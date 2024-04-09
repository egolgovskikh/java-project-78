package hexlet.code.schemas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static hexlet.code.schemas.StringSchemaTest.changeField;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {

    private MapSchema mapSchema;

    @BeforeEach
    void setUp() {
        mapSchema = new MapSchema();
    }

    @Test
    void testRequired() {
        mapSchema = mapSchema.required();
        MapSchema expected = new MapSchema();
        changeField(expected, "required", true);
        assertEquals(expected, mapSchema);
    }

    @Test
    void testSizeOf() {
        mapSchema = mapSchema.sizeOf(3);
        MapSchema expected = new MapSchema();
        changeField(expected, "sizeOf", 3);
        assertEquals(expected, mapSchema);
    }

    @Test
    void testIsValid() {
        mapSchema = mapSchema.required().sizeOf(2);
        Map<String, String> data = new HashMap<>();
        data.put("1", "first");
        data.put("2", "second");
        assertTrue(mapSchema.isValid(data));
        assertFalse(mapSchema.isValid(null));
        assertFalse(mapSchema.isValid(new HashMap<String, String>()));
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
        mapSchema = mapSchema.sizeOf(3);
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
    void testHashCode() {
        mapSchema = mapSchema.required();

        MapSchema expectedSchema = new MapSchema();
        changeField(expectedSchema, "required", true);

        assertEquals(expectedSchema.hashCode(), mapSchema.hashCode());
    }

    @Test
    void testEquals() {
        mapSchema = mapSchema.required();

        MapSchema sameSchema = new MapSchema();
        changeField(sameSchema, "required", true);

        assertEquals(mapSchema, sameSchema);

        MapSchema emptySchema = new MapSchema();
        assertNotEquals(mapSchema, emptySchema);

        Object object = new Object();
        assertNotEquals(mapSchema, object);
    }
}
