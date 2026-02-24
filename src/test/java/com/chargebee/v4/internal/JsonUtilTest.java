package com.chargebee.v4.internal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for JsonUtil.
 * Inspired by Gson's testing strategies to battle-test our JSON parsing.
 */
@DisplayName("JsonUtil Tests")
class JsonUtilTest {

    // ========== getString Tests ==========
    @Nested
    @DisplayName("getString Tests")
    class GetStringTests {

        @Test
        @DisplayName("should extract simple string value")
        void shouldExtractSimpleString() {
            String json = "{\"name\": \"John\"}";
            assertEquals("John", JsonUtil.getString(json, "name"));
        }

        @Test
        @DisplayName("should extract string with spaces")
        void shouldExtractStringWithSpaces() {
            String json = "{\"message\": \"Hello World\"}";
            assertEquals("Hello World", JsonUtil.getString(json, "message"));
        }

        @Test
        @DisplayName("should handle escaped quotes in string")
        void shouldHandleEscapedQuotes() {
            String json = "{\"text\": \"He said \\\"Hello\\\"\"}";
            assertEquals("He said \"Hello\"", JsonUtil.getString(json, "text"));
        }

        @Test
        @DisplayName("should handle escaped backslashes")
        void shouldHandleEscapedBackslashes() {
            String json = "{\"path\": \"C:\\\\Users\\\\test\"}";
            assertEquals("C:\\Users\\test", JsonUtil.getString(json, "path"));
        }

        @Test
        @DisplayName("should handle newlines and tabs")
        void shouldHandleNewlinesAndTabs() {
            String json = "{\"text\": \"line1\\nline2\\ttab\"}";
            assertEquals("line1\nline2\ttab", JsonUtil.getString(json, "text"));
        }

        @Test
        @DisplayName("should return null for missing key")
        void shouldReturnNullForMissingKey() {
            String json = "{\"name\": \"John\"}";
            assertNull(JsonUtil.getString(json, "missing"));
        }

        @Test
        @DisplayName("should return null for null json")
        void shouldReturnNullForNullJson() {
            assertNull(JsonUtil.getString((String) null, "key"));
        }

        @Test
        @DisplayName("should return null for null key")
        void shouldReturnNullForNullKey() {
            assertNull(JsonUtil.getString("{\"name\": \"John\"}", null));
        }

        @Test
        @DisplayName("should extract empty string")
        void shouldExtractEmptyString() {
            String json = "{\"empty\": \"\"}";
            assertEquals("", JsonUtil.getString(json, "empty"));
        }

        @Test
        @DisplayName("should extract string with unicode characters")
        void shouldExtractUnicodeString() {
            String json = "{\"text\": \"日本語 中文 한국어\"}";
            assertEquals("日本語 中文 한국어", JsonUtil.getString(json, "text"));
        }

        @Test
        @DisplayName("should extract string with special JSON characters")
        void shouldExtractStringWithSpecialChars() {
            String json = "{\"text\": \"test: \\\"value\\\", more\"}";
            assertEquals("test: \"value\", more", JsonUtil.getString(json, "text"));
        }
    }

    // ========== getLong Tests ==========
    @Nested
    @DisplayName("getLong Tests")
    class GetLongTests {

        @Test
        @DisplayName("should extract positive long")
        void shouldExtractPositiveLong() {
            String json = "{\"id\": 12345678901234}";
            assertEquals(12345678901234L, JsonUtil.getLong(json, "id"));
        }

        @Test
        @DisplayName("should extract negative long")
        void shouldExtractNegativeLong() {
            String json = "{\"value\": -9876543210}";
            assertEquals(-9876543210L, JsonUtil.getLong(json, "value"));
        }

        @Test
        @DisplayName("should extract zero")
        void shouldExtractZero() {
            String json = "{\"count\": 0}";
            assertEquals(0L, JsonUtil.getLong(json, "count"));
        }

        @Test
        @DisplayName("should return null for missing key")
        void shouldReturnNullForMissingKey() {
            String json = "{\"id\": 123}";
            assertNull(JsonUtil.getLong(json, "missing"));
        }

        @Test
        @DisplayName("should return null for null json")
        void shouldReturnNullForNullJson() {
            assertNull(JsonUtil.getLong((String) null, "key"));
        }

        @Test
        @DisplayName("should extract epoch timestamp")
        void shouldExtractEpochTimestamp() {
            String json = "{\"created_at\": 1605530769}";
            assertEquals(1605530769L, JsonUtil.getLong(json, "created_at"));
        }

        @Test
        @DisplayName("should extract resource_version (large long)")
        void shouldExtractResourceVersion() {
            String json = "{\"resource_version\": 1605530769000}";
            assertEquals(1605530769000L, JsonUtil.getLong(json, "resource_version"));
        }
    }

    // ========== getInteger Tests ==========
    @Nested
    @DisplayName("getInteger Tests")
    class GetIntegerTests {

        @Test
        @DisplayName("should extract positive integer")
        void shouldExtractPositiveInteger() {
            String json = "{\"count\": 42}";
            assertEquals(42, JsonUtil.getInteger(json, "count"));
        }

        @Test
        @DisplayName("should extract negative integer")
        void shouldExtractNegativeInteger() {
            String json = "{\"offset\": -10}";
            assertEquals(-10, JsonUtil.getInteger(json, "offset"));
        }

        @Test
        @DisplayName("should return null for missing key")
        void shouldReturnNullForMissingKey() {
            String json = "{\"count\": 42}";
            assertNull(JsonUtil.getInteger(json, "missing"));
        }
    }

    // ========== getBoolean Tests ==========
    @Nested
    @DisplayName("getBoolean Tests")
    class GetBooleanTests {

        @Test
        @DisplayName("should extract true")
        void shouldExtractTrue() {
            String json = "{\"active\": true}";
            assertTrue(JsonUtil.getBoolean(json, "active"));
        }

        @Test
        @DisplayName("should extract false")
        void shouldExtractFalse() {
            String json = "{\"deleted\": false}";
            assertFalse(JsonUtil.getBoolean(json, "deleted"));
        }

        @Test
        @DisplayName("should return null for missing key")
        void shouldReturnNullForMissingKey() {
            String json = "{\"active\": true}";
            assertNull(JsonUtil.getBoolean(json, "missing"));
        }

        @Test
        @DisplayName("should return null for null json")
        void shouldReturnNullForNullJson() {
            assertNull(JsonUtil.getBoolean((String) null, "key"));
        }
    }

