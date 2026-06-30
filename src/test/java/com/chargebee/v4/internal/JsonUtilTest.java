package com.chargebee.v4.internal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JsonUtil Tests")
class JsonUtilTest {

    private enum Status { ACTIVE, IN_TRIAL, NON_RENEWING }

    // ========== parse / parseToArray ==========
    @Nested
    @DisplayName("parse Tests")
    class ParseTests {

        @Test void validJson() {
            JsonObject obj = JsonUtil.parse("{\"id\": \"sub_123\", \"amount\": 1000}");
            assertNotNull(obj);
            assertEquals("sub_123", JsonUtil.getString(obj, "id"));
            assertEquals(1000L, JsonUtil.getLong(obj, "amount"));
        }

        @Test void nullReturnsEmptyObject() {
            JsonObject obj = JsonUtil.parse(null);
            assertNotNull(obj);
            assertTrue(obj.entrySet().isEmpty());
        }

        @Test void emptyStringReturnsEmptyObject() {
            assertNotNull(JsonUtil.parse(""));
            assertNotNull(JsonUtil.parse("  "));
        }

        @Test void invalidJsonReturnsEmptyObject() {
            JsonObject obj = JsonUtil.parse("not json");
            assertNotNull(obj);
            assertTrue(obj.entrySet().isEmpty());
        }

        @Test void parseToArrayValid() {
            JsonArray arr = JsonUtil.parseToArray("[1, 2, 3]");
            assertNotNull(arr);
            assertEquals(3, arr.size());
        }

        @Test void parseToArrayNull() {
            JsonArray arr = JsonUtil.parseToArray(null);
            assertNotNull(arr);
            assertEquals(0, arr.size());
        }
    }

    // ========== getString ==========
    @Nested
    @DisplayName("getString Tests")
    class GetStringTests {

        @Test void simpleString() {
            JsonObject obj = JsonUtil.parse("{\"name\": \"John\"}");
            assertEquals("John", JsonUtil.getString(obj, "name"));
        }

        @Test void stringWithSpaces() {
            JsonObject obj = JsonUtil.parse("{\"message\": \"Hello World\"}");
            assertEquals("Hello World", JsonUtil.getString(obj, "message"));
        }

        @Test void escapedQuotes() {
            JsonObject obj = JsonUtil.parse("{\"text\": \"He said \\\"Hello\\\"\"}");
            assertEquals("He said \"Hello\"", JsonUtil.getString(obj, "text"));
        }

        @Test void escapedBackslashes() {
            JsonObject obj = JsonUtil.parse("{\"path\": \"C:\\\\Users\\\\test\"}");
            assertEquals("C:\\Users\\test", JsonUtil.getString(obj, "path"));
        }

        @Test void newlinesAndTabs() {
            JsonObject obj = JsonUtil.parse("{\"text\": \"line1\\nline2\\ttab\"}");
            assertEquals("line1\nline2\ttab", JsonUtil.getString(obj, "text"));
        }

        @Test void missingKeyReturnsNull() {
            JsonObject obj = JsonUtil.parse("{\"name\": \"John\"}");
            assertNull(JsonUtil.getString(obj, "missing"));
        }

        @Test void nullObjReturnsNull() {
            assertNull(JsonUtil.getString((JsonObject) null, "key"));
        }

        @Test void nullKeyReturnsNull() {
            JsonObject obj = JsonUtil.parse("{\"name\": \"John\"}");
            assertNull(JsonUtil.getString(obj, null));
        }

        @Test void emptyString() {
            JsonObject obj = JsonUtil.parse("{\"empty\": \"\"}");
            assertEquals("", JsonUtil.getString(obj, "empty"));
        }

        @Test void unicode() {
            JsonObject obj = JsonUtil.parse("{\"text\": \"日本語 中文 한국어\"}");
            assertEquals("日本語 中文 한국어", JsonUtil.getString(obj, "text"));
        }

        @Test void colonsInValue() {
            JsonObject obj = JsonUtil.parse("{\"url\": \"https://example.com:8080/path\"}");
            assertEquals("https://example.com:8080/path", JsonUtil.getString(obj, "url"));
        }
    }

    // ========== getLong ==========
    @Nested
    @DisplayName("getLong Tests")
    class GetLongTests {

        @Test void positiveLong() {
            JsonObject obj = JsonUtil.parse("{\"id\": 12345678901234}");
            assertEquals(12345678901234L, JsonUtil.getLong(obj, "id"));
        }

        @Test void negativeLong() {
            JsonObject obj = JsonUtil.parse("{\"value\": -9876543210}");
            assertEquals(-9876543210L, JsonUtil.getLong(obj, "value"));
        }

        @Test void zero() {
            JsonObject obj = JsonUtil.parse("{\"count\": 0}");
            assertEquals(0L, JsonUtil.getLong(obj, "count"));
        }

