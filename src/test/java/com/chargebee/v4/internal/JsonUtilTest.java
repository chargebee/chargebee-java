package com.chargebee.v4.internal;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JsonUtil Tests")
class JsonUtilTest {

    // ========== getString ==========
    @Nested
    @DisplayName("getString")
    class GetStringTests {

        @Test void simpleString() {
            assertEquals("John", JsonUtil.getString("{\"name\": \"John\"}", "name"));
        }

        @Test void stringWithSpaces() {
            assertEquals("Hello World", JsonUtil.getString("{\"message\": \"Hello World\"}", "message"));
        }

        @Test void escapedQuotes() {
            assertEquals("He said \"Hello\"",
                    JsonUtil.getString("{\"text\": \"He said \\\"Hello\\\"\"}", "text"));
        }

        @Test void escapedBackslashes() {
            assertEquals("C:\\Users\\test",
                    JsonUtil.getString("{\"path\": \"C:\\\\Users\\\\test\"}", "path"));
        }

        @Test void newlinesAndTabs() {
            assertEquals("line1\nline2\ttab",
                    JsonUtil.getString("{\"text\": \"line1\\nline2\\ttab\"}", "text"));
        }

        @Test void missingKey() {
            assertNull(JsonUtil.getString("{\"name\": \"John\"}", "missing"));
        }

        @Test void nullJson() {
            assertNull(JsonUtil.getString(null, "key"));
        }

        @Test void nullKey() {
            assertNull(JsonUtil.getString("{\"name\": \"John\"}", null));
        }

        @Test void emptyString() {
            assertEquals("", JsonUtil.getString("{\"empty\": \"\"}", "empty"));
        }

        @Test void unicodeString() {
            assertEquals("日本語 中文 한국어",
                    JsonUtil.getString("{\"text\": \"日本語 中文 한국어\"}", "text"));
        }

        @Test void specialJsonChars() {
            assertEquals("test: \"value\", more",
                    JsonUtil.getString("{\"text\": \"test: \\\"value\\\", more\"}", "text"));
        }
    }

    // ========== getLong ==========
    @Nested
    @DisplayName("getLong")
    class GetLongTests {

        @Test void positiveLong() {
            assertEquals(12345678901234L, JsonUtil.getLong("{\"id\": 12345678901234}", "id"));
        }

        @Test void negativeLong() {
            assertEquals(-9876543210L, JsonUtil.getLong("{\"value\": -9876543210}", "value"));
        }

        @Test void zero() {
            assertEquals(0L, JsonUtil.getLong("{\"count\": 0}", "count"));
        }

        @Test void missingKey() {
            assertNull(JsonUtil.getLong("{\"id\": 123}", "missing"));
        }

        @Test void nullJson() {
            assertNull(JsonUtil.getLong(null, "key"));
        }

        @Test void nullKey() {
            assertNull(JsonUtil.getLong("{\"a\": 1}", null));
        }

        @Test void epochTimestamp() {
            assertEquals(1605530769L, JsonUtil.getLong("{\"created_at\": 1605530769}", "created_at"));
        }

        @Test void resourceVersion() {
            assertEquals(1605530769000L,
                    JsonUtil.getLong("{\"resource_version\": 1605530769000}", "resource_version"));
        }
    }

    // ========== getInteger ==========
    @Nested
    @DisplayName("getInteger")
    class GetIntegerTests {

        @Test void positiveInteger() {
            assertEquals(42, JsonUtil.getInteger("{\"count\": 42}", "count"));
        }

        @Test void negativeInteger() {
            assertEquals(-10, JsonUtil.getInteger("{\"offset\": -10}", "offset"));
        }

        @Test void missingKey() {
            assertNull(JsonUtil.getInteger("{\"count\": 42}", "missing"));
        }

        @Test void nullJson() {
            assertNull(JsonUtil.getInteger(null, "k"));
        }

        @Test void nullKey() {
            assertNull(JsonUtil.getInteger("{\"a\": 1}", null));
        }
    }

    // ========== getBoolean ==========
    @Nested
    @DisplayName("getBoolean")
    class GetBooleanTests {

        @Test void extractTrue() {
            assertTrue(JsonUtil.getBoolean("{\"active\": true}", "active"));
        }

        @Test void extractFalse() {
            assertFalse(JsonUtil.getBoolean("{\"deleted\": false}", "deleted"));
        }

        @Test void missingKey() {
            assertNull(JsonUtil.getBoolean("{\"active\": true}", "missing"));
        }

        @Test void nullJson() {
            assertNull(JsonUtil.getBoolean(null, "key"));
        }

        @Test void nullKey() {
            assertNull(JsonUtil.getBoolean("{\"a\": true}", null));
        }
    }

    // ========== getDouble ==========
    @Nested
    @DisplayName("getDouble")
    class GetDoubleTests {

        @Test void positiveDouble() {
            assertEquals(99.99, JsonUtil.getDouble("{\"price\": 99.99}", "price"), 0.001);
        }

        @Test void negativeDouble() {
            assertEquals(-123.45, JsonUtil.getDouble("{\"balance\": -123.45}", "balance"), 0.001);
        }

        @Test void exchangeRate() {
            assertEquals(1.0, JsonUtil.getDouble("{\"exchange_rate\": 1.0}", "exchange_rate"), 0.001);
        }

        @Test void integerAsDouble() {
            assertEquals(10000.0, JsonUtil.getDouble("{\"amount\": 10000}", "amount"), 0.001);
        }

