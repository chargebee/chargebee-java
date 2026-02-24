package com.chargebee.v4.internal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/** Gson-backed JSON parsing utility. */
public class JsonUtil {

    private JsonUtil() {}

    // --- Parse entry points ---

    /** Parses a JSON string into a JsonObject; returns empty JsonObject on failure. */
    public static JsonObject parse(String json) {
        if (json == null || json.trim().isEmpty()) {
            return new JsonObject();
        }
        try {
            return JsonParser.parseString(json).getAsJsonObject();
        } catch (Exception e) {
            return new JsonObject();
        }
    }

    /** Parses a JSON string into a JsonArray; returns empty JsonArray on failure. */
    public static JsonArray parseToArray(String arrayJson) {
        if (arrayJson == null || arrayJson.trim().isEmpty()) {
            return new JsonArray();
        }
        try {
            return JsonParser.parseString(arrayJson).getAsJsonArray();
        } catch (Exception e) {
            return new JsonArray();
        }
    }

    // --- JsonObject-based extraction ---

    /** Returns the String value for the given key, or null. */
    public static String getString(JsonObject obj, String key) {
        if (obj == null || key == null) return null;
        JsonElement elem = obj.get(key);
        if (elem == null || elem.isJsonNull()) return null;
        try { return elem.getAsString(); } catch (Exception e) { return null; }
    }

    /** Returns the Long value for the given key, or null. */
    public static Long getLong(JsonObject obj, String key) {
        if (obj == null || key == null) return null;
        JsonElement elem = obj.get(key);
        if (elem == null || elem.isJsonNull()) return null;
        try { return elem.getAsLong(); } catch (Exception e) { return null; }
    }

    /** Returns the Integer value for the given key, or null. */
    public static Integer getInteger(JsonObject obj, String key) {
        if (obj == null || key == null) return null;
        JsonElement elem = obj.get(key);
        if (elem == null || elem.isJsonNull()) return null;
        try { return elem.getAsInt(); } catch (Exception e) { return null; }
    }

    /** Returns the Boolean value for the given key, or null. */
    public static Boolean getBoolean(JsonObject obj, String key) {
        if (obj == null || key == null) return null;
        JsonElement elem = obj.get(key);
        if (elem == null || elem.isJsonNull()) return null;
        try { return elem.getAsBoolean(); } catch (Exception e) { return null; }
    }

    /** Returns the Double value for the given key, or null. */
    public static Double getDouble(JsonObject obj, String key) {
        if (obj == null || key == null) return null;
        JsonElement elem = obj.get(key);
        if (elem == null || elem.isJsonNull()) return null;
        try { return elem.getAsDouble(); } catch (Exception e) { return null; }
    }

    /** Returns the Number value for the given key, or null. */
    public static Number getNumber(JsonObject obj, String key) {
        return getDouble(obj, key);
    }

    /** Returns the BigDecimal value for the given key, or null. */
    public static BigDecimal getBigDecimal(JsonObject obj, String key) {
        if (obj == null || key == null) return null;
        JsonElement elem = obj.get(key);
        if (elem == null || elem.isJsonNull()) return null;
        try { return elem.getAsBigDecimal(); } catch (Exception e) { return null; }
    }

    /** Converts an epoch-seconds Long to a Timestamp, or null. */
    public static Timestamp getTimestamp(JsonObject obj, String key) {
        Long epochSeconds = getLong(obj, key);
        return epochSeconds != null ? new Timestamp(epochSeconds * 1000) : null;
    }

    /** Returns a nested JsonObject for the given key, or null. */
    public static JsonObject getJsonObject(JsonObject obj, String key) {
        if (obj == null || key == null) return null;
        JsonElement elem = obj.get(key);
        if (elem == null || elem.isJsonNull() || !elem.isJsonObject()) return null;
        return elem.getAsJsonObject();
    }

    /** Returns a nested JsonArray for the given key, or null. */
    public static JsonArray getJsonArray(JsonObject obj, String key) {
        if (obj == null || key == null) return null;
        JsonElement elem = obj.get(key);
        if (elem == null || elem.isJsonNull() || !elem.isJsonArray()) return null;
        return elem.getAsJsonArray();
    }

    /** Returns a nested object as a JSON string for the given key, or null. */
    public static String getObject(JsonObject obj, String key) {
        if (obj == null || key == null) return null;
        JsonElement elem = obj.get(key);
        if (elem == null || elem.isJsonNull() || !elem.isJsonObject()) return null;
        return elem.toString();
    }