        @Test void missingKeyReturnsNull() {
            JsonObject obj = JsonUtil.parse("{\"id\": 123}");
            assertNull(JsonUtil.getLong(obj, "missing"));
        }

        @Test void epochTimestamp() {
            JsonObject obj = JsonUtil.parse("{\"created_at\": 1605530769}");
            assertEquals(1605530769L, JsonUtil.getLong(obj, "created_at"));
        }

        @Test void resourceVersion() {
            JsonObject obj = JsonUtil.parse("{\"resource_version\": 1605530769000}");
            assertEquals(1605530769000L, JsonUtil.getLong(obj, "resource_version"));
        }
    }

    // ========== getInteger ==========
    @Nested
    @DisplayName("getInteger Tests")
    class GetIntegerTests {

        @Test void positiveInteger() {
            JsonObject obj = JsonUtil.parse("{\"count\": 42}");
            assertEquals(42, JsonUtil.getInteger(obj, "count"));
        }

        @Test void negativeInteger() {
            JsonObject obj = JsonUtil.parse("{\"offset\": -10}");
            assertEquals(-10, JsonUtil.getInteger(obj, "offset"));
        }

        @Test void missingKeyReturnsNull() {
            JsonObject obj = JsonUtil.parse("{\"count\": 42}");
            assertNull(JsonUtil.getInteger(obj, "missing"));
        }
    }

    // ========== getBoolean ==========
    @Nested
    @DisplayName("getBoolean Tests")
    class GetBooleanTests {

        @Test void trueValue() {
            JsonObject obj = JsonUtil.parse("{\"active\": true}");
            assertTrue(JsonUtil.getBoolean(obj, "active"));
        }

        @Test void falseValue() {
            JsonObject obj = JsonUtil.parse("{\"deleted\": false}");
            assertFalse(JsonUtil.getBoolean(obj, "deleted"));
        }

        @Test void missingKeyReturnsNull() {
            JsonObject obj = JsonUtil.parse("{\"active\": true}");
            assertNull(JsonUtil.getBoolean(obj, "missing"));
        }

        @Test void nullObjReturnsNull() {
            assertNull(JsonUtil.getBoolean((JsonObject) null, "key"));
        }
    }

    // ========== getDouble ==========
    @Nested
    @DisplayName("getDouble Tests")
    class GetDoubleTests {

        @Test void positiveDouble() {
            JsonObject obj = JsonUtil.parse("{\"price\": 99.99}");
            assertEquals(99.99, JsonUtil.getDouble(obj, "price"), 0.001);
        }

        @Test void negativeDouble() {
            JsonObject obj = JsonUtil.parse("{\"balance\": -123.45}");
            assertEquals(-123.45, JsonUtil.getDouble(obj, "balance"), 0.001);
        }

        @Test void exchangeRate() {
            JsonObject obj = JsonUtil.parse("{\"exchange_rate\": 1.0}");
            assertEquals(1.0, JsonUtil.getDouble(obj, "exchange_rate"), 0.001);
        }

        @Test void integerAsDouble() {
            JsonObject obj = JsonUtil.parse("{\"amount\": 10000}");
            assertEquals(10000.0, JsonUtil.getDouble(obj, "amount"), 0.001);
        }
    }

    // ========== getBigDecimal ==========
    @Nested
    @DisplayName("getBigDecimal Tests")
    class GetBigDecimalTests {

        @Test void decimalValue() {
            JsonObject obj = JsonUtil.parse("{\"amount\": 1234.56}");
            assertEquals(new BigDecimal("1234.56"), JsonUtil.getBigDecimal(obj, "amount"));
        }

        @Test void integerAsBigDecimal() {
            JsonObject obj = JsonUtil.parse("{\"amount\": 10000}");
            assertEquals(new BigDecimal("10000"), JsonUtil.getBigDecimal(obj, "amount"));
        }
    }

    // ========== getTimestamp ==========
    @Nested
    @DisplayName("getTimestamp Tests")
    class GetTimestampTests {

        @Test void epochSecondsToTimestamp() {
            JsonObject obj = JsonUtil.parse("{\"created_at\": 1605530769}");
            Timestamp result = JsonUtil.getTimestamp(obj, "created_at");
            assertNotNull(result);
            assertEquals(1605530769000L, result.getTime());
        }

        @Test void missingKeyReturnsNull() {
            JsonObject obj = JsonUtil.parse("{\"updated_at\": 1605530769}");
            assertNull(JsonUtil.getTimestamp(obj, "created_at"));
        }
    }

    // ========== getJsonObject / getObject ==========
    @Nested
    @DisplayName("getJsonObject / getObject Tests")
    class GetObjectTests {