        @Test void missingKey() {
            assertNull(JsonUtil.getDouble("{\"a\": 1.0}", "missing"));
        }

        @Test void nullJson() {
            assertNull(JsonUtil.getDouble(null, "k"));
        }

        @Test void nullKey() {
            assertNull(JsonUtil.getDouble("{\"a\": 1.0}", null));
        }
    }

    // ========== getNumber ==========
    @Nested
    @DisplayName("getNumber")
    class GetNumberTests {

        @Test void extractsValue() {
            assertEquals(42.0, JsonUtil.getNumber("{\"n\": 42}", "n").doubleValue(), 0.001);
        }

        @Test void nullJson() {
            assertNull(JsonUtil.getNumber(null, "n"));
        }

        @Test void nullKey() {
            assertNull(JsonUtil.getNumber("{\"n\": 1}", null));
        }
    }

    // ========== getBigDecimal ==========
    @Nested
    @DisplayName("getBigDecimal")
    class GetBigDecimalTests {

        @Test void decimalValue() {
            assertEquals(new BigDecimal("1234.56"),
                    JsonUtil.getBigDecimal("{\"amount\": 1234.56}", "amount"));
        }

        @Test void integerAsBigDecimal() {
            assertEquals(new BigDecimal("10000"),
                    JsonUtil.getBigDecimal("{\"amount\": 10000}", "amount"));
        }

        @Test void missingKey() {
            assertNull(JsonUtil.getBigDecimal("{\"a\": 1}", "missing"));
        }

        @Test void nullJson() {
            assertNull(JsonUtil.getBigDecimal(null, "k"));
        }

        @Test void nullKey() {
            assertNull(JsonUtil.getBigDecimal("{\"a\": 1}", null));
        }
    }

    // ========== getTimestamp ==========
    @Nested
    @DisplayName("getTimestamp")
    class GetTimestampTests {

        @Test void epochSecondsToTimestamp() {
            Timestamp result = JsonUtil.getTimestamp("{\"created_at\": 1605530769}", "created_at");
            assertNotNull(result);
            assertEquals(1605530769000L, result.getTime());
        }

        @Test void missingKey() {
            assertNull(JsonUtil.getTimestamp("{\"updated_at\": 1605530769}", "created_at"));
        }
    }

    // ========== getObject ==========
    @Nested
    @DisplayName("getObject")
    class GetObjectTests {

        @Test void simpleNestedObject() {
            String result = JsonUtil.getObject(
                    "{\"customer\": {\"id\": \"cust_123\", \"name\": \"John\"}}", "customer");
            assertNotNull(result);
            assertTrue(result.contains("\"id\": \"cust_123\""));
        }

        @Test void deeplyNestedObject() {
            String dataObj = JsonUtil.getObject(
                    "{\"data\": {\"customer\": {\"address\": {\"city\": \"NYC\"}}}}", "data");
            assertNotNull(dataObj);
            String addressObj = JsonUtil.getObject(
                    JsonUtil.getObject(dataObj, "customer"), "address");
            assertEquals("NYC", JsonUtil.getString(addressObj, "city"));
        }

        @Test void objectWithNestedArrays() {
            String result = JsonUtil.getObject(
                    "{\"txn\": {\"id\": \"t1\", \"invoices\": [{\"id\": \"i1\"}]}}", "txn");
            assertNotNull(result);
            assertTrue(result.contains("invoices"));
        }

        @Test void objectWithEscapedStrings() {
            String result = JsonUtil.getObject(
                    "{\"data\": {\"text\": \"Hello \\\"World\\\"\"}}", "data");
            assertNotNull(result);
            assertTrue(result.contains("Hello \\\"World\\\""));
        }

        @Test void objectWithBackslashInsideValue() {
            String obj = JsonUtil.getObject(
                    "{\"obj\": {\"path\": \"C:\\\\Users\\\\test\"}}", "obj");
            assertNotNull(obj);
            assertTrue(obj.contains("C:\\\\Users"));
        }

        @Test void missingKey() {
            assertNull(JsonUtil.getObject("{\"customer\": {\"id\": \"123\"}}", "missing"));
        }

        @Test void valueIsNotObject() {
            assertNull(JsonUtil.getObject("{\"name\": \"John\"}", "name"));
        }

        @Test void emptyObject() {
            assertEquals("{}", JsonUtil.getObject("{\"metadata\": {}}", "metadata"));
        }

        @Test void nullJson() {
            assertNull(JsonUtil.getObject(null, "k"));
        }

        @Test void nullKey() {
            assertNull(JsonUtil.getObject("{\"a\": {}}", null));
        }

        @Test void unterminatedObject() {
            assertNull(JsonUtil.getObject("{\"k\": {\"a\": 1", "k"));
        }

        @Test void valueIsScalar() {
            assertNull(JsonUtil.getObject("{\"k\": 123}", "k"));
        }
    }

    // ========== getArray ==========
    @Nested
    @DisplayName("getArray")
    class GetArrayTests {

        @Test void simpleArrayOfStrings() {
            assertEquals("[\"a\", \"b\", \"c\"]",
                    JsonUtil.getArray("{\"tags\": [\"a\", \"b\", \"c\"]}", "tags"));
        }

        @Test void emptyArray() {
            assertEquals("[]", JsonUtil.getArray("{\"items\": []}", "items"));
        }