    // ========== getDouble Tests ==========
    @Nested
    @DisplayName("getDouble Tests")
    class GetDoubleTests {

        @Test
        @DisplayName("should extract positive double")
        void shouldExtractPositiveDouble() {
            String json = "{\"price\": 99.99}";
            assertEquals(99.99, JsonUtil.getDouble(json, "price"), 0.001);
        }

        @Test
        @DisplayName("should extract negative double")
        void shouldExtractNegativeDouble() {
            String json = "{\"balance\": -123.45}";
            assertEquals(-123.45, JsonUtil.getDouble(json, "balance"), 0.001);
        }

        @Test
        @DisplayName("should extract exchange rate")
        void shouldExtractExchangeRate() {
            String json = "{\"exchange_rate\": 1.0}";
            assertEquals(1.0, JsonUtil.getDouble(json, "exchange_rate"), 0.001);
        }

        @Test
        @DisplayName("should extract integer as double")
        void shouldExtractIntegerAsDouble() {
            String json = "{\"amount\": 10000}";
            assertEquals(10000.0, JsonUtil.getDouble(json, "amount"), 0.001);
        }
    }

    // ========== getBigDecimal Tests ==========
    @Nested
    @DisplayName("getBigDecimal Tests")
    class GetBigDecimalTests {

        @Test
        @DisplayName("should extract decimal value")
        void shouldExtractDecimalValue() {
            String json = "{\"amount\": 1234.56}";
            assertEquals(new BigDecimal("1234.56"), JsonUtil.getBigDecimal(json, "amount"));
        }

        @Test
        @DisplayName("should extract integer as BigDecimal")
        void shouldExtractIntegerAsBigDecimal() {
            String json = "{\"amount\": 10000}";
            assertEquals(new BigDecimal("10000"), JsonUtil.getBigDecimal(json, "amount"));
        }
    }

    // ========== getObject Tests ==========
    @Nested
    @DisplayName("getObject Tests")
    class GetObjectTests {

        @Test
        @DisplayName("should extract simple nested object")
        void shouldExtractSimpleNestedObject() {
            String json = "{\"customer\": {\"id\": \"cust_123\", \"name\": \"John\"}}";
            String result = JsonUtil.getObject(json, "customer");
            assertNotNull(result);
            assertEquals("cust_123", JsonUtil.getString(result, "id"));
            assertEquals("John", JsonUtil.getString(result, "name"));
        }

        @Test
        @DisplayName("should extract deeply nested object")
        void shouldExtractDeeplyNestedObject() {
            String json = "{\"data\": {\"customer\": {\"address\": {\"city\": \"NYC\"}}}}";
            String dataObj = JsonUtil.getObject(json, "data");
            assertNotNull(dataObj);
            String customerObj = JsonUtil.getObject(dataObj, "customer");
            assertNotNull(customerObj);
            String addressObj = JsonUtil.getObject(customerObj, "address");
            assertNotNull(addressObj);
            assertEquals("NYC", JsonUtil.getString(addressObj, "city"));
        }

        @Test
        @DisplayName("should handle object with nested arrays")
        void shouldHandleObjectWithNestedArrays() {
            String json = "{\"transaction\": {\"id\": \"txn_123\", \"linked_invoices\": [{\"id\": \"inv_1\"}]}}";
            String result = JsonUtil.getObject(json, "transaction");
            assertNotNull(result);
            assertTrue(result.contains("linked_invoices"));
            assertTrue(result.contains("inv_1"));
        }

        @Test
        @DisplayName("should handle object with escaped strings")
        void shouldHandleObjectWithEscapedStrings() {
            String json = "{\"data\": {\"text\": \"Hello \\\"World\\\"\"}}";
            String result = JsonUtil.getObject(json, "data");
            assertNotNull(result);
            assertTrue(result.contains("Hello \\\"World\\\""));
        }

        @Test
        @DisplayName("should return null for missing key")
        void shouldReturnNullForMissingKey() {
            String json = "{\"customer\": {\"id\": \"123\"}}";
            assertNull(JsonUtil.getObject(json, "missing"));
        }

        @Test
        @DisplayName("should return null when value is not object")
        void shouldReturnNullWhenNotObject() {
            String json = "{\"name\": \"John\"}";
            assertNull(JsonUtil.getObject(json, "name"));
        }

        @Test
        @DisplayName("should extract empty object")
        void shouldExtractEmptyObject() {
            String json = "{\"metadata\": {}}";
            assertEquals("{}", JsonUtil.getObject(json, "metadata"));
        }
    }

    // ========== getArray Tests - THE CRITICAL FIX ==========
    @Nested
    @DisplayName("getArray Tests")
    class GetArrayTests {

        @Test
        @DisplayName("should extract simple array of strings")
        void shouldExtractSimpleArrayOfStrings() {
            String json = "{\"tags\": [\"a\", \"b\", \"c\"]}";
            String result = JsonUtil.getArray(json, "tags");
            assertNotNull(result);
            List<String> parsed = JsonUtil.parseArrayOfString(result);
            assertEquals(3, parsed.size());
            assertEquals("a", parsed.get(0));
            assertEquals("b", parsed.get(1));
            assertEquals("c", parsed.get(2));
        }

        @Test
        @DisplayName("should extract empty array")
        void shouldExtractEmptyArray() {
            String json = "{\"items\": []}";
            assertEquals("[]", JsonUtil.getArray(json, "items"));
        }

        @Test
        @DisplayName("should extract array of objects")
        void shouldExtractArrayOfObjects() {
            String json = "{\"list\": [{\"id\": 1}, {\"id\": 2}]}";
            String result = JsonUtil.getArray(json, "list");
            assertNotNull(result);
            assertTrue(result.startsWith("["));
            assertTrue(result.endsWith("]"));
            List<String> objects = JsonUtil.parseObjectArray(result);
            assertEquals(2, objects.size());
            assertEquals(1, JsonUtil.getInteger(objects.get(0), "id"));
            assertEquals(2, JsonUtil.getInteger(objects.get(1), "id"));
        }