        @Test void getJsonObjectNested() {
            JsonObject root = JsonUtil.parse("{\"customer\": {\"id\": \"cust_1\", \"email\": \"a@b.com\"}}");
            JsonObject customer = JsonUtil.getJsonObject(root, "customer");
            assertNotNull(customer);
            assertEquals("cust_1", JsonUtil.getString(customer, "id"));
            assertEquals("a@b.com", JsonUtil.getString(customer, "email"));
        }

        @Test void getJsonObjectMissingReturnsNull() {
            JsonObject obj = JsonUtil.parse("{\"name\": \"test\"}");
            assertNull(JsonUtil.getJsonObject(obj, "missing"));
        }

        @Test void getJsonObjectNonObjectReturnsNull() {
            JsonObject obj = JsonUtil.parse("{\"name\": \"test\"}");
            assertNull(JsonUtil.getJsonObject(obj, "name"));
        }

        @Test void getObjectReturnsJsonString() {
            JsonObject root = JsonUtil.parse("{\"meta\": {\"key\": \"val\"}}");
            String meta = JsonUtil.getObject(root, "meta");
            assertNotNull(meta);
            assertTrue(meta.contains("key"));
            assertTrue(meta.contains("val"));
        }

        @Test void getObjectDeeplyNested() {
            JsonObject root = JsonUtil.parse("{\"data\": {\"customer\": {\"address\": {\"city\": \"NYC\"}}}}");
            JsonObject data = JsonUtil.getJsonObject(root, "data");
            JsonObject customer = JsonUtil.getJsonObject(data, "customer");
            JsonObject address = JsonUtil.getJsonObject(customer, "address");
            assertEquals("NYC", JsonUtil.getString(address, "city"));
        }

        @Test void getObjectEmptyObject() {
            JsonObject obj = JsonUtil.parse("{\"metadata\": {}}");
            assertEquals("{}", JsonUtil.getObject(obj, "metadata"));
        }

        @Test void getObjectNonObjectReturnsNull() {
            JsonObject obj = JsonUtil.parse("{\"name\": \"John\"}");
            assertNull(JsonUtil.getObject(obj, "name"));
        }
    }

    // ========== getJsonArray / getArray ==========
    @Nested
    @DisplayName("getJsonArray / getArray Tests")
    class GetArrayTests {

        @Test void getJsonArrayValid() {
            JsonObject root = JsonUtil.parse("{\"items\": [{\"id\": \"item_1\"}, {\"id\": \"item_2\"}]}");
            JsonArray items = JsonUtil.getJsonArray(root, "items");
            assertNotNull(items);
            assertEquals(2, items.size());
            assertEquals("item_1", items.get(0).getAsJsonObject().get("id").getAsString());
        }

        @Test void getJsonArrayMissingReturnsNull() {
            JsonObject obj = JsonUtil.parse("{\"name\": \"test\"}");
            assertNull(JsonUtil.getJsonArray(obj, "missing"));
        }

        @Test void getArrayReturnsJsonString() {
            JsonObject obj = JsonUtil.parse("{\"tags\": [\"a\", \"b\"]}");
            String tags = JsonUtil.getArray(obj, "tags");
            assertNotNull(tags);
            assertTrue(tags.startsWith("["));
            assertTrue(tags.endsWith("]"));
        }

        @Test void getArrayEmpty() {
            JsonObject obj = JsonUtil.parse("{\"items\": []}");
            assertEquals("[]", JsonUtil.getArray(obj, "items"));
        }

        @Test void getArrayNestedStructures() {
            JsonObject root = JsonUtil.parse(
                "{\"list\": [{\"transaction\": {\"linked_invoices\": [{\"id\": \"inv_1\"}], \"linked_refunds\": []}}]}");
            String result = JsonUtil.getArray(root, "list");
            assertNotNull(result);
            assertTrue(result.contains("linked_invoices"));
            assertTrue(result.contains("linked_refunds"));
            assertTrue(result.contains("inv_1"));
        }

        @Test void getArrayNonArrayReturnsNull() {
            JsonObject obj = JsonUtil.parse("{\"name\": \"John\"}");
            assertNull(JsonUtil.getArray(obj, "name"));
        }
    }

    // ========== hasValue ==========
    @Nested
    @DisplayName("hasValue Tests")
    class HasValueTests {

        @Test void existingNonNullValue() {
            JsonObject obj = JsonUtil.parse("{\"name\": \"John\", \"age\": 30}");
            assertTrue(JsonUtil.hasValue(obj, "name"));
            assertTrue(JsonUtil.hasValue(obj, "age"));
        }

        @Test void nullValueReturnsFalse() {
            JsonObject obj = JsonUtil.parse("{\"name\": null}");
            assertFalse(JsonUtil.hasValue(obj, "name"));
        }

        @Test void missingKeyReturnsFalse() {
            JsonObject obj = JsonUtil.parse("{\"name\": \"John\"}");
            assertFalse(JsonUtil.hasValue(obj, "missing"));
        }
    }