        @Test void arrayOfObjects() {
            String result = JsonUtil.getArray("{\"list\": [{\"id\": 1}, {\"id\": 2}]}", "list");
            assertNotNull(result);
            assertTrue(result.contains("{\"id\": 1}"));
        }

        @Test void arrayWithNestedArrays() {
            String json = "{\"list\": [{\"txn\": {\"inv\": [{\"id\": \"i1\"}], \"ref\": []}}]}";
            String result = JsonUtil.getArray(json, "list");
            assertNotNull(result);
            assertTrue(result.contains("inv"));
            assertTrue(result.contains("ref"));
        }

        @Test void complexNestedStructure() {
            String json = "{\"list\": [{\"transaction\": {"
                    + "\"id\": \"txn_1\","
                    + "\"linked_invoices\": [{\"invoice_id\": \"inv_1\"}],"
                    + "\"linked_refunds\": [],"
                    + "\"details\": \"{\\\"card\\\":{\\\"iin\\\":\\\"555\\\"}}\""
                    + "}}]}";
            String result = JsonUtil.getArray(json, "list");
            assertNotNull(result);
            assertTrue(result.contains("txn_1"));
            assertTrue(result.contains("linked_refunds"));
        }

        @Test void arrayWithDeeplyNestedObjects() {
            String json = "{\"data\": [{\"l1\": {\"l2\": {\"l3\": [{\"v\": 1}]}}}]}";
            String result = JsonUtil.getArray(json, "data");
            assertNotNull(result);
            assertTrue(result.contains("l3"));
        }

        @Test void escapedBracketsInStrings() {
            String result = JsonUtil.getArray(
                    "{\"msg\": [\"text [brackets]\", \"another [one]\"]}", "msg");
            assertNotNull(result);
            assertTrue(result.contains("[brackets]"));
        }

        @Test void jsonStringFieldContainingBrackets() {
            String result = JsonUtil.getArray(
                    "{\"list\": [{\"d\": \"{\\\"arr\\\":[1,2,3]}\"}]}", "list");
            assertNotNull(result);
        }

        @Test void arrayWithBackslashInsideValue() {
            assertNotNull(JsonUtil.getArray("{\"arr\": [\"a\\\\b\", \"c\\\"d\"]}", "arr"));
        }

        @Test void missingKey() {
            assertNull(JsonUtil.getArray("{\"data\": [1, 2, 3]}", "missing"));
        }

        @Test void valueIsNotArray() {
            assertNull(JsonUtil.getArray("{\"name\": \"John\"}", "name"));
        }

        @Test void multipleArrays() {
            String json = "{\"a\": [1, 2], \"b\": [3, 4], \"c\": [5, 6]}";
            assertEquals("[1, 2]", JsonUtil.getArray(json, "a"));
            assertEquals("[3, 4]", JsonUtil.getArray(json, "b"));
            assertEquals("[5, 6]", JsonUtil.getArray(json, "c"));
        }

        @Test void arrayWithNulls() {
            String result = JsonUtil.getArray("{\"v\": [null, \"a\", null]}", "v");
            assertNotNull(result);
            assertTrue(result.contains("null"));
        }

        @Test void nullJson() {
            assertNull(JsonUtil.getArray(null, "k"));
        }

        @Test void nullKey() {
            assertNull(JsonUtil.getArray("{\"a\": []}", null));
        }

        @Test void unterminatedArray() {
            assertNull(JsonUtil.getArray("{\"k\": [1, 2", "k"));
        }

        @Test void valueIsScalar() {
            assertNull(JsonUtil.getArray("{\"k\": 123}", "k"));
        }
    }

    // ========== hasValue ==========
    @Nested
    @DisplayName("hasValue")
    class HasValueTests {

        @Test void existingNonNullValue() {
            assertTrue(JsonUtil.hasValue("{\"name\": \"John\", \"age\": 30}", "name"));
            assertTrue(JsonUtil.hasValue("{\"name\": \"John\", \"age\": 30}", "age"));
        }

        @Test void nullValue() {
            assertFalse(JsonUtil.hasValue("{\"name\": null}", "name"));
        }

        @Test void missingKey() {
            assertFalse(JsonUtil.hasValue("{\"name\": \"John\"}", "missing"));
        }

        @Test void nullJson() {
            assertFalse(JsonUtil.hasValue(null, "k"));
        }

        @Test void nullKey() {
            assertFalse(JsonUtil.hasValue("{\"a\": 1}", null));
        }
    }

    // ========== parseObjectArray ==========
    @Nested
    @DisplayName("parseObjectArray")
    class ParseObjectArrayTests {

        @Test void simpleObjects() {
            List<String> objects = JsonUtil.parseObjectArray("[{\"id\": 1}, {\"id\": 2}, {\"id\": 3}]");
            assertEquals(3, objects.size());
            assertTrue(objects.get(0).contains("\"id\": 1"));
        }

        @Test void complexObjects() {
            List<String> objects = JsonUtil.parseObjectArray(
                    "[{\"txn\": {\"id\": \"t1\", \"items\": [1, 2]}}, {\"txn\": {\"id\": \"t2\"}}]");
            assertEquals(2, objects.size());
            assertTrue(objects.get(0).contains("t1"));
        }

        @Test void nullInput() {
            assertTrue(JsonUtil.parseObjectArray(null).isEmpty());
        }