        @Test
        @DisplayName("should handle array with nested arrays - THE BUG FIX TEST")
        void shouldHandleArrayWithNestedArrays() {
            // This is the exact case that was failing before the fix!
            String json = "{\"list\": [{\"transaction\": {\"linked_invoices\": [{\"id\": \"inv_1\"}], \"linked_refunds\": []}}]}";
            String result = JsonUtil.getArray(json, "list");
            assertNotNull(result);
            assertTrue(result.startsWith("["));
            assertTrue(result.endsWith("]"));
            // Verify the entire array is extracted, not truncated at first ]
            assertTrue(result.contains("linked_invoices"));
            assertTrue(result.contains("linked_refunds"));
            assertTrue(result.contains("inv_1"));
        }

        @Test
        @DisplayName("should handle complex nested structure from transaction API")
        void shouldHandleComplexNestedStructure() {
            // Real-world example from the bug report
            String json = "{\"list\": [{\"transaction\": {" +
                "\"id\": \"txn_AzZhUGSPAkLskJQo\"," +
                "\"customer_id\": \"cbdemo_dave\"," +
                "\"amount\": 10000," +
                "\"linked_invoices\": [{" +
                    "\"invoice_id\": \"DemoInv_103\"," +
                    "\"applied_amount\": 10000," +
                    "\"applied_at\": 1605530769" +
                "}]," +
                "\"linked_refunds\": []," +
                "\"payment_method_details\": \"{\\\"card\\\":{\\\"iin\\\":\\\"555555\\\"}}\"" +
                "}}]}";
            
            String result = JsonUtil.getArray(json, "list");
            assertNotNull(result);
            assertTrue(result.startsWith("["));
            assertTrue(result.endsWith("]"));
            
            // Critical: verify we got the complete array, not truncated
            assertTrue(result.contains("txn_AzZhUGSPAkLskJQo"));
            assertTrue(result.contains("linked_invoices"));
            assertTrue(result.contains("DemoInv_103"));
            assertTrue(result.contains("linked_refunds"));
        }

        @Test
        @DisplayName("should handle array with deeply nested objects")
        void shouldHandleArrayWithDeeplyNestedObjects() {
            String json = "{\"data\": [{\"level1\": {\"level2\": {\"level3\": [{\"value\": 1}]}}}]}";
            String result = JsonUtil.getArray(json, "data");
            assertNotNull(result);
            assertTrue(result.contains("level1"));
            assertTrue(result.contains("level2"));
            assertTrue(result.contains("level3"));
            assertTrue(result.contains("value"));
        }

        @Test
        @DisplayName("should handle array with escaped strings containing brackets")
        void shouldHandleArrayWithEscapedBrackets() {
            String json = "{\"messages\": [\"text with [brackets]\", \"another [one]\"]}";
            String result = JsonUtil.getArray(json, "messages");
            assertNotNull(result);
            assertTrue(result.contains("[brackets]"));
            assertTrue(result.contains("[one]"));
        }

        @Test
        @DisplayName("should handle array with JSON string field containing brackets")
        void shouldHandleArrayWithJsonStringField() {
            // payment_method_details contains a JSON string with brackets
            String json = "{\"list\": [{\"details\": \"{\\\"array\\\":[1,2,3]}\"}]}";
            String result = JsonUtil.getArray(json, "list");
            assertNotNull(result);
            assertTrue(result.contains("details"));
        }

        @Test
        @DisplayName("should return null for missing array key")
        void shouldReturnNullForMissingKey() {
            String json = "{\"data\": [1, 2, 3]}";
            assertNull(JsonUtil.getArray(json, "missing"));
        }

        @Test
        @DisplayName("should return null when value is not array")
        void shouldReturnNullWhenNotArray() {
            String json = "{\"name\": \"John\"}";
            assertNull(JsonUtil.getArray(json, "name"));
        }

        @Test
        @DisplayName("should handle multiple arrays in same object")
        void shouldHandleMultipleArrays() {
            String json = "{\"first\": [1, 2], \"second\": [3, 4], \"third\": [5, 6]}";
            List<Integer> first = JsonUtil.parseArrayOfInteger(JsonUtil.getArray(json, "first"));
            assertEquals(2, first.size());
            assertEquals(1, first.get(0));
            assertEquals(2, first.get(1));

            List<Integer> second = JsonUtil.parseArrayOfInteger(JsonUtil.getArray(json, "second"));
            assertEquals(2, second.size());
            assertEquals(3, second.get(0));

            List<Integer> third = JsonUtil.parseArrayOfInteger(JsonUtil.getArray(json, "third"));
            assertEquals(2, third.size());
            assertEquals(5, third.get(0));
        }

        @Test
        @DisplayName("should handle array with null values")
        void shouldHandleArrayWithNulls() {
            String json = "{\"values\": [null, \"a\", null, \"b\"]}";
            String result = JsonUtil.getArray(json, "values");
            assertNotNull(result);
            assertTrue(result.contains("null"));
        }
    }

    // ========== parseObjectArray Tests ==========
    @Nested
    @DisplayName("parseObjectArray Tests")
    class ParseObjectArrayTests {

        @Test
        @DisplayName("should parse array of simple objects")
        void shouldParseArrayOfSimpleObjects() {
            String arrayJson = "[{\"id\": 1}, {\"id\": 2}, {\"id\": 3}]";
            List<String> objects = JsonUtil.parseObjectArray(arrayJson);
            assertEquals(3, objects.size());
            assertEquals(1, JsonUtil.getInteger(objects.get(0), "id"));
            assertEquals(2, JsonUtil.getInteger(objects.get(1), "id"));
            assertEquals(3, JsonUtil.getInteger(objects.get(2), "id"));
        }

        @Test
        @DisplayName("should parse array of complex objects")
        void shouldParseArrayOfComplexObjects() {
            String arrayJson = "[{\"transaction\": {\"id\": \"txn_1\", \"items\": [1, 2]}}, {\"transaction\": {\"id\": \"txn_2\"}}]";
            List<String> objects = JsonUtil.parseObjectArray(arrayJson);
            assertEquals(2, objects.size());
            assertTrue(objects.get(0).contains("txn_1"));
            assertTrue(objects.get(1).contains("txn_2"));
        }

        @Test
        @DisplayName("should return empty list for null input")
        void shouldReturnEmptyListForNull() {
            List<String> objects = JsonUtil.parseObjectArray(null);
            assertTrue(objects.isEmpty());
        }

        @Test
        @DisplayName("should return empty list for empty array")
        void shouldReturnEmptyListForEmptyArray() {
            List<String> objects = JsonUtil.parseObjectArray("[]");
            assertTrue(objects.isEmpty());
        }