    // ========== parseJsonObjectToMap ==========
    @Nested
    @DisplayName("parseJsonObjectToMap Tests")
    class ParseJsonObjectToMapTests {

        @Test void simpleObjectToMap() {
            JsonObject obj = JsonUtil.parse("{\"name\": \"John\", \"age\": 30, \"active\": true}");
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(obj);
            assertEquals("John", map.get("name"));
            assertEquals(30L, map.get("age"));
            assertEquals(true, map.get("active"));
        }

        @Test void nestedObjectsAsMaps() {
            JsonObject obj = JsonUtil.parse("{\"user\": {\"name\": \"John\"}}");
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(obj);
            assertTrue(map.get("user") instanceof Map);
            @SuppressWarnings("unchecked")
            Map<String, Object> user = (Map<String, Object>) map.get("user");
            assertEquals("John", user.get("name"));
        }

        @Test void arraysAsLists() {
            JsonObject obj = JsonUtil.parse("{\"tags\": [\"a\", \"b\"]}");
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(obj);
            assertTrue(map.get("tags") instanceof List);
            @SuppressWarnings("unchecked")
            List<Object> tags = (List<Object>) map.get("tags");
            assertEquals("a", tags.get(0));
            assertEquals("b", tags.get(1));
        }

        @Test void deeplyNestedStructures() {
            JsonObject obj =
                JsonUtil.parse(
                    "{\"subscription\": {\"id\": \"sub_1\", \"items\": [{\"id\": \"item_1\"}]}}");
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(obj);
            @SuppressWarnings("unchecked")
            Map<String, Object> subscription = (Map<String, Object>) map.get("subscription");
            assertEquals("sub_1", subscription.get("id"));
            @SuppressWarnings("unchecked")
            List<Object> items = (List<Object>) subscription.get("items");
            @SuppressWarnings("unchecked")
            Map<String, Object> item = (Map<String, Object>) items.get(0);
            assertEquals("item_1", item.get("id"));
        }

        @Test void nullReturnsEmptyMap() {
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap((JsonObject) null);
            assertTrue(map.isEmpty());
        }

        @Test void nullValuesPreserved() {
            JsonObject obj = JsonUtil.parse("{\"value\": null}");
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(obj);
            assertTrue(map.containsKey("value"));
            assertNull(map.get("value"));
        }

        @Test void doubleValues() {
            JsonObject obj = JsonUtil.parse("{\"price\": 99.99}");
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(obj);
            assertEquals(99.99, (Double) map.get("price"), 0.001);
        }
    }

    // ========== mapArray ==========
    @Nested
    @DisplayName("mapArray Tests")
    class MapArrayTests {

        @Test void mapsElements() {
            JsonArray array = JsonUtil.parseToArray("[{\"id\": \"a\"}, {\"id\": \"b\"}, {\"id\": \"c\"}]");
            List<String> ids = JsonUtil.mapArray(array, obj -> JsonUtil.getString(obj, "id"));
            assertEquals(3, ids.size());
            assertEquals("a", ids.get(0));
            assertEquals("b", ids.get(1));
            assertEquals("c", ids.get(2));
        }

