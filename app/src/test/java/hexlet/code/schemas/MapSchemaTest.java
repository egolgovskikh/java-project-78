package hexlet.code.schemas;

import hexlet.code.states.map.RequiredState;
import hexlet.code.states.map.SizeOfState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hexlet.code.TestUtil.changeField;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {

    private MapSchema<String, String> mapSchema;

    @BeforeEach
    void setUp() {
        mapSchema = new MapSchema<>();
    }

    @Test
    void testRequired() {
        mapSchema = mapSchema.required();
        MapSchema<String, String> expected = new MapSchema<>();
        changeField(expected, List.of(new RequiredState<>()));
        assertEquals(expected, mapSchema);
    }

    @Test
    void testSizeOf() {
        mapSchema = mapSchema.sizeOf(3);
        MapSchema<String, String> expected = new MapSchema<>();
        changeField(expected, List.of(new SizeOfState<>(3)));
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
        MapSchema<String, String> expectedSchema = new MapSchema<>();
        changeField(expectedSchema, List.of(new RequiredState<>()));
        assertEquals(expectedSchema.hashCode(), mapSchema.hashCode());
    }

    @Test
    void testEquals() {
        mapSchema = mapSchema.required();

        MapSchema<String, String> sameSchema = new MapSchema<>();
        changeField(sameSchema, List.of(new RequiredState<>()));

        assertEquals(mapSchema, sameSchema);

        MapSchema<String, String> emptySchema = new MapSchema<>();
        assertNotEquals(mapSchema, emptySchema);

        Object object = new Object();
        assertNotEquals(mapSchema, object);
    }
}