        @Test void emptyArray() {
            assertTrue(JsonUtil.parseObjectArray("[]").isEmpty());
        }

        @Test void nonArrayInput() {
            assertTrue(JsonUtil.parseObjectArray("{\"id\": 1}").isEmpty());
        }

        @Test void objectsWithEscapedStrings() {
            List<String> objects = JsonUtil.parseObjectArray("[{\"text\": \"Hello \\\"World\\\"\"}]");
            assertEquals(1, objects.size());
            assertTrue(objects.get(0).contains("Hello \\\"World\\\""));
        }
    }

    // ========== parseArrayOf* ==========
    @Nested
    @DisplayName("parseArrayOf*")
    class ParseArrayOfTests {

        @Test void arrayOfStrings() {
            List<String> result = JsonUtil.parseArrayOfString("[\"a\", \"b\", \"c\"]");
            assertEquals(3, result.size());
            assertEquals("a", result.get(0));
        }

        @Test void arrayOfIntegers() {
            List<Integer> result = JsonUtil.parseArrayOfInteger("[1, 2, 3, -4, 0]");
            assertEquals(5, result.size());
            assertEquals(-4, result.get(3));
        }

        @Test void arrayOfLongs() {
            List<Long> result = JsonUtil.parseArrayOfLong("[1605530769000, 1605530770000]");
            assertEquals(2, result.size());
            assertEquals(1605530769000L, result.get(0));
        }

        @Test void arrayOfBooleans() {
            List<Boolean> result = JsonUtil.parseArrayOfBoolean("[true, false, true]");
            assertEquals(3, result.size());
            assertTrue(result.get(0));
            assertFalse(result.get(1));
        }

        @Test void arrayOfDoubles() {
            List<Double> result = JsonUtil.parseArrayOfDouble("[1.5, 2.7, 3.14]");
            assertEquals(3, result.size());
            assertEquals(1.5, result.get(0), 0.001);
        }

        @Test void arrayOfBigDecimal() {
            List<BigDecimal> result = JsonUtil.parseArrayOfBigDecimal("[123.45, 678.90]");
            assertEquals(2, result.size());
        }

        @Test void emptyArrays() {
            assertTrue(JsonUtil.parseArrayOfString("[]").isEmpty());
            assertTrue(JsonUtil.parseArrayOfInteger("[]").isEmpty());
            assertTrue(JsonUtil.parseArrayOfLong("[]").isEmpty());
            assertTrue(JsonUtil.parseArrayOfBoolean("[]").isEmpty());
            assertTrue(JsonUtil.parseArrayOfDouble("[]").isEmpty());
            assertTrue(JsonUtil.parseArrayOfBigDecimal("[]").isEmpty());
        }

        @Test void nullInputs() {
            assertTrue(JsonUtil.parseArrayOfString(null).isEmpty());
            assertTrue(JsonUtil.parseArrayOfInteger(null).isEmpty());
            assertTrue(JsonUtil.parseArrayOfLong(null).isEmpty());
            assertTrue(JsonUtil.parseArrayOfBoolean(null).isEmpty());
            assertTrue(JsonUtil.parseArrayOfDouble(null).isEmpty());
            assertTrue(JsonUtil.parseArrayOfBigDecimal(null).isEmpty());
        }
    }

    // ========== parseJsonObjectToMap ==========
    @Nested
    @DisplayName("parseJsonObjectToMap")
    class ParseJsonObjectToMapTests {

        @Test void simpleObject() {
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(
                    "{\"name\": \"John\", \"age\": 30, \"active\": true}");
            assertEquals("John", map.get("name"));
            assertEquals(30L, map.get("age"));
            assertEquals(true, map.get("active"));
        }

        @Test void nestedObjects() {
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap("{\"user\": {\"name\": \"John\"}}");
            assertTrue(map.get("user") instanceof String);
            assertTrue(((String) map.get("user")).contains("name"));
        }

        @Test void arraysInMap() {
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap("{\"tags\": [\"a\", \"b\"]}");
            assertTrue(((String) map.get("tags")).contains("\"a\""));
        }

        @Test void emptyObject() {
            assertTrue(JsonUtil.parseJsonObjectToMap("{}").isEmpty());
        }

        @Test void nullInput() {
            assertTrue(JsonUtil.parseJsonObjectToMap(null).isEmpty());
        }

        @Test void emptyStringInput() {
            assertTrue(JsonUtil.parseJsonObjectToMap("").isEmpty());
        }

        @Test void whitespaceOnlyContent() {
            assertTrue(JsonUtil.parseJsonObjectToMap("{   }").isEmpty());
        }

        @Test void nullValues() {
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap("{\"value\": null}");
            assertTrue(map.containsKey("value"));
            assertNull(map.get("value"));
        }

        @Test void doubleValues() {
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap("{\"price\": 99.99}");
            assertEquals(99.99, (Double) map.get("price"), 0.001);
        }

        @Test void booleanFalse() {
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap("{\"flag\": false}");
            assertEquals(Boolean.FALSE, map.get("flag"));
        }

        @Test void negativeNumber() {
            assertEquals(-42L, JsonUtil.parseJsonObjectToMap("{\"val\": -42}").get("val"));
        }

        @Test void escapedStringValue() {
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(
                    "{\"text\": \"hello\\\\world\\\"quoted\\\"\"}");
            assertEquals("hello\\world\"quoted\"", map.get("text"));
        }