        @Test void nullReturnsEmptyList() {
            List<String> result = JsonUtil.mapArray(null, obj -> "x");
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    // ========== mapArrayToObjects / mapArrayToMaps ==========
    @Nested
    @DisplayName("mapArrayToObjects / mapArrayToMaps Tests")
    class MapArrayToTests {

        @Test void mapArrayToMaps() {
            JsonArray array = JsonUtil.parseToArray("[{\"k1\": \"v1\"}, {\"k2\": 42}]");
            List<Map<String, Object>> maps = JsonUtil.mapArrayToMaps(array);
            assertEquals(2, maps.size());
            assertEquals("v1", maps.get(0).get("k1"));
            assertEquals(42L, maps.get(1).get("k2"));
        }

        @Test void mapArrayToObjects() {
            JsonArray array = JsonUtil.parseToArray("[{\"k\": \"v\"}, 42, \"text\", null]");
            List<Object> objs = JsonUtil.mapArrayToObjects(array);
            assertEquals(4, objs.size());
            assertTrue(objs.get(0) instanceof Map);
            assertEquals(42L, objs.get(1));
            assertEquals("text", objs.get(2));
            assertNull(objs.get(3));
        }
    }

    // ========== parseArrayOf* ==========
    @Nested
    @DisplayName("parseArrayOf* Tests")
    class ParseArrayOfTests {

        @Test void strings() {
            JsonArray arr = JsonUtil.parseToArray("[\"a\", \"b\", \"c\"]");
            List<String> result = JsonUtil.parseArrayOfString(arr);
            assertEquals(3, result.size());
            assertEquals("a", result.get(0));
        }

        @Test void integers() {
            JsonArray arr = JsonUtil.parseToArray("[1, 2, 3, -4, 0]");
            List<Integer> result = JsonUtil.parseArrayOfInteger(arr);
            assertEquals(5, result.size());
            assertEquals(1, result.get(0));
            assertEquals(-4, result.get(3));
            assertEquals(0, result.get(4));
        }

        @Test void longs() {
            JsonArray arr = JsonUtil.parseToArray("[1605530769000, 1605530770000]");
            List<Long> result = JsonUtil.parseArrayOfLong(arr);
            assertEquals(2, result.size());
            assertEquals(1605530769000L, result.get(0));
        }

        @Test void booleans() {
            JsonArray arr = JsonUtil.parseToArray("[true, false, true]");
            List<Boolean> result = JsonUtil.parseArrayOfBoolean(arr);
            assertEquals(3, result.size());
            assertTrue(result.get(0));
            assertFalse(result.get(1));
        }

        @Test void doubles() {
            JsonArray arr = JsonUtil.parseToArray("[1.5, 2.7, 3.14]");
            List<Double> result = JsonUtil.parseArrayOfDouble(arr);
            assertEquals(3, result.size());
            assertEquals(1.5, result.get(0), 0.001);
        }

        @Test void bigDecimals() {
            JsonArray arr = JsonUtil.parseToArray("[123.45, 678.90]");
            List<BigDecimal> result = JsonUtil.parseArrayOfBigDecimal(arr);
            assertEquals(2, result.size());
        }

        @Test void nullReturnsEmptyLists() {
            assertTrue(JsonUtil.parseArrayOfString((JsonArray) null).isEmpty());
            assertTrue(JsonUtil.parseArrayOfInteger((JsonArray) null).isEmpty());
            assertTrue(JsonUtil.parseArrayOfLong((JsonArray) null).isEmpty());
            assertTrue(JsonUtil.parseArrayOfBoolean((JsonArray) null).isEmpty());
            assertTrue(JsonUtil.parseArrayOfDouble((JsonArray) null).isEmpty());
            assertTrue(JsonUtil.parseArrayOfBigDecimal((JsonArray) null).isEmpty());
        }
    }

    // ========== extractCustomFields / extractConsentFields ==========
    @Nested
    @DisplayName("extractCustomFields / extractConsentFields Tests")
    class ExtractFieldsTests {

        @Test void extractsCustomFields() {
            JsonObject obj = JsonUtil.parse("{\"id\": \"sub_1\", \"cf_color\": \"blue\", \"cf_size\": \"large\", \"name\": \"test\"}");
            Set<String> known = new HashSet<>();
            known.add("id");
            known.add("name");
            Map<String, String> cf = JsonUtil.extractCustomFields(obj, known);
            assertEquals(2, cf.size());
            assertEquals("blue", cf.get("cf_color"));
            assertEquals("large", cf.get("cf_size"));
        }

        @Test void customFieldNullValues() {
            JsonObject obj = JsonUtil.parse("{\"cf_nullable\": null}");
            Map<String, String> cf = JsonUtil.extractCustomFields(obj, new HashSet<>());
            assertEquals(1, cf.size());
            assertNull(cf.get("cf_nullable"));
        }

        @Test void excludesKnownCustomFields() {
            JsonObject obj = JsonUtil.parse("{\"cf_known\": \"val\"}");
            Set<String> known = new HashSet<>();
            known.add("cf_known");
            Map<String, String> cf = JsonUtil.extractCustomFields(obj, known);
            assertTrue(cf.isEmpty());
        }

        @Test void extractsConsentFields() {
            JsonObject obj = JsonUtil.parse("{\"id\": \"cust_1\", \"cs_marketing\": true, \"cs_analytics\": false}");
            Set<String> known = new HashSet<>();
            known.add("id");
            Map<String, Object> cs = JsonUtil.extractConsentFields(obj, known);
            assertEquals(2, cs.size());
            assertEquals(true, cs.get("cs_marketing"));
            assertEquals(false, cs.get("cs_analytics"));
        }
    }

    // ========== toJson ==========
    @Nested
    @DisplayName("toJson Tests")
    class ToJsonTests {

        @Test void serializeMap() {
            Map<String, Object> map = new java.util.LinkedHashMap<>();
            map.put("name", "John");
            map.put("age", 30);
            String json = JsonUtil.toJson(map);
            assertTrue(json.contains("\"name\":\"John\""));
            assertTrue(json.contains("\"age\":30"));
        }

        @Test void serializeList() {
            List<String> list = java.util.Arrays.asList("a", "b", "c");
            assertEquals("[\"a\",\"b\",\"c\"]", JsonUtil.toJson(list));
        }

        @Test void serializeEmptyMap() {
            assertEquals("{}", JsonUtil.toJson(new java.util.HashMap<>()));
        }

        @Test void serializeEmptyList() {
            assertEquals("[]", JsonUtil.toJson(new java.util.ArrayList<>()));
        }

        @Test void escapeSpecialCharacters() {
            Map<String, Object> map = new java.util.HashMap<>();
            map.put("text", "Hello \"World\"\nNew line");
            String json = JsonUtil.toJson(map);
            assertTrue(json.contains("\\\"World\\\""));
            assertTrue(json.contains("\\n"));
        }

        @Test void serializeNullValues() {
            Map<String, Object> map = new java.util.HashMap<>();
            map.put("value", null);
            String json = JsonUtil.toJson(map);
            assertTrue(json.contains("\"value\":null"));
        }

        @Test void serializeNestedStructures() {
            Map<String, Object> inner = new java.util.HashMap<>();
            inner.put("id", 123);
            Map<String, Object> outer = new java.util.HashMap<>();
            outer.put("data", inner);
            String json = JsonUtil.toJson(outer);
            assertTrue(json.contains("\"data\":{"));
            assertTrue(json.contains("\"id\":123"));
        }
    }

    // ========== Timestamp / Date / Enum serialization ==========
    // Regression coverage for a bug where java.sql.Timestamp values were emitted
    // in human-readable form (e.g. "2026-06-23 09:54:44.513") because they fell
    // through to the default `value.toString()` branch in toJsonElement(...).
    // The JSON path emits Timestamp as Unix milliseconds (Timestamp.getTime()).
    @Nested
    @DisplayName("Timestamp / Date / Enum serialization")
    class TimestampDateEnumSerialization {

        @Test void timestampIsEmittedAsUnixMillisNumber() {
            Timestamp ts = Timestamp.from(java.time.Instant.parse("2026-06-23T09:54:44Z"));
            long expected = ts.getTime();
            Map<String, Object> map = new java.util.LinkedHashMap<>();
            map.put("expires_at", ts);

            String json = JsonUtil.toJson(map);
            JsonObject parsed = JsonUtil.parse(json);

            assertEquals(expected, JsonUtil.getLong(parsed, "expires_at"),
                    "Timestamp must be serialized as Unix-millis number");
            assertTrue(json.contains("\"expires_at\":" + expected),
                    "JSON should contain numeric expires_at. Got: " + json);
            // And must NOT be a quoted string of any shape.
            assertFalse(json.matches(".*\"expires_at\"\\s*:\\s*\"[^\"]+\".*"),
                    "Timestamp must not be quoted. Got: " + json);
            assertFalse(json.contains(ts.toString()),
                    "JSON must not contain Timestamp.toString() output. Got: " + json);
        }

        @Test void dateIsEmittedAsYyyyMmDdString() {
            java.util.Calendar cal = java.util.Calendar.getInstance(java.util.TimeZone.getDefault());
            cal.clear();
            cal.set(2025, java.util.Calendar.DECEMBER, 31, 10, 0, 0);
            Date d = cal.getTime();
            String expected = new java.text.SimpleDateFormat("yyyy-MM-dd").format(d);

            Map<String, Object> map = new java.util.HashMap<>();
            map.put("trial_end_date", d);

            String json = JsonUtil.toJson(map);
            assertEquals(expected, JsonUtil.getString(JsonUtil.parse(json), "trial_end_date"));
        }

        @Test void enumIsEmittedAsLowercaseString() {
            Map<String, Object> map = new java.util.HashMap<>();
            map.put("status", Status.IN_TRIAL);

            String json = JsonUtil.toJson(map);
            assertEquals("in_trial", JsonUtil.getString(JsonUtil.parse(json), "status"));
        }

        @Test void timestampNestedInsideMapIsConverted() {
            Timestamp ts = Timestamp.from(java.time.Instant.parse("2026-01-01T00:00:00Z"));
            long expected = ts.getTime();

            Map<String, Object> inner = new java.util.LinkedHashMap<>();
            inner.put("seen_at", ts);
            Map<String, Object> outer = new java.util.LinkedHashMap<>();
            outer.put("metadata", inner);

            JsonObject parsed = JsonUtil.parse(JsonUtil.toJson(outer));
            JsonObject got = JsonUtil.getJsonObject(parsed, "metadata");
            assertNotNull(got);
            assertEquals(expected, JsonUtil.getLong(got, "seen_at"));
        }

        @Test void timestampInsideListIsConvertedPerElement() {
            Timestamp t1 = Timestamp.from(java.time.Instant.parse("2026-01-01T00:00:00Z"));
            Timestamp t2 = Timestamp.from(java.time.Instant.parse("2026-02-01T00:00:00Z"));

            Map<String, Object> map = new java.util.HashMap<>();
            map.put("checkpoints", java.util.Arrays.asList(t1, t2));

            String json = JsonUtil.toJson(map);
            JsonArray arr = JsonUtil.getJsonArray(JsonUtil.parse(json), "checkpoints");
            assertNotNull(arr);
            assertEquals(2, arr.size());
            assertEquals(t1.getTime(), arr.get(0).getAsLong());
            assertEquals(t2.getTime(), arr.get(1).getAsLong());
        }

        @Test void objectArrayIsConvertedRecursively() {
            Timestamp ts = Timestamp.from(java.time.Instant.parse("2026-03-01T00:00:00Z"));

            Map<String, Object> map = new java.util.HashMap<>();
            map.put("mixed", new Object[] { ts, "hello", 7 });

            JsonArray arr = JsonUtil.getJsonArray(JsonUtil.parse(JsonUtil.toJson(map)), "mixed");
            assertNotNull(arr);
            assertEquals(3, arr.size());
            assertEquals(ts.getTime(), arr.get(0).getAsLong());
            assertEquals("hello", arr.get(1).getAsString());
            assertEquals(7, arr.get(2).getAsInt());
        }

        @Test void deeplyNestedMapAndListAreFullyTraversed() {
            Timestamp ts = Timestamp.from(java.time.Instant.parse("2026-04-15T12:00:00Z"));
            long expected = ts.getTime();

            Map<String, Object> inner = new java.util.HashMap<>();
            inner.put("at", ts);
            inner.put("status", Status.ACTIVE);

            Map<String, Object> outer = new java.util.HashMap<>();
            outer.put("events", java.util.Arrays.asList(inner, java.util.Arrays.asList(ts, "x")));

            JsonObject parsed = JsonUtil.parse(JsonUtil.toJson(outer));
            JsonArray events = JsonUtil.getJsonArray(parsed, "events");
            assertNotNull(events);

            JsonObject first = events.get(0).getAsJsonObject();
            assertEquals(expected, JsonUtil.getLong(first, "at"));
            assertEquals("active", JsonUtil.getString(first, "status"));

            JsonArray second = events.get(1).getAsJsonArray();
            assertEquals(expected, second.get(0).getAsLong());
            assertEquals("x", second.get(1).getAsString());
        }

        @Test void numericTypesAreStillEmittedAsJsonNumbers() {
            Map<String, Object> map = new java.util.LinkedHashMap<>();
            map.put("int_val", 42);
            map.put("long_val", 1234567890123L);
            map.put("double_val", 3.14);
            map.put("decimal_val", new BigDecimal("19.99"));
            map.put("bool_val", true);

            JsonObject parsed = JsonUtil.parse(JsonUtil.toJson(map));
            assertEquals(42, JsonUtil.getInteger(parsed, "int_val"));
            assertEquals(1234567890123L, JsonUtil.getLong(parsed, "long_val"));
            assertEquals(3.14, JsonUtil.getDouble(parsed, "double_val"), 0.0001);
            assertEquals(new BigDecimal("19.99"), JsonUtil.getBigDecimal(parsed, "decimal_val"));
            assertTrue(JsonUtil.getBoolean(parsed, "bool_val"));
        }
    }

    // ========== Edge Cases ==========
    @Nested
    @DisplayName("Edge Cases")
    class EdgeCases {

        @Test void whitespaceVariations() {
            assertEquals("value", JsonUtil.getString(JsonUtil.parse("{\"key\":\"value\"}"), "key"));
            assertEquals("value", JsonUtil.getString(JsonUtil.parse("{ \"key\" : \"value\" }"), "key"));
            assertEquals("value", JsonUtil.getString(JsonUtil.parse("{\n  \"key\"\t:\n  \"value\"\n}"), "key"));
        }

        @Test void keysWithSpecialCharacters() {
            JsonObject obj = JsonUtil.parse("{\"my-key\": \"value1\", \"my_key\": \"value2\", \"my.key\": \"value3\"}");
            assertEquals("value1", JsonUtil.getString(obj, "my-key"));
            assertEquals("value2", JsonUtil.getString(obj, "my_key"));
            assertEquals("value3", JsonUtil.getString(obj, "my.key"));
        }

        @Test void veryLongStrings() {
            StringBuilder longValue = new StringBuilder();
            for (int i = 0; i < 10000; i++) longValue.append("x");
            JsonObject obj = JsonUtil.parse("{\"long\": \"" + longValue + "\"}");
            assertEquals(longValue.toString(), JsonUtil.getString(obj, "long"));
        }

        @Test void deeplyNestedStructures() {
            StringBuilder json = new StringBuilder();
            for (int i = 0; i < 10; i++) json.append("{\"level").append(i).append("\": ");
            json.append("\"deep_value\"");
            for (int i = 0; i < 10; i++) json.append("}");
            JsonObject root = JsonUtil.parse(json.toString());
            JsonObject level0 = JsonUtil.getJsonObject(root, "level0");
            assertNotNull(level0);
        }

        @Test void mixedTypesInArray() {
            JsonObject obj = JsonUtil.parse("{\"mixed\": [1, \"two\", true, null, {\"key\": \"value\"}, [1, 2]]}");
            JsonArray arr = JsonUtil.getJsonArray(obj, "mixed");
            assertNotNull(arr);
            assertEquals(6, arr.size());
        }

        @Test void nullAndMissingKeysReturnNull() {
            JsonObject obj = JsonUtil.parse("{\"name\": \"test\"}");
            assertNull(JsonUtil.getString(obj, "missing"));
            assertNull(JsonUtil.getLong(obj, "missing"));
            assertNull(JsonUtil.getInteger(obj, "missing"));
            assertNull(JsonUtil.getBoolean(obj, "missing"));
            assertNull(JsonUtil.getDouble(obj, "missing"));
            assertNull(JsonUtil.getBigDecimal(obj, "missing"));
            assertNull(JsonUtil.getTimestamp(obj, "missing"));
            assertNull(JsonUtil.getJsonObject(obj, "missing"));
            assertNull(JsonUtil.getJsonArray(obj, "missing"));
        }
    }

    // ========== Real-World Scenarios ==========
    @Nested
    @DisplayName("Real-World Scenarios")
    class RealWorldScenarios {

        @Test void complexSubscriptionResponse() {
            String json = "{" +
                "\"subscription\": {" +
                    "\"id\": \"sub_123\"," +
                    "\"status\": \"active\"," +
                    "\"customer_id\": \"cust_456\"," +
                    "\"mrr\": 5000," +
                    "\"created_at\": 1605530769," +
                    "\"deleted\": false," +
                    "\"exchange_rate\": 1.25," +
                    "\"subscription_items\": [" +
                        "{\"item_price_id\": \"price_1\", \"quantity\": 1}," +
                        "{\"item_price_id\": \"price_2\", \"quantity\": 2}" +
                    "]," +
                    "\"shipping_address\": {\"city\": \"NYC\", \"zip\": \"10001\"}," +
                    "\"coupons\": []," +
                    "\"meta_data\": {\"source\": \"api\"}" +
                "}" +
            "}";

            JsonObject root = JsonUtil.parse(json);
            JsonObject sub = JsonUtil.getJsonObject(root, "subscription");
            assertNotNull(sub);

            assertEquals("sub_123", JsonUtil.getString(sub, "id"));
            assertEquals("active", JsonUtil.getString(sub, "status"));
            assertEquals(5000L, JsonUtil.getLong(sub, "mrr"));
            assertEquals(1605530769000L, JsonUtil.getTimestamp(sub, "created_at").getTime());
            assertFalse(JsonUtil.getBoolean(sub, "deleted"));
            assertEquals(1.25, JsonUtil.getDouble(sub, "exchange_rate"), 0.001);

            JsonArray items = JsonUtil.getJsonArray(sub, "subscription_items");
            assertEquals(2, items.size());
            List<String> itemIds = JsonUtil.mapArray(items, obj -> JsonUtil.getString(obj, "item_price_id"));
            assertEquals("price_1", itemIds.get(0));
            assertEquals("price_2", itemIds.get(1));

            JsonObject shipping = JsonUtil.getJsonObject(sub, "shipping_address");
            assertEquals("NYC", JsonUtil.getString(shipping, "city"));

            JsonArray coupons = JsonUtil.getJsonArray(sub, "coupons");
            assertEquals(0, coupons.size());

            Map<String, Object> meta = JsonUtil.parseJsonObjectToMap(JsonUtil.getJsonObject(sub, "meta_data"));
            assertEquals("api", meta.get("source"));
        }

        @Test void transactionListResponse() {
            String json = "{" +
                "\"list\": [{\"transaction\": {" +
                    "\"id\": \"txn_AzZhUGSPAkLskJQo\"," +
                    "\"amount\": 10000," +
                    "\"linked_invoices\": [{\"invoice_id\": \"DemoInv_103\", \"applied_amount\": 10000}]," +
                    "\"linked_refunds\": []" +
                "}}]," +
                "\"next_offset\": null" +
            "}";

            JsonObject root = JsonUtil.parse(json);
            JsonArray list = JsonUtil.getJsonArray(root, "list");
            assertNotNull(list);
            assertEquals(1, list.size());

            JsonObject wrapper = list.get(0).getAsJsonObject();
            JsonObject txn = JsonUtil.getJsonObject(wrapper, "transaction");
            assertEquals("txn_AzZhUGSPAkLskJQo", JsonUtil.getString(txn, "id"));
            assertEquals(10000, JsonUtil.getInteger(txn, "amount"));

            JsonArray invoices = JsonUtil.getJsonArray(txn, "linked_invoices");
            assertEquals(1, invoices.size());

            JsonArray refunds = JsonUtil.getJsonArray(txn, "linked_refunds");
            assertEquals(0, refunds.size());

            assertNull(JsonUtil.getString(root, "next_offset"));
        }
    }
}