        @Test
        @DisplayName("should return empty list for non-array input")
        void shouldReturnEmptyListForNonArray() {
            List<String> objects = JsonUtil.parseObjectArray("{\"id\": 1}");
            assertTrue(objects.isEmpty());
        }

        @Test
        @DisplayName("should handle objects with escaped strings")
        void shouldHandleObjectsWithEscapedStrings() {
            String arrayJson = "[{\"text\": \"Hello \\\"World\\\"\"}]";
            List<String> objects = JsonUtil.parseObjectArray(arrayJson);
            assertEquals(1, objects.size());
            assertTrue(objects.get(0).contains("Hello \\\"World\\\""));
        }
    }

    // ========== parseArrayOf* Tests ==========
    @Nested
    @DisplayName("parseArrayOf* Tests")
    class ParseArrayOfTests {

        @Test
        @DisplayName("should parse array of strings")
        void shouldParseArrayOfStrings() {
            String arrayJson = "[\"a\", \"b\", \"c\"]";
            List<String> result = JsonUtil.parseArrayOfString(arrayJson);
            assertEquals(3, result.size());
            assertEquals("a", result.get(0));
            assertEquals("b", result.get(1));
            assertEquals("c", result.get(2));
        }

        @Test
        @DisplayName("should parse array of integers")
        void shouldParseArrayOfIntegers() {
            String arrayJson = "[1, 2, 3, -4, 0]";
            List<Integer> result = JsonUtil.parseArrayOfInteger(arrayJson);
            assertEquals(5, result.size());
            assertEquals(1, result.get(0));
            assertEquals(-4, result.get(3));
            assertEquals(0, result.get(4));
        }

        @Test
        @DisplayName("should parse array of longs")
        void shouldParseArrayOfLongs() {
            String arrayJson = "[1605530769000, 1605530770000]";
            List<Long> result = JsonUtil.parseArrayOfLong(arrayJson);
            assertEquals(2, result.size());
            assertEquals(1605530769000L, result.get(0));
        }

        @Test
        @DisplayName("should parse array of booleans")
        void shouldParseArrayOfBooleans() {
            String arrayJson = "[true, false, true]";
            List<Boolean> result = JsonUtil.parseArrayOfBoolean(arrayJson);
            assertEquals(3, result.size());
            assertTrue(result.get(0));
            assertFalse(result.get(1));
            assertTrue(result.get(2));
        }

        @Test
        @DisplayName("should parse array of doubles")
        void shouldParseArrayOfDoubles() {
            String arrayJson = "[1.5, 2.7, 3.14]";
            List<Double> result = JsonUtil.parseArrayOfDouble(arrayJson);
            assertEquals(3, result.size());
            assertEquals(1.5, result.get(0), 0.001);
        }

        @Test
        @DisplayName("should parse array of BigDecimal")
        void shouldParseArrayOfBigDecimal() {
            String arrayJson = "[123.45, 678.90]";
            List<BigDecimal> result = JsonUtil.parseArrayOfBigDecimal(arrayJson);
            assertEquals(2, result.size());
        }

        @Test
        @DisplayName("should return empty list for empty arrays")
        void shouldReturnEmptyListForEmptyArrays() {
            assertTrue(JsonUtil.parseArrayOfString("[]").isEmpty());
            assertTrue(JsonUtil.parseArrayOfInteger("[]").isEmpty());
            assertTrue(JsonUtil.parseArrayOfLong("[]").isEmpty());
            assertTrue(JsonUtil.parseArrayOfBoolean("[]").isEmpty());
            assertTrue(JsonUtil.parseArrayOfDouble("[]").isEmpty());
        }

        @Test
        @DisplayName("should return empty list for null input")
        void shouldReturnEmptyListForNull() {
            assertTrue(JsonUtil.parseArrayOfString((String) null).isEmpty());
            assertTrue(JsonUtil.parseArrayOfInteger((String) null).isEmpty());
        }
    }

    // ========== getTimestamp Tests ==========
    @Nested
    @DisplayName("getTimestamp Tests")
    class GetTimestampTests {

        @Test
        @DisplayName("should parse epoch seconds to timestamp")
        void shouldParseEpochSecondsToTimestamp() {
            String json = "{\"created_at\": 1605530769}";
            Timestamp result = JsonUtil.getTimestamp(json, "created_at");
            assertNotNull(result);
            assertEquals(1605530769000L, result.getTime());
        }

        @Test
        @DisplayName("should return null for missing key")
        void shouldReturnNullForMissingKey() {
            String json = "{\"updated_at\": 1605530769}";
            assertNull(JsonUtil.getTimestamp(json, "created_at"));
        }
    }

    // ========== hasValue Tests ==========
    @Nested
    @DisplayName("hasValue Tests")
    class HasValueTests {

        @Test
        @DisplayName("should return true for existing non-null value")
        void shouldReturnTrueForExistingValue() {
            String json = "{\"name\": \"John\", \"age\": 30}";
            assertTrue(JsonUtil.hasValue(json, "name"));
            assertTrue(JsonUtil.hasValue(json, "age"));
        }

        @Test
        @DisplayName("should return false for null value")
        void shouldReturnFalseForNullValue() {
            String json = "{\"name\": null}";
            assertFalse(JsonUtil.hasValue(json, "name"));
        }

        @Test
        @DisplayName("should return false for missing key")
        void shouldReturnFalseForMissingKey() {
            String json = "{\"name\": \"John\"}";
            assertFalse(JsonUtil.hasValue(json, "missing"));
        }
    }

    // ========== parseJsonObjectToMap Tests ==========
    @Nested
    @DisplayName("parseJsonObjectToMap Tests")
    class ParseJsonObjectToMapTests {

        @Test
        @DisplayName("should parse simple object to map")
        void shouldParseSimpleObjectToMap() {
            String json = "{\"name\": \"John\", \"age\": 30, \"active\": true}";
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(json);
            assertEquals("John", map.get("name"));
            assertEquals(30L, map.get("age"));
            assertEquals(true, map.get("active"));
        }

        @Test
        @DisplayName("should handle nested objects")
        void shouldHandleNestedObjects() {
            String json = "{\"user\": {\"name\": \"John\"}}";
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(json);
            assertTrue(map.get("user") instanceof String);
            assertTrue(((String) map.get("user")).contains("name"));
        }