        @Test void escapedKey() {
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap("{\"k\\\"ey\": \"val\"}");
            assertEquals("val", map.get("k\"ey"));
        }

        @Test void nestedObjectWithEscapedStrings() {
            assertNotNull(JsonUtil.parseJsonObjectToMap(
                    "{\"obj\": {\"k\": \"v\\\\x\\\"y\"}}").get("obj"));
        }

        @Test void nestedArrayWithEscapedStrings() {
            assertNotNull(JsonUtil.parseJsonObjectToMap(
                    "{\"arr\": [\"a\\\\b\", \"c\\\"d\"]}").get("arr"));
        }

        @Test void nestedObjBackslashInString() {
            assertNotNull(JsonUtil.parseJsonObjectToMap(
                    "{\"obj\": {\"path\": \"C:\\\\Users\"}}").get("obj"));
        }

        @Test void nestedArrayBackslashInString() {
            assertNotNull(JsonUtil.parseJsonObjectToMap(
                    "{\"arr\": [\"path\\\\to\\\"file\"]}").get("arr"));
        }

        @Test void deeplyNestedObject() {
            String nested = (String) JsonUtil.parseJsonObjectToMap(
                    "{\"a\": {\"b\": {\"c\": 1}}}").get("a");
            assertNotNull(nested);
            assertTrue(nested.contains("\"b\""));
        }

        @Test void deeplyNestedArray() {
            assertNotNull(JsonUtil.parseJsonObjectToMap("{\"a\": [[1, 2], [3, 4]]}").get("a"));
        }

        @Test void scientificNotation() {
            assertEquals(150.0,
                    (Double) JsonUtil.parseJsonObjectToMap("{\"val\": 1.5e2}").get("val"), 0.001);
        }

        @Test void scientificE() {
            assertEquals(2500.0,
                    (Double) JsonUtil.parseJsonObjectToMap("{\"v\": 2.5E3}").get("v"), 0.001);
        }

        @Test void numberPlusSign() {
            assertEquals(100.0,
                    (Double) JsonUtil.parseJsonObjectToMap("{\"v\": 1e+2}").get("v"), 0.001);
        }

        @Test void noBraces() {
            assertNotNull(JsonUtil.parseJsonObjectToMap("\"key\": \"val\""));
        }

        @Test void unknownValueType() {
            assertTrue(JsonUtil.parseJsonObjectToMap("{\"k\": undefined}").containsKey("k"));
        }

        @Test void leadingWhitespaceKeys() {
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap("{ \"a\" : 1 , \"b\" : 2 }");
            assertEquals(1L, map.get("a"));
            assertEquals(2L, map.get("b"));
        }

        @Test void customFieldsAtRootOnly() {
            String json = "{"
                    + "\"id\": \"inv_1\","
                    + "\"billing_address\": {\"cf_addr\": \"nested\"},"
                    + "\"line_items\": [{\"cf_line\": \"also_nested\"}],"
                    + "\"cf_root\": \"visible\""
                    + "}";
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(json);
            assertEquals("visible", map.get("cf_root"));
            assertFalse(map.containsKey("cf_addr"));
            assertFalse(map.containsKey("cf_line"));
        }

        @Test void customFieldVariousTypes() {
            String json = "{\"cf_str\": \"text\", \"cf_num\": 5, \"cf_dec\": 99.5,"
                    + "\"cf_bool\": true, \"cf_nil\": null}";
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(json);
            assertEquals("text", map.get("cf_str"));
            assertEquals(5L, map.get("cf_num"));
            assertEquals(99.5, (Double) map.get("cf_dec"), 0.001);
            assertEquals(true, map.get("cf_bool"));
            assertNull(map.get("cf_nil"));
        }
    }