    /** Returns a nested array as a JSON string for the given key, or null. */
    public static String getArray(JsonObject obj, String key) {
        if (obj == null || key == null) return null;
        JsonElement elem = obj.get(key);
        if (elem == null || elem.isJsonNull() || !elem.isJsonArray()) return null;
        return elem.toString();
    }

    /** Returns true if the key exists and is not null. */
    public static boolean hasValue(JsonObject obj, String key) {
        if (obj == null || key == null) return false;
        JsonElement elem = obj.get(key);
        return elem != null && !elem.isJsonNull();
    }

    // --- JsonObject to Map conversion ---

    /** Converts a JsonObject to a Map; primitives become Java types, nested structures become JSON strings. */
    public static Map<String, Object> parseJsonObjectToMap(JsonObject obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) return map;
        for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
            map.put(entry.getKey(), toJavaValue(entry.getValue()));
        }
        return map;
    }

    // --- JsonArray-based extraction ---

    /** Maps each JsonObject element through the given function; e.g. {@code mapArray(arr, Model::fromJson)}. */
    public static <T> List<T> mapArray(JsonArray array, Function<JsonObject, T> mapper) {
        if (array == null) return new ArrayList<>();
        List<T> result = new ArrayList<>(array.size());
        for (JsonElement elem : array) {
            if (elem != null && elem.isJsonObject()) {
                result.add(mapper.apply(elem.getAsJsonObject()));
            }
        }
        return result;
    }

    /** Converts each JsonArray element to its Java value, returning {@code List<Object>}. */
    public static List<Object> mapArrayToObjects(JsonArray array) {
        if (array == null) return new ArrayList<>();
        List<Object> result = new ArrayList<>(array.size());
        for (JsonElement elem : array) {
            if (elem != null && elem.isJsonObject()) {
                result.add(parseJsonObjectToMap(elem.getAsJsonObject()));
            } else {
                result.add(toJavaValue(elem));
            }
        }
        return result;
    }

    /** Converts each JsonObject element to a {@code Map<String, Object>}; skips non-object elements. */
    public static List<Map<String, Object>> mapArrayToMaps(JsonArray array) {
        if (array == null) return new ArrayList<>();
        List<Map<String, Object>> result = new ArrayList<>(array.size());
        for (JsonElement elem : array) {
            if (elem != null && elem.isJsonObject()) {
                result.add(parseJsonObjectToMap(elem.getAsJsonObject()));
            }
        }
        return result;
    }

    /** Serializes each JsonObject element to its JSON string representation. */
    public static List<String> parseObjectArray(JsonArray array) {
        List<String> result = new ArrayList<>();
        if (array == null) return result;
        for (JsonElement elem : array) {
            if (elem != null && elem.isJsonObject()) {
                result.add(elem.toString());
            }
        }
        return result;
    }

    /** Parses a JsonArray of strings into a List. */
    public static List<String> parseArrayOfString(JsonArray array) {
        List<String> result = new ArrayList<>();
        if (array == null) return result;
        for (JsonElement elem : array) {
            if (elem != null && !elem.isJsonNull()) {
                result.add(elem.getAsString());
            }
        }
        return result;
    }

    /** Parses a JsonArray of integers into a List. */
    public static List<Integer> parseArrayOfInteger(JsonArray array) {
        List<Integer> result = new ArrayList<>();
        if (array == null) return result;
        for (JsonElement elem : array) {
            if (elem != null && !elem.isJsonNull()) {
                try { result.add(elem.getAsInt()); } catch (Exception e) { /* skip */ }
            }
        }
        return result;
    }

    /** Parses a JsonArray of longs into a List. */
    public static List<Long> parseArrayOfLong(JsonArray array) {
        List<Long> result = new ArrayList<>();
        if (array == null) return result;
        for (JsonElement elem : array) {
            if (elem != null && !elem.isJsonNull()) {
                try { result.add(elem.getAsLong()); } catch (Exception e) { /* skip */ }
            }
        }
        return result;
    }

    /** Parses a JsonArray of booleans into a List. */
    public static List<Boolean> parseArrayOfBoolean(JsonArray array) {
        List<Boolean> result = new ArrayList<>();
        if (array == null) return result;
        for (JsonElement elem : array) {
            if (elem != null && !elem.isJsonNull()) {
                try { result.add(elem.getAsBoolean()); } catch (Exception e) { /* skip */ }
            }
        }
        return result;
    }

    /** Parses a JsonArray of doubles into a List. */
    public static List<Double> parseArrayOfDouble(JsonArray array) {
        List<Double> result = new ArrayList<>();
        if (array == null) return result;
        for (JsonElement elem : array) {
            if (elem != null && !elem.isJsonNull()) {
                try { result.add(elem.getAsDouble()); } catch (Exception e) { /* skip */ }
            }
        }
        return result;
    }

    /** Parses a JsonArray of BigDecimals into a List. */
    public static List<BigDecimal> parseArrayOfBigDecimal(JsonArray array) {
        List<BigDecimal> result = new ArrayList<>();
        if (array == null) return result;
        for (JsonElement elem : array) {
            if (elem != null && !elem.isJsonNull()) {
                try { result.add(elem.getAsBigDecimal()); } catch (Exception e) { /* skip */ }
            }
        }
        return result;
    }

    // --- Custom / consent field extraction ---

    /** Extracts keys starting with "cf_" that are not in the known fields set. */
    public static Map<String, String> extractCustomFields(JsonObject obj, Set<String> knownFields) {
        Map<String, String> customFields = new HashMap<>();
        if (obj == null) return customFields;
        for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
            String key = entry.getKey();
            if (key != null && key.startsWith("cf_") && !knownFields.contains(key)) {
                JsonElement value = entry.getValue();
                if (value == null || value.isJsonNull()) {
                    customFields.put(key, null);
                } else if (value.isJsonPrimitive()) {
                    customFields.put(key, value.getAsString());
                } else {
                    customFields.put(key, value.toString());
                }
            }
        }
        return customFields;
    }

    /** Extracts keys starting with "cs_" that are not in the known fields set. */
    public static Map<String, Object> extractConsentFields(JsonObject obj, Set<String> knownFields) {
        Map<String, Object> consentFields = new HashMap<>();
        if (obj == null) return consentFields;
        for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
            String key = entry.getKey();
            if (key != null && key.startsWith("cs_") && !knownFields.contains(key)) {
                consentFields.put(key, toJavaValue(entry.getValue()));
            }
        }
        return consentFields;
    }

    // --- Serialization ---

    /** Serializes a Map to a JSON string. */
    @SuppressWarnings("unchecked")
    public static String toJson(Map<String, Object> map) {
        if (map == null || map.isEmpty()) return "{}";
        JsonObject obj = new JsonObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            obj.add(entry.getKey(), toJsonElement(entry.getValue()));
        }
        return obj.toString();
    }

    /** Serializes a List to a JSON string. */
    public static String toJson(List<?> list) {
        if (list == null || list.isEmpty()) return "[]";
        JsonArray array = new JsonArray();
        for (Object item : list) {
            array.add(toJsonElement(item));
        }
        return array.toString();
    }

    // --- Internal helpers ---

    private static Object toJavaValue(JsonElement value) {
        if (value == null || value.isJsonNull()) return null;
        if (value.isJsonPrimitive()) {
            JsonPrimitive prim = value.getAsJsonPrimitive();
            if (prim.isString()) return prim.getAsString();
            if (prim.isBoolean()) return prim.getAsBoolean();
            if (prim.isNumber()) {
                String numStr = prim.getAsString();
                if (numStr.contains(".") || numStr.contains("e") || numStr.contains("E")) {
                    return prim.getAsDouble();
                }
                return prim.getAsLong();
            }
        }
        if (value.isJsonObject() || value.isJsonArray()) {
            return value.toString();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static JsonElement toJsonElement(Object value) {
        if (value == null) return JsonNull.INSTANCE;
        if (value instanceof String) return new JsonPrimitive((String) value);
        if (value instanceof Number) return new JsonPrimitive((Number) value);
        if (value instanceof Boolean) return new JsonPrimitive((Boolean) value);
        if (value instanceof Map) {
            JsonObject obj = new JsonObject();
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) value).entrySet()) {
                obj.add(entry.getKey(), toJsonElement(entry.getValue()));
            }
            return obj;
        }
        if (value instanceof List) {
            JsonArray array = new JsonArray();
            for (Object item : (List<?>) value) {
                array.add(toJsonElement(item));
            }
            return array;
        }
        return new JsonPrimitive(value.toString());
    }
}