        @Test
        @DisplayName("should handle arrays in map")
        void shouldHandleArraysInMap() {
            String json = "{\"tags\": [\"a\", \"b\"]}";
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(json);
            assertTrue(map.get("tags") instanceof String);
            assertTrue(((String) map.get("tags")).contains("\"a\""));
        }

        @Test
        @DisplayName("should return empty map for empty object")
        void shouldReturnEmptyMapForEmptyObject() {
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap("{}");
            assertTrue(map.isEmpty());
        }

        @Test
        @DisplayName("should return empty map for null")
        void shouldReturnEmptyMapForNull() {
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap((String) null);
            assertTrue(map.isEmpty());
        }

        @Test
        @DisplayName("should handle null values")
        void shouldHandleNullValues() {
            String json = "{\"value\": null}";
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(json);
            assertTrue(map.containsKey("value"));
            assertNull(map.get("value"));
        }

        @Test
        @DisplayName("should handle double values")
        void shouldHandleDoubleValues() {
            String json = "{\"price\": 99.99}";
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(json);
            assertEquals(99.99, (Double) map.get("price"), 0.001);
        }
    }

    // ========== toJson Tests ==========
    @Nested
    @DisplayName("toJson Tests")
    class ToJsonTests {

        @Test
        @DisplayName("should serialize map to JSON")
        void shouldSerializeMapToJson() {
            Map<String, Object> map = new java.util.LinkedHashMap<>();
            map.put("name", "John");
            map.put("age", 30);
            
            String json = JsonUtil.toJson(map);
            assertTrue(json.contains("\"name\":\"John\""));
            assertTrue(json.contains("\"age\":30"));
        }

        @Test
        @DisplayName("should serialize list to JSON")
        void shouldSerializeListToJson() {
            List<String> list = java.util.Arrays.asList("a", "b", "c");
            String json = JsonUtil.toJson(list);
            assertEquals("[\"a\",\"b\",\"c\"]", json);
        }

        @Test
        @DisplayName("should serialize empty map")
        void shouldSerializeEmptyMap() {
            assertEquals("{}", JsonUtil.toJson(new java.util.HashMap<>()));
        }

        @Test
        @DisplayName("should serialize empty list")
        void shouldSerializeEmptyList() {
            assertEquals("[]", JsonUtil.toJson(new java.util.ArrayList<>()));
        }

        @Test
        @DisplayName("should escape special characters")
        void shouldEscapeSpecialCharacters() {
            Map<String, Object> map = new java.util.HashMap<>();
            map.put("text", "Hello \"World\"\nNew line");
            
            String json = JsonUtil.toJson(map);
            assertTrue(json.contains("\\\"World\\\""));
            assertTrue(json.contains("\\n"));
        }

        @Test
        @DisplayName("should serialize null values")
        void shouldSerializeNullValues() {
            Map<String, Object> map = new java.util.HashMap<>();
            map.put("value", null);
            
            String json = JsonUtil.toJson(map);
            assertTrue(json.contains("\"value\":null"));
        }

        @Test
        @DisplayName("should serialize nested structures")
        void shouldSerializeNestedStructures() {
            Map<String, Object> inner = new java.util.HashMap<>();
            inner.put("id", 123);
            
            Map<String, Object> outer = new java.util.HashMap<>();
            outer.put("data", inner);
            
            String json = JsonUtil.toJson(outer);
            assertTrue(json.contains("\"data\":{"));
            assertTrue(json.contains("\"id\":123"));
        }
    }

    // ========== Edge Cases and Real-World Scenarios ==========
    @Nested
    @DisplayName("Real-World Scenarios")
    class RealWorldScenarios {

        @Test
        @DisplayName("should parse transaction list response correctly")
        void shouldParseTransactionListResponse() {
            String json = "{" +
                "\"list\": [{\"transaction\": {" +
                    "\"id\": \"txn_AzZhUGSPAkLskJQo\"," +
                    "\"customer_id\": \"cbdemo_dave\"," +
                    "\"subscription_id\": \"cbdemo_dave-sub1\"," +
                    "\"gateway_account_id\": \"gw_AzZhUGSPAkLeQJPg\"," +
                    "\"payment_method\": \"card\"," +
                    "\"gateway\": \"chargebee\"," +
                    "\"type\": \"payment\"," +
                    "\"date\": 1605530769," +
                    "\"exchange_rate\": 1.0," +
                    "\"amount\": 10000," +
                    "\"id_at_gateway\": \"cb___dev__KyVnqiSIrqRVUEN\"," +
                    "\"status\": \"success\"," +
                    "\"updated_at\": 1605530769," +
                    "\"resource_version\": 1605530769000," +
                    "\"deleted\": false," +
                    "\"object\": \"transaction\"," +
                    "\"masked_card_number\": \"************4444\"," +
                    "\"currency_code\": \"USD\"," +
                    "\"base_currency_code\": \"USD\"," +
                    "\"amount_unused\": 0," +
                    "\"linked_invoices\": [{" +
                        "\"invoice_id\": \"DemoInv_103\"," +
                        "\"applied_amount\": 10000," +
                        "\"applied_at\": 1605530769," +
                        "\"invoice_date\": 1605530769," +
                        "\"invoice_total\": 10000," +
                        "\"invoice_status\": \"paid\"" +
                    "}]," +
                    "\"linked_refunds\": []," +
                    "\"payment_method_details\": \"{\\\"card\\\":{\\\"iin\\\":\\\"555555\\\",\\\"last4\\\":\\\"4444\\\"}}\"" +
                "}}]," +
                "\"next_offset\": null" +
            "}";

            // Test getArray with nested structures
            String listArray = JsonUtil.getArray(json, "list");
            assertNotNull(listArray, "list array should not be null");
            assertTrue(listArray.startsWith("["), "Should start with [");
            assertTrue(listArray.endsWith("]"), "Should end with ]");
            
            // Verify it contains the complete transaction data
            assertTrue(listArray.contains("txn_AzZhUGSPAkLskJQo"));
            assertTrue(listArray.contains("linked_invoices"));
            assertTrue(listArray.contains("DemoInv_103"));
            assertTrue(listArray.contains("linked_refunds"));
            assertTrue(listArray.contains("payment_method_details"));

            // Test parseObjectArray
            List<String> objects = JsonUtil.parseObjectArray(listArray);
            assertEquals(1, objects.size());
            
            // Test nested object extraction
            String transactionWrapper = objects.get(0);
            String transaction = JsonUtil.getObject(transactionWrapper, "transaction");
            assertNotNull(transaction);
            
            assertEquals("txn_AzZhUGSPAkLskJQo", JsonUtil.getString(transaction, "id"));
            assertEquals("cbdemo_dave", JsonUtil.getString(transaction, "customer_id"));
            assertEquals(10000, JsonUtil.getInteger(transaction, "amount"));
            assertEquals(1.0, JsonUtil.getDouble(transaction, "exchange_rate"), 0.001);
            assertFalse(JsonUtil.getBoolean(transaction, "deleted"));
            assertEquals(1605530769000L, JsonUtil.getLong(transaction, "resource_version"));
            
            // Test nested array extraction
            String linkedInvoices = JsonUtil.getArray(transaction, "linked_invoices");
            assertNotNull(linkedInvoices);
            assertTrue(linkedInvoices.contains("DemoInv_103"));
            
            String linkedRefunds = JsonUtil.getArray(transaction, "linked_refunds");
            assertNotNull(linkedRefunds);
            assertEquals("[]", linkedRefunds);
        }