    // ========== toJson ==========
    @Nested
    @DisplayName("toJson")
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
            assertEquals("[\"a\",\"b\",\"c\"]",
                    JsonUtil.toJson(java.util.Arrays.asList("a", "b", "c")));
        }

        @Test void emptyMap() {
            assertEquals("{}", JsonUtil.toJson(new java.util.HashMap<>()));
        }

        @Test void emptyList() {
            assertEquals("[]", JsonUtil.toJson(new java.util.ArrayList<>()));
        }

        @Test void nullMap() {
            assertEquals("{}", JsonUtil.toJson((Map<String, Object>) null));
        }

        @Test void nullList() {
            assertEquals("[]", JsonUtil.toJson((List<?>) null));
        }

        @Test void nullValues() {
            Map<String, Object> m = new java.util.HashMap<>();
            m.put("value", null);
            assertTrue(JsonUtil.toJson(m).contains("\"value\":null"));
        }

        @Test void booleanValue() {
            Map<String, Object> m = new java.util.HashMap<>();
            m.put("flag", true);
            assertTrue(JsonUtil.toJson(m).contains("true"));
        }

        @Test void listValue() {
            Map<String, Object> m = new java.util.LinkedHashMap<>();
            m.put("items", java.util.Arrays.asList("a", "b"));
            assertTrue(JsonUtil.toJson(m).contains("[\"a\",\"b\"]"));
        }

        @Test void nestedMapValue() {
            Map<String, Object> inner = new java.util.HashMap<>();
            inner.put("id", 123);
            Map<String, Object> outer = new java.util.HashMap<>();
            outer.put("data", inner);
            String json = JsonUtil.toJson(outer);
            assertTrue(json.contains("\"data\":{"));
            assertTrue(json.contains("\"id\":123"));
        }

        @Test void unknownType() {
            Map<String, Object> m = new java.util.HashMap<>();
            m.put("ts", java.sql.Timestamp.valueOf("2020-01-01 00:00:00"));
            assertTrue(JsonUtil.toJson(m).contains("2020"));
        }

        @Test @SuppressWarnings("all")
        void nullKeyInMap() {
            Map<String, Object> m = new java.util.HashMap<>();
            m.put(null, "val");
            assertNotNull(JsonUtil.toJson(m));
        }

        @Test void escapeQuotes() {
            Map<String, Object> m = new java.util.HashMap<>();
            m.put("t", "Hello \"World\"\nNew line");
            String json = JsonUtil.toJson(m);
            assertTrue(json.contains("\\\"World\\\""));
            assertTrue(json.contains("\\n"));
        }

        @Test void escapeBackslash() {
            Map<String, Object> m = new java.util.HashMap<>();
            m.put("p", "a\\b");
            assertTrue(JsonUtil.toJson(m).contains("a\\\\b"));
        }

        @Test void escapeTab() {
            Map<String, Object> m = new java.util.HashMap<>();
            m.put("t", "a\tb");
            assertTrue(JsonUtil.toJson(m).contains("\\t"));
        }

        @Test void escapeCarriageReturn() {
            Map<String, Object> m = new java.util.HashMap<>();
            m.put("r", "a\rb");
            assertTrue(JsonUtil.toJson(m).contains("\\r"));
        }

        @Test void escapeBackspace() {
            Map<String, Object> m = new java.util.HashMap<>();
            m.put("b", "a\bb");
            assertTrue(JsonUtil.toJson(m).contains("\\b"));
        }

        @Test void escapeFormFeed() {
            Map<String, Object> m = new java.util.HashMap<>();
            m.put("f", "a\fb");
            assertTrue(JsonUtil.toJson(m).contains("\\f"));
        }

        @Test void escapeControlChar() {
            Map<String, Object> m = new java.util.HashMap<>();
            m.put("c", "a\u0001b");
            assertTrue(JsonUtil.toJson(m).contains("\\u0001"));
        }
    }

    // ========== Top-Level Key Resolution ==========
    @Nested
    @DisplayName("Top-Level Key Resolution (duplicate keys across nesting)")
    class TopLevelKeyResolutionTests {

        @Test void getString_shadowedByNestedObject() {
            assertEquals("outer",
                    JsonUtil.getString("{\"child\": {\"id\": \"inner\"}, \"id\": \"outer\"}", "id"));
        }

        @Test void getString_shadowedByNestedArray() {
            assertEquals("top",
                    JsonUtil.getString("{\"items\": [{\"id\": \"a\"}], \"id\": \"top\"}", "id"));
        }

        @Test void getString_topLevelFirst() {
            assertEquals("top",
                    JsonUtil.getString("{\"id\": \"top\", \"child\": {\"id\": \"inner\"}}", "id"));
        }

        @Test void getString_deeplyNested() {
            assertEquals("top", JsonUtil.getString(
                    "{\"l1\": {\"l2\": {\"status\": \"deep\"}}, \"status\": \"top\"}", "status"));
        }

        @Test void getString_missingAtTopLevel() {
            assertNull(JsonUtil.getString("{\"child\": {\"secret\": \"hidden\"}}", "secret"));
        }

        @Test void getString_keyInValue() {
            assertEquals("real",
                    JsonUtil.getString("{\"label\": \"id is here\", \"id\": \"real\"}", "id"));
        }

        @Test void getString_escapedValueContainingBraces() {
            assertEquals("top", JsonUtil.getString(
                    "{\"data\": \"{\\\"id\\\": \\\"inner\\\"}\", \"id\": \"top\"}", "id"));
        }

        @Test void getLong_shadowedByNested() {
            assertEquals(Long.valueOf(42),
                    JsonUtil.getLong("{\"nested\": {\"amount\": 999}, \"amount\": 42}", "amount"));
        }

        @Test void getLong_shadowedByMultipleArrayElements() {
            assertEquals(Long.valueOf(500), JsonUtil.getLong(
                    "{\"items\": [{\"amount\": 100},{\"amount\": 200}], \"amount\": 500}", "amount"));
        }

        @Test void getLong_missingAtTopLevel() {
            assertNull(JsonUtil.getLong("{\"child\": {\"total\": 100}}", "total"));
        }

        @Test void getInteger_shadowedByNested() {
            assertEquals(Integer.valueOf(1),
                    JsonUtil.getInteger("{\"d\": {\"qty\": 5}, \"qty\": 1}", "qty"));
        }

        @Test void getBoolean_shadowedByNested() {
            assertEquals(false,
                    JsonUtil.getBoolean("{\"n\": {\"active\": true}, \"active\": false}", "active"));
        }

        @Test void getBoolean_deeplyNested() {
            assertEquals(false, JsonUtil.getBoolean(
                    "{\"a\": {\"b\": {\"c\": {\"del\": true}}}, \"del\": false}", "del"));
        }

        @Test void getDouble_shadowedByNested() {
            assertEquals(3.75,
                    JsonUtil.getDouble("{\"d\": {\"rate\": 1.5}, \"rate\": 3.75}", "rate"), 0.001);
        }

        @Test void getBigDecimal_shadowedByNested() {
            assertEquals(new BigDecimal("1.25"), JsonUtil.getBigDecimal(
                    "{\"d\": {\"exchange_rate\": 0.85}, \"exchange_rate\": 1.25}", "exchange_rate"));
        }

        @Test void getTimestamp_shadowedByNested() {
            Timestamp ts = JsonUtil.getTimestamp(
                    "{\"items\": [{\"date\": 1000000}], \"date\": 1605530769}", "date");
            assertNotNull(ts);
            assertEquals(1605530769000L, ts.getTime());
        }

        @Test void getObject_shadowedByNested() {
            String obj = JsonUtil.getObject(
                    "{\"p\": {\"addr\": {\"city\": \"nested\"}}, \"addr\": {\"city\": \"top\"}}", "addr");
            assertEquals("top", JsonUtil.getString(obj, "city"));
        }

        @Test void getObject_missingAtTopLevel() {
            assertNull(JsonUtil.getObject("{\"w\": {\"inner\": {\"v\": 1}}}", "inner"));
        }

        @Test void getObject_escapedCharsBeforeObject() {
            String obj = JsonUtil.getObject(
                    "{\"text\": \"a\\\\b\\\"c\", \"obj\": {\"k\": 1}}", "obj");
            assertNotNull(obj);
            assertEquals(Long.valueOf(1), JsonUtil.getLong(obj, "k"));
        }

        @Test void getObject_afterStringLookingLikeObject() {
            String obj = JsonUtil.getObject(
                    "{\"raw\": \"not an object\", \"obj\": {\"a\": 1}}", "obj");
            assertNotNull(obj);
        }

        @Test void getArray_shadowedByNested() {
            String arr = JsonUtil.getArray(
                    "{\"w\": {\"tags\": [\"i1\"]}, \"tags\": [\"t1\", \"t2\", \"t3\"]}", "tags");
            List<String> tags = JsonUtil.parseArrayOfString(arr);
            assertEquals(3, tags.size());
            assertEquals("t1", tags.get(0));
        }

        @Test void getArray_missingAtTopLevel() {
            assertNull(JsonUtil.getArray("{\"w\": {\"items\": [1, 2]}}", "items"));
        }

        @Test void getArray_escapedCharsBeforeArray() {
            assertNotNull(JsonUtil.getArray(
                    "{\"text\": \"a\\\\b\\\"c\", \"arr\": [1, 2]}", "arr"));
        }

        @Test void hasValue_topLevelNull() {
            assertFalse(JsonUtil.hasValue(
                    "{\"inner\": {\"name\": \"hidden\"}, \"name\": null}", "name"));
        }

        @Test void hasValue_nestedNull() {
            assertTrue(JsonUtil.hasValue(
                    "{\"inner\": {\"name\": null}, \"name\": \"visible\"}", "name"));
        }

        @Test void hasValue_missingAtTopLevel() {
            assertFalse(JsonUtil.hasValue("{\"inner\": {\"key\": \"val\"}}", "key"));
        }

        @Test void hasValue_topLevelArray() {
            assertTrue(JsonUtil.hasValue(
                    "{\"tags\": [1, 2, 3], \"name\": \"test\"}", "tags"));
        }

        @Test void hasValue_topLevelObject() {
            assertTrue(JsonUtil.hasValue(
                    "{\"billing_address\": {\"city\": \"SF\"}, \"id\": \"inv_1\"}", "billing_address"));
        }

        @Test void hasValue_topLevelEmptyArray() {
            assertTrue(JsonUtil.hasValue("{\"items\": [], \"id\": \"x\"}", "items"));
        }

        @Test void hasValue_topLevelEmptyObject() {
            assertTrue(JsonUtil.hasValue("{\"meta\": {}, \"id\": \"x\"}", "meta"));
        }

        @Test void stripNested_escapedCharInNestedString() {
            assertEquals("top", JsonUtil.getString(
                    "{\"child\": {\"k\": \"a\\\\b\"}, \"id\": \"top\"}", "id"));
        }

        @Test void allScalarTypes_invoiceLikeStructure() {
            String json = "{"
                    + "\"line_items\": [{\"id\": \"li_1\", \"amount\": 500, \"tax\": 50,"
                    + "  \"is_taxed\": true, \"description\": \"item\", \"exchange_rate\": 0.85,"
                    + "  \"date_from\": 1000000}],"
                    + "\"billing_address\": {\"first_name\": \"nested\"},"
                    + "\"id\": \"inv_top\", \"amount\": 2000, \"tax\": 80,"
                    + "\"is_taxed\": false, \"description\": \"top desc\","
                    + "\"exchange_rate\": 1.25, \"date_from\": 9999999"
                    + "}";

            assertEquals("inv_top", JsonUtil.getString(json, "id"));
            assertEquals(Long.valueOf(2000), JsonUtil.getLong(json, "amount"));
            assertEquals(Long.valueOf(80), JsonUtil.getLong(json, "tax"));
            assertEquals(false, JsonUtil.getBoolean(json, "is_taxed"));
            assertEquals("top desc", JsonUtil.getString(json, "description"));
            assertEquals(new BigDecimal("1.25"), JsonUtil.getBigDecimal(json, "exchange_rate"));
            assertEquals(9999999000L, JsonUtil.getTimestamp(json, "date_from").getTime());
            assertEquals("nested",
                    JsonUtil.getString(JsonUtil.getObject(json, "billing_address"), "first_name"));
            assertEquals("li_1",
                    JsonUtil.getString(JsonUtil.parseObjectArray(
                            JsonUtil.getArray(json, "line_items")).get(0), "id"));
        }
    }

    // ========== Real-World Scenarios ==========
    @Nested
    @DisplayName("Real-World Scenarios")
    class RealWorldScenarios {

        @Test void transactionListResponse() {
            String json = "{\"list\": [{\"transaction\": {"
                    + "\"id\": \"txn_1\", \"customer_id\": \"cust_1\","
                    + "\"amount\": 10000, \"exchange_rate\": 1.0,"
                    + "\"status\": \"success\", \"resource_version\": 1605530769000,"
                    + "\"deleted\": false,"
                    + "\"linked_invoices\": [{\"invoice_id\": \"inv_1\", \"applied_amount\": 10000}],"
                    + "\"linked_refunds\": [],"
                    + "\"payment_method_details\": \"{\\\"card\\\":{\\\"iin\\\":\\\"555\\\"}}\""
                    + "}}], \"next_offset\": null}";

            String listArray = JsonUtil.getArray(json, "list");
            assertNotNull(listArray);
            String txn = JsonUtil.getObject(JsonUtil.parseObjectArray(listArray).get(0), "transaction");
            assertEquals("txn_1", JsonUtil.getString(txn, "id"));
            assertEquals(10000, JsonUtil.getInteger(txn, "amount"));
            assertFalse(JsonUtil.getBoolean(txn, "deleted"));
            assertTrue(JsonUtil.getArray(txn, "linked_invoices").contains("inv_1"));
            assertEquals("[]", JsonUtil.getArray(txn, "linked_refunds"));
        }

        @Test void customerListResponse() {
            String json = "{\"list\": ["
                    + "{\"customer\": {\"id\": \"c1\", \"email\": \"a@b.com\"}},"
                    + "{\"customer\": {\"id\": \"c2\", \"email\": \"x@y.com\"}}"
                    + "], \"next_offset\": \"off_1\"}";

            List<String> items = JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list"));
            assertEquals(2, items.size());
            assertEquals("c1", JsonUtil.getString(JsonUtil.getObject(items.get(0), "customer"), "id"));
            assertEquals("off_1", JsonUtil.getString(json, "next_offset"));
        }

        @Test void subscriptionWithMultipleNestedArrays() {
            String json = "{\"subscription\": {"
                    + "\"id\": \"sub_1\","
                    + "\"subscription_items\": [{\"item_price_id\": \"p1\"}, {\"item_price_id\": \"p2\"}],"
                    + "\"addons\": [{\"id\": \"a1\"}], \"coupons\": [],"
                    + "\"discounts\": [{\"id\": \"d1\", \"apply_till\": [1, 2, 3]}]"
                    + "}}";

            String sub = JsonUtil.getObject(json, "subscription");
            assertEquals(2, JsonUtil.parseObjectArray(
                    JsonUtil.getArray(sub, "subscription_items")).size());
            assertEquals("[]", JsonUtil.getArray(sub, "coupons"));
            assertTrue(JsonUtil.getArray(sub, "discounts").contains("apply_till"));
        }
    }

    // ========== Edge Cases ==========
    @Nested
    @DisplayName("Edge Cases")
    class EdgeCases {

        @Test void whitespaceVariations() {
            assertEquals("value", JsonUtil.getString("{\"key\":\"value\"}", "key"));
            assertEquals("value", JsonUtil.getString("{ \"key\" : \"value\" }", "key"));
            assertEquals("value", JsonUtil.getString("{\n  \"key\"\t:\n  \"value\"\n}", "key"));
        }

        @Test void keysWithSpecialCharacters() {
            String json = "{\"my-key\": \"v1\", \"my_key\": \"v2\", \"my.key\": \"v3\"}";
            assertEquals("v1", JsonUtil.getString(json, "my-key"));
            assertEquals("v2", JsonUtil.getString(json, "my_key"));
            assertEquals("v3", JsonUtil.getString(json, "my.key"));
        }

        @Test void veryLongStrings() {
            String longVal = "x".repeat(10000);
            assertEquals(longVal, JsonUtil.getString("{\"long\": \"" + longVal + "\"}", "long"));
        }

        @Test void deeplyNestedStructures() {
            StringBuilder json = new StringBuilder();
            for (int i = 0; i < 10; i++) json.append("{\"level").append(i).append("\": ");
            json.append("\"deep_value\"");
            for (int i = 0; i < 10; i++) json.append("}");
            assertNotNull(JsonUtil.getObject(json.toString(), "level0"));
        }

        @Test void arrayWithMixedTypes() {
            String arr = JsonUtil.getArray(
                    "{\"mixed\": [1, \"two\", true, null, {\"k\": \"v\"}, [1, 2]]}", "mixed");
            assertNotNull(arr);
            assertTrue(arr.contains("\"two\""));
            assertTrue(arr.contains("null"));
        }

        @Test void colonsInStringValues() {
            assertEquals("https://example.com:8080/path",
                    JsonUtil.getString("{\"url\": \"https://example.com:8080/path\"}", "url"));
        }

        @Test void duplicateKeysAtSameLevel() {
            assertNotNull(JsonUtil.getString("{\"key\": \"first\", \"key\": \"second\"}", "key"));
        }

        @Test void constructorAccessible() {
            assertNotNull(new JsonUtil());
        }
    }
}