        @Test
        @DisplayName("should handle customer list response")
        void shouldHandleCustomerListResponse() {
            String json = "{" +
                "\"list\": [" +
                    "{\"customer\": {\"id\": \"cust_123\", \"email\": \"test@example.com\"}}," +
                    "{\"customer\": {\"id\": \"cust_456\", \"email\": \"user@example.com\"}}" +
                "]," +
                "\"next_offset\": \"offset_abc123\"" +
            "}";

            String listArray = JsonUtil.getArray(json, "list");
            assertNotNull(listArray);
            
            List<String> items = JsonUtil.parseObjectArray(listArray);
            assertEquals(2, items.size());
            
            String customer1 = JsonUtil.getObject(items.get(0), "customer");
            assertEquals("cust_123", JsonUtil.getString(customer1, "id"));
            
            String nextOffset = JsonUtil.getString(json, "next_offset");
            assertEquals("offset_abc123", nextOffset);
        }

        @Test
        @DisplayName("should handle subscription with multiple nested arrays")
        void shouldHandleSubscriptionWithMultipleNestedArrays() {
            String json = "{" +
                "\"subscription\": {" +
                    "\"id\": \"sub_123\"," +
                    "\"subscription_items\": [" +
                        "{\"item_price_id\": \"price_1\", \"quantity\": 1}," +
                        "{\"item_price_id\": \"price_2\", \"quantity\": 2}" +
                    "]," +
                    "\"addons\": [{\"id\": \"addon_1\"}]," +
                    "\"coupons\": []," +
                    "\"discounts\": [{\"id\": \"disc_1\", \"apply_till\": [1, 2, 3]}]" +
                "}" +
            "}";

            String subscription = JsonUtil.getObject(json, "subscription");
            assertNotNull(subscription);
            
            String items = JsonUtil.getArray(subscription, "subscription_items");
            assertNotNull(items);
            List<String> itemsList = JsonUtil.parseObjectArray(items);
            assertEquals(2, itemsList.size());
            
            String addons = JsonUtil.getArray(subscription, "addons");
            assertNotNull(addons);
            
            String coupons = JsonUtil.getArray(subscription, "coupons");
            assertEquals("[]", coupons);
            
            String discounts = JsonUtil.getArray(subscription, "discounts");
            assertNotNull(discounts);
            // Verify nested array within object within array is handled
            assertTrue(discounts.contains("apply_till"));
            assertTrue(discounts.contains("[1, 2, 3]") || discounts.contains("[1,2,3]"));
        }
    }

    // ========== Edge Cases ==========
    @Nested
    @DisplayName("Edge Cases")
    class EdgeCases {

        @Test
        @DisplayName("should handle whitespace variations")
        void shouldHandleWhitespaceVariations() {
            String json1 = "{\"key\":\"value\"}";
            String json2 = "{ \"key\" : \"value\" }";
            String json3 = "{\n  \"key\"\t:\n  \"value\"\n}";
            
            assertEquals("value", JsonUtil.getString(json1, "key"));
            assertEquals("value", JsonUtil.getString(json2, "key"));
            assertEquals("value", JsonUtil.getString(json3, "key"));
        }

        @Test
        @DisplayName("should handle keys with special characters")
        void shouldHandleKeysWithSpecialCharacters() {
            String json = "{\"my-key\": \"value1\", \"my_key\": \"value2\", \"my.key\": \"value3\"}";
            assertEquals("value1", JsonUtil.getString(json, "my-key"));
            assertEquals("value2", JsonUtil.getString(json, "my_key"));
            assertEquals("value3", JsonUtil.getString(json, "my.key"));
        }

        @Test
        @DisplayName("should handle very long strings")
        void shouldHandleVeryLongStrings() {
            StringBuilder longValue = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                longValue.append("x");
            }
            String json = "{\"long\": \"" + longValue + "\"}";
            assertEquals(longValue.toString(), JsonUtil.getString(json, "long"));
        }

        @Test
        @DisplayName("should handle deeply nested structures")
        void shouldHandleDeeplyNestedStructures() {
            // Create 10 levels of nesting
            StringBuilder json = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                json.append("{\"level").append(i).append("\": ");
            }
            json.append("\"deep_value\"");
            for (int i = 0; i < 10; i++) {
                json.append("}");
            }
            
            String result = json.toString();
            String level0 = JsonUtil.getObject(result, "level0");
            assertNotNull(level0);
            assertTrue(level0.contains("deep_value"));
        }

        @Test
        @DisplayName("should handle array with mixed types")
        void shouldHandleArrayWithMixedTypes() {
            String json = "{\"mixed\": [1, \"two\", true, null, {\"key\": \"value\"}, [1, 2]]}";
            String array = JsonUtil.getArray(json, "mixed");
            assertNotNull(array);
            assertTrue(array.contains("1"));
            assertTrue(array.contains("\"two\""));
            assertTrue(array.contains("true"));
            assertTrue(array.contains("null"));
        }

        @Test
        @DisplayName("should handle colons in string values")
        void shouldHandleColonsInStringValues() {
            String json = "{\"url\": \"https://example.com:8080/path\"}";
            assertEquals("https://example.com:8080/path", JsonUtil.getString(json, "url"));
        }

        @Test
        @DisplayName("should handle quotes in key names")
        void shouldHandleFirstMatchForDuplicateKeys() {
            // JSON spec says duplicate keys have undefined behavior, but we should handle gracefully
            String json = "{\"key\": \"first\", \"key\": \"second\"}";
            String result = JsonUtil.getString(json, "key");
            // Should return one of the values (typically first)
            assertNotNull(result);
        }
    }

    // ========== Parse-Once (JsonObject-based) Tests ==========
    @Nested
    @DisplayName("Parse-Once JsonObject API Tests")
    class ParseOnceTests {

        @Test
        @DisplayName("parse should return JsonObject for valid JSON")
        void parseShouldReturnJsonObject() {
            JsonObject obj = JsonUtil.parse("{\"id\": \"sub_123\", \"amount\": 1000}");
            assertNotNull(obj);
            assertEquals("sub_123", JsonUtil.getString(obj, "id"));
            assertEquals(1000L, JsonUtil.getLong(obj, "amount"));
        }

        @Test
        @DisplayName("parse should return empty JsonObject for null/empty input")
        void parseShouldHandleNullAndEmpty() {
            assertNotNull(JsonUtil.parse(null));
            assertTrue(JsonUtil.parse(null).entrySet().isEmpty());
            assertNotNull(JsonUtil.parse(""));
            assertNotNull(JsonUtil.parse("  "));
        }

        @Test
        @DisplayName("all primitive extractors should work on JsonObject")
        void allPrimitiveExtractorsOnJsonObject() {
            String json = "{\"s\": \"hello\", \"i\": 42, \"l\": 9876543210, \"b\": true, \"d\": 3.14, \"bd\": 123.456, \"ts\": 1605530769}";
            JsonObject obj = JsonUtil.parse(json);

            assertEquals("hello", JsonUtil.getString(obj, "s"));
            assertEquals(42, JsonUtil.getInteger(obj, "i"));
            assertEquals(9876543210L, JsonUtil.getLong(obj, "l"));
            assertTrue(JsonUtil.getBoolean(obj, "b"));
            assertEquals(3.14, JsonUtil.getDouble(obj, "d"), 0.001);
            assertEquals(new BigDecimal("123.456"), JsonUtil.getBigDecimal(obj, "bd"));
            Timestamp ts = JsonUtil.getTimestamp(obj, "ts");
            assertNotNull(ts);
            assertEquals(1605530769000L, ts.getTime());
        }

        @Test
        @DisplayName("null and missing keys should return null on JsonObject")
        void nullAndMissingKeysOnJsonObject() {
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

        @Test
        @DisplayName("getJsonObject should extract nested objects without re-parsing")
        void getJsonObjectShouldExtractNested() {
            String json = "{\"customer\": {\"id\": \"cust_1\", \"email\": \"a@b.com\"}, \"amount\": 500}";
            JsonObject root = JsonUtil.parse(json);

            JsonObject customer = JsonUtil.getJsonObject(root, "customer");
            assertNotNull(customer);
            assertEquals("cust_1", JsonUtil.getString(customer, "id"));
            assertEquals("a@b.com", JsonUtil.getString(customer, "email"));
        }

        @Test
        @DisplayName("getJsonArray should extract arrays without re-parsing")
        void getJsonArrayShouldExtractArrays() {
            String json = "{\"items\": [{\"id\": \"item_1\"}, {\"id\": \"item_2\"}]}";
            JsonObject root = JsonUtil.parse(json);

            JsonArray items = JsonUtil.getJsonArray(root, "items");
            assertNotNull(items);
            assertEquals(2, items.size());
            assertEquals("item_1", items.get(0).getAsJsonObject().get("id").getAsString());
        }

        @Test
        @DisplayName("hasValue should work on JsonObject")
        void hasValueOnJsonObject() {
            JsonObject obj = JsonUtil.parse("{\"name\": \"test\", \"empty\": null}");
            assertTrue(JsonUtil.hasValue(obj, "name"));
            assertFalse(JsonUtil.hasValue(obj, "empty"));
            assertFalse(JsonUtil.hasValue(obj, "missing"));
        }

        @Test
        @DisplayName("mapArray should map JsonArray elements through fromJson-like function")
        void mapArrayShouldWork() {
            String json = "[{\"id\": \"a\"}, {\"id\": \"b\"}, {\"id\": \"c\"}]";
            JsonArray array = JsonUtil.parseToArray(json);
            List<String> ids = JsonUtil.mapArray(array, obj -> JsonUtil.getString(obj, "id"));
            assertEquals(3, ids.size());
            assertEquals("a", ids.get(0));
            assertEquals("b", ids.get(1));
            assertEquals("c", ids.get(2));
        }

        @Test
        @DisplayName("mapArray should return empty list for null array")
        void mapArrayNullShouldReturnEmpty() {
            List<String> result = JsonUtil.mapArray(null, obj -> "x");
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("mapArrayToMaps should convert array of objects to list of maps")
        void mapArrayToMapsShouldWork() {
            String json = "[{\"k1\": \"v1\"}, {\"k2\": 42}]";
            JsonArray array = JsonUtil.parseToArray(json);
            List<Map<String, Object>> maps = JsonUtil.mapArrayToMaps(array);
            assertEquals(2, maps.size());
            assertEquals("v1", maps.get(0).get("k1"));
            assertEquals(42L, maps.get(1).get("k2"));
        }

        @Test
        @DisplayName("parseArrayOfString with JsonArray should work")
        void parseArrayOfStringWithJsonArray() {
            JsonArray array = JsonUtil.parseToArray("[\"x\", \"y\", \"z\"]");
            List<String> result = JsonUtil.parseArrayOfString(array);
            assertEquals(3, result.size());
            assertEquals("x", result.get(0));
        }

        @Test
        @DisplayName("parseArrayOfInteger with JsonArray should work")
        void parseArrayOfIntegerWithJsonArray() {
            JsonArray array = JsonUtil.parseToArray("[1, 2, 3]");
            List<Integer> result = JsonUtil.parseArrayOfInteger(array);
            assertEquals(3, result.size());
            assertEquals(1, result.get(0));
        }

        @Test
        @DisplayName("parseArrayOfLong with JsonArray should work")
        void parseArrayOfLongWithJsonArray() {
            JsonArray array = JsonUtil.parseToArray("[1605530769000, 1605530770000]");
            List<Long> result = JsonUtil.parseArrayOfLong(array);
            assertEquals(2, result.size());
            assertEquals(1605530769000L, result.get(0));
        }

        @Test
        @DisplayName("parseArrayOfBoolean with JsonArray should work")
        void parseArrayOfBooleanWithJsonArray() {
            JsonArray array = JsonUtil.parseToArray("[true, false, true]");
            List<Boolean> result = JsonUtil.parseArrayOfBoolean(array);
            assertEquals(3, result.size());
            assertTrue(result.get(0));
            assertFalse(result.get(1));
        }

        @Test
        @DisplayName("parseArrayOfDouble with JsonArray should work")
        void parseArrayOfDoubleWithJsonArray() {
            JsonArray array = JsonUtil.parseToArray("[1.5, 2.7]");
            List<Double> result = JsonUtil.parseArrayOfDouble(array);
            assertEquals(2, result.size());
            assertEquals(1.5, result.get(0), 0.001);
        }

        @Test
        @DisplayName("parseArrayOfBigDecimal with JsonArray should work")
        void parseArrayOfBigDecimalWithJsonArray() {
            JsonArray array = JsonUtil.parseToArray("[123.45, 678.90]");
            List<BigDecimal> result = JsonUtil.parseArrayOfBigDecimal(array);
            assertEquals(2, result.size());
        }

        @Test
        @DisplayName("parseJsonObjectToMap with JsonObject should work")
        void parseJsonObjectToMapWithJsonObject() {
            JsonObject obj = JsonUtil.parse("{\"name\": \"John\", \"age\": 30, \"active\": true}");
            Map<String, Object> map = JsonUtil.parseJsonObjectToMap(obj);
            assertEquals("John", map.get("name"));
            assertEquals(30L, map.get("age"));
            assertEquals(true, map.get("active"));
        }

        @Test
        @DisplayName("getObject(JsonObject, key) should return JSON string")
        void getObjectOnJsonObjectShouldReturnString() {
            JsonObject obj = JsonUtil.parse("{\"meta\": {\"key\": \"val\"}}");
            String meta = JsonUtil.getObject(obj, "meta");
            assertNotNull(meta);
            assertTrue(meta.contains("key"));
            assertTrue(meta.contains("val"));
        }

        @Test
        @DisplayName("getArray(JsonObject, key) should return JSON string")
        void getArrayOnJsonObjectShouldReturnString() {
            JsonObject obj = JsonUtil.parse("{\"tags\": [\"a\", \"b\"]}");
            String tags = JsonUtil.getArray(obj, "tags");
            assertNotNull(tags);
            assertTrue(tags.startsWith("["));
            assertTrue(tags.endsWith("]"));
        }
    }

    // ========== extractCustomFields / extractConsentFields Tests ==========
    @Nested
    @DisplayName("extractCustomFields / extractConsentFields Tests")
    class ExtractFieldsTests {

        @Test
        @DisplayName("extractCustomFields should extract cf_ prefixed fields")
        void extractCustomFieldsShouldWork() {
            JsonObject obj = JsonUtil.parse("{\"id\": \"sub_1\", \"cf_color\": \"blue\", \"cf_size\": \"large\", \"name\": \"test\"}");
            Set<String> known = new HashSet<>();
            known.add("id");
            known.add("name");

            Map<String, String> cf = JsonUtil.extractCustomFields(obj, known);
            assertEquals(2, cf.size());
            assertEquals("blue", cf.get("cf_color"));
            assertEquals("large", cf.get("cf_size"));
        }

        @Test
        @DisplayName("extractCustomFields should handle null values")
        void extractCustomFieldsNullValues() {
            JsonObject obj = JsonUtil.parse("{\"cf_nullable\": null}");
            Map<String, String> cf = JsonUtil.extractCustomFields(obj, new HashSet<>());
            assertEquals(1, cf.size());
            assertNull(cf.get("cf_nullable"));
        }

        @Test
        @DisplayName("extractCustomFields should exclude known fields even with cf_ prefix")
        void extractCustomFieldsExcludesKnown() {
            JsonObject obj = JsonUtil.parse("{\"cf_known\": \"val\"}");
            Set<String> known = new HashSet<>();
            known.add("cf_known");
            Map<String, String> cf = JsonUtil.extractCustomFields(obj, known);
            assertTrue(cf.isEmpty());
        }

        @Test
        @DisplayName("extractConsentFields should extract cs_ prefixed fields")
        void extractConsentFieldsShouldWork() {
            JsonObject obj = JsonUtil.parse("{\"id\": \"cust_1\", \"cs_marketing\": true, \"cs_analytics\": false}");
            Set<String> known = new HashSet<>();
            known.add("id");

            Map<String, Object> cs = JsonUtil.extractConsentFields(obj, known);
            assertEquals(2, cs.size());
            assertEquals(true, cs.get("cs_marketing"));
            assertEquals(false, cs.get("cs_analytics"));
        }
    }

    // ========== Parse-Once Real-World Scenario ==========
    @Nested
    @DisplayName("Parse-Once Real-World Scenario")
    class ParseOnceRealWorld {

        @Test
        @DisplayName("should parse complex response with single parse call")
        void shouldParseComplexResponseWithSingleParse() {
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
            assertEquals("cust_456", JsonUtil.getString(sub, "customer_id"));
            assertEquals(5000L, JsonUtil.getLong(sub, "mrr"));
            assertEquals(1605530769000L, JsonUtil.getTimestamp(sub, "created_at").getTime());
            assertFalse(JsonUtil.getBoolean(sub, "deleted"));
            assertEquals(1.25, JsonUtil.getDouble(sub, "exchange_rate"), 0.001);

            JsonArray items = JsonUtil.getJsonArray(sub, "subscription_items");
            assertNotNull(items);
            assertEquals(2, items.size());
            List<String> itemIds = JsonUtil.mapArray(items, obj -> JsonUtil.getString(obj, "item_price_id"));
            assertEquals("price_1", itemIds.get(0));
            assertEquals("price_2", itemIds.get(1));

            JsonObject shipping = JsonUtil.getJsonObject(sub, "shipping_address");
            assertNotNull(shipping);
            assertEquals("NYC", JsonUtil.getString(shipping, "city"));

            JsonArray coupons = JsonUtil.getJsonArray(sub, "coupons");
            assertNotNull(coupons);
            assertEquals(0, coupons.size());

            JsonObject metaObj = JsonUtil.getJsonObject(sub, "meta_data");
            assertNotNull(metaObj);
            Map<String, Object> meta = JsonUtil.parseJsonObjectToMap(metaObj);
            assertEquals("api", meta.get("source"));
        }
    }
}

