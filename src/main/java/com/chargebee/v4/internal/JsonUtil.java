package com.chargebee.v4.internal;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple JSON parsing utility for basic response parsing.
 * Avoids heavy dependencies while providing essential JSON functionality.
 */
public class JsonUtil {

    private static final Pattern ARRAY_STRING_PATTERN =
            Pattern.compile("\"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\"");
    private static final Pattern ARRAY_INT_PATTERN =
            Pattern.compile("(-?\\d+)(?![.\\d])");
    private static final Pattern ARRAY_BOOL_PATTERN =
            Pattern.compile("\\b(true|false)\\b");
    private static final Pattern ARRAY_DECIMAL_PATTERN =
            Pattern.compile("(-?\\d+(?:\\.\\d+)?)");

    /**
     * Extract string value from JSON for a given key.
     * Only matches top-level keys (not inside nested objects/arrays).
     */
    public static String getString(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        int start = findTopLevelValueStart(json, key);
        if (start < 0 || start >= json.length() || json.charAt(start) != '"') {
            return null;
        }
        int i = start + 1;
        boolean escaped = false;
        while (i < json.length()) {
            char c = json.charAt(i);
            if (escaped) {
                escaped = false;
            } else if (c == '\\') {
                escaped = true;
            } else if (c == '"') {
                return unescapeJsonString(json.substring(start + 1, i));
            }
            i++;
        }
        return null;
    }
    
    /**
     * Extract long value from JSON for a given key.
     * Only matches top-level keys (not inside nested objects/arrays).
     */
    public static Long getLong(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        String numStr = extractNumericString(json, key);
        if (numStr == null) {
            return null;
        }
        try {
            return Long.parseLong(numStr);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    /**
     * Extract integer value from JSON for a given key.
     * Only matches top-level keys (not inside nested objects/arrays).
     */
    public static Integer getInteger(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        String numStr = extractNumericString(json, key);
        if (numStr == null) {
            return null;
        }
        try {
            return Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    /**
     * Extract boolean value from JSON for a given key.
     * Only matches top-level keys (not inside nested objects/arrays).
     */
    public static Boolean getBoolean(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        int start = findTopLevelValueStart(json, key);
        if (start < 0 || start >= json.length()) {
            return null;
        }
        if (json.regionMatches(start, "true", 0, 4)) {
            return Boolean.TRUE;
        }
        if (json.regionMatches(start, "false", 0, 5)) {
            return Boolean.FALSE;
        }
        return null;
    }
    
    /**
     * Extract double value from JSON for a given key.
     * Only matches top-level keys (not inside nested objects/arrays).
     */
    public static Double getDouble(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        String numStr = extractNumericString(json, key);
        if (numStr == null) {
            return null;
        }
        try {
            return Double.parseDouble(numStr);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    /**
     * Extract number value from JSON for a given key.
     * Returns Double for numeric values.
     */
    public static Number getNumber(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        return getDouble(json, key);
    }
    
    /**
     * Extract BigDecimal value from JSON for a given key.
     * Only matches top-level keys (not inside nested objects/arrays).
     */
    public static java.math.BigDecimal getBigDecimal(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        String numStr = extractNumericString(json, key);
        if (numStr == null) {
            return null;
        }
        try {
            return new java.math.BigDecimal(numStr);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Extract nested object as JSON string for a given key.
     * Only matches top-level keys (not inside nested objects/arrays).
     */
    public static String getObject(String json, String key) {   
        if (json == null || key == null) {
            return null;
        }
        int start = findTopLevelValueStart(json, key);
        if (start < 0 || start >= json.length() || json.charAt(start) != '{') {
            return null;
        }
        
        int depth = 0;
        boolean inString = false;
        boolean escaped = false;
        
        for (int i = start; i < json.length(); i++) {
            char c = json.charAt(i);
            
            if (escaped) {
                escaped = false;
                continue;
            }
            
            if (c == '\\' && inString) {
                escaped = true;
                continue;
            }
            
            if (c == '"' && !escaped) {
                inString = !inString;
                continue;
            }
            
            if (!inString) {
                if (c == '{') {
                    depth++;
                } else if (c == '}') {
                    depth--;
                    if (depth == 0) {
                        return json.substring(start, i + 1);
                    }
                }
            }
        }
        
        return null;
    }
    
    /**
     * Extract array as JSON string for a given key.
     * Only matches top-level keys (not inside nested objects/arrays).
     */
    public static String getArray(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        int start = findTopLevelValueStart(json, key);
        if (start < 0 || start >= json.length() || json.charAt(start) != '[') {
            return null;
        }
        
        int depth = 0;
        boolean inString = false;
        boolean escaped = false;
        
        for (int i = start; i < json.length(); i++) {
            char c = json.charAt(i);
            
            if (escaped) {
                escaped = false;
                continue;
            }
            
            if (c == '\\' && inString) {
                escaped = true;
                continue;
            }
            
            if (c == '"' && !escaped) {
                inString = !inString;
                continue;
            }
            
            if (!inString) {
                if (c == '[') {
                    depth++;
                } else if (c == ']') {
                    depth--;
                    if (depth == 0) {
                        return json.substring(start, i + 1);
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * Find the index in the original JSON where the value starts for a
     * top-level key (depth&nbsp;1 inside the outermost braces).
     *
     * @return index of the first non-whitespace character after the colon,
     *         or {@code -1} if the key is not found at the top level.
     */
    private static int findTopLevelValueStart(String json, String key) {
        String target = "\"" + key + "\"";
        int depth = 0;
        boolean inString = false;
        boolean escaped = false;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);

            if (escaped) {
                escaped = false;
                continue;
            }
            if (c == '\\' && inString) {
                escaped = true;
                continue;
            }
            if (c == '"') {
                if (!inString && depth == 1
                        && i + target.length() <= json.length()
                        && json.regionMatches(i, target, 0, target.length())) {
                    int j = i + target.length();
                    while (j < json.length() && Character.isWhitespace(json.charAt(j))) j++;
                    if (j < json.length() && json.charAt(j) == ':') {
                        j++;
                        while (j < json.length() && Character.isWhitespace(json.charAt(j))) j++;
                        return j;
                    }
                }
                inString = !inString;
                continue;
            }
            if (!inString) {
                if (c == '{' || c == '[') depth++;
                else if (c == '}' || c == ']') depth--;
            }
        }
        return -1;
    }

    /**
     * Locate a top-level numeric value for the given key and return it as a
     * raw string (e.g. "-123", "3.75").  Returns {@code null} when the key
     * is absent or the value is not a number.
     */
    private static String extractNumericString(String json, String key) {
        int start = findTopLevelValueStart(json, key);
        if (start < 0 || start >= json.length()) {
            return null;
        }
        char c = json.charAt(start);
        if (c != '-' && !Character.isDigit(c)) {
            return null;
        }
        int end = start + 1;
        while (end < json.length()) {
            c = json.charAt(end);
            if (Character.isDigit(c) || c == '.') {
                end++;
            } else {
                break;
            }
        }
        return json.substring(start, end);
    }
    
    /**
     * Parse array of objects and extract each object as JSON string.
     */
    public static List<String> parseObjectArray(String arrayJson) {
        List<String> objects = new ArrayList<>();
        if (arrayJson == null || !arrayJson.trim().startsWith("[")) {
            return objects;
        }
        
        // Simple object extraction from array
        int depth = 0;
        int start = -1;
        boolean inString = false;
        boolean escaped = false;
        
        for (int i = 0; i < arrayJson.length(); i++) {
            char c = arrayJson.charAt(i);
            
            if (escaped) {
                escaped = false;
                continue;
            }
            
            if (c == '\\' && inString) {
                escaped = true;
                continue;
            }
            
            if (c == '"' && !escaped) {
                inString = !inString;
                continue;
            }
            
            if (!inString) {
                if (c == '{') {
                    if (depth == 0) {
                        start = i;
                    }
                    depth++;
                } else if (c == '}') {
                    depth--;
                    if (depth == 0 && start != -1) {
                        objects.add(arrayJson.substring(start, i + 1));
                        start = -1;
                    }
                }
            }
        }
        
        return objects;
    }
    
    /**
     * Check if a key exists and has non-null value.
     * Only checks top-level keys (not inside nested objects/arrays).
     */
    public static boolean hasValue(String json, String key) {
        if (json == null || key == null) {
            return false;
        }
        int start = findTopLevelValueStart(json, key);
        if (start < 0 || start >= json.length()) {
            return false;
        }
        return !json.regionMatches(start, "null", 0, 4);
    }
    
    /**
     * Parse array of strings from JSON array string.
     */
    public static List<String> parseArrayOfString(String arrayJson) {
        List<String> result = new ArrayList<>();
        if (arrayJson == null || arrayJson.trim().equals("[]")) {
            return result;
        }
        
        Matcher matcher = ARRAY_STRING_PATTERN.matcher(arrayJson);
        while (matcher.find()) {
            result.add(unescapeJsonString(matcher.group(1)));
        }
        return result;
    }

    /**
     * Parse array of integers from JSON array string.
     */
    public static List<Integer> parseArrayOfInteger(String arrayJson) {
        List<Integer> result = new ArrayList<>();
        if (arrayJson == null || arrayJson.trim().equals("[]")) {
            return result;
        }
        
        Matcher matcher = ARRAY_INT_PATTERN.matcher(arrayJson);
        while (matcher.find()) {
            try {
                result.add(Integer.parseInt(matcher.group(1)));
            } catch (NumberFormatException e) {
                // Skip invalid numbers
            }
        }
        return result;
    }

    /**
     * Parse array of longs from JSON array string.
     */
    public static List<Long> parseArrayOfLong(String arrayJson) {
        List<Long> result = new ArrayList<>();
        if (arrayJson == null || arrayJson.trim().equals("[]")) {
            return result;
        }
        
        Matcher matcher = ARRAY_INT_PATTERN.matcher(arrayJson);
        while (matcher.find()) {
            try {
                result.add(Long.parseLong(matcher.group(1)));
            } catch (NumberFormatException e) {
                // Skip invalid numbers
            }
        }
        return result;
    }

    /**
     * Parse array of booleans from JSON array string.
     */
    public static List<Boolean> parseArrayOfBoolean(String arrayJson) {
        List<Boolean> result = new ArrayList<>();
        if (arrayJson == null || arrayJson.trim().equals("[]")) {
            return result;
        }
        
        Matcher matcher = ARRAY_BOOL_PATTERN.matcher(arrayJson);
        while (matcher.find()) {
            result.add(Boolean.parseBoolean(matcher.group(1)));
        }
        return result;
    }

    /**
     * Parse array of doubles from JSON array string.
     */
    public static List<Double> parseArrayOfDouble(String arrayJson) {
        List<Double> result = new ArrayList<>();
        if (arrayJson == null || arrayJson.trim().equals("[]")) {
            return result;
        }
        
        Matcher matcher = ARRAY_DECIMAL_PATTERN.matcher(arrayJson);
        while (matcher.find()) {
            try {
                result.add(Double.parseDouble(matcher.group(1)));
            } catch (NumberFormatException e) {
                // Skip invalid numbers
            }
        }
        return result;
    }

    /**
     * Parse array of BigDecimal from JSON array string.
     */
    public static List<java.math.BigDecimal> parseArrayOfBigDecimal(String arrayJson) {
        List<java.math.BigDecimal> result = new ArrayList<>();
        if (arrayJson == null || arrayJson.trim().equals("[]")) {
            return result;
        }

        Matcher matcher = ARRAY_DECIMAL_PATTERN.matcher(arrayJson);
        while (matcher.find()) {
            try {
                result.add(new java.math.BigDecimal(matcher.group(1)));
            } catch (NumberFormatException e) {
                // Skip invalid numbers
            }
        }
        return result;
    }

    /** 
     * Parse timestamp from JSON (Unix epoch seconds).
     */
    public static Timestamp getTimestamp(String json, String key) {
        Long epochSeconds = getLong(json, key);
        return epochSeconds != null ? new Timestamp(epochSeconds * 1000) : null;
    }

    /**
     * Parse a JSON object into a Map&lt;String, Object&gt;.
     * Values are kept as their raw types (String, Long, Double, Boolean, or nested JSON strings).
     */
    public static java.util.Map<String, Object> parseJsonObjectToMap(String json) {
        java.util.Map<String, Object> map = new java.util.HashMap<>();
        if (json == null || json.trim().isEmpty() || json.trim().equals("{}")) {
            return map;
        }
        
        // Remove outer braces and trim
        String content = json.trim();
        if (content.startsWith("{")) {
            content = content.substring(1);
        }
        if (content.endsWith("}")) {
            content = content.substring(0, content.length() - 1);
        }
        content = content.trim();
        
        if (content.isEmpty()) {
            return map;
        }
        
        // Parse key-value pairs
        int i = 0;
        while (i < content.length()) {
            // Skip whitespace
            while (i < content.length() && Character.isWhitespace(content.charAt(i))) {
                i++;
            }
            if (i >= content.length()) break;
            
            // Parse key (must be quoted)
            if (content.charAt(i) != '"') {
                break; // Invalid JSON
            }
            i++; // Skip opening quote
            
            StringBuilder keyBuilder = new StringBuilder();
            boolean escaped = false;
            while (i < content.length()) {
                char c = content.charAt(i);
                if (escaped) {
                    keyBuilder.append(c);
                    escaped = false;
                } else if (c == '\\') {
                    escaped = true;
                } else if (c == '"') {
                    i++; // Skip closing quote
                    break;
                } else {
                    keyBuilder.append(c);
                }
                i++;
            }
            String key = keyBuilder.toString();
            
            // Skip whitespace and colon
            while (i < content.length() && (Character.isWhitespace(content.charAt(i)) || content.charAt(i) == ':')) {
                i++;
            }
            
            // Parse value
            Object value = null;
            if (i < content.length()) {
                char c = content.charAt(i);
                
                if (c == '"') {
                    // String value
                    i++; // Skip opening quote
                    StringBuilder valueBuilder = new StringBuilder();
                    escaped = false;
                    while (i < content.length()) {
                        c = content.charAt(i);
                        if (escaped) {
                            valueBuilder.append(c);
                            escaped = false;
                        } else if (c == '\\') {
                            escaped = true;
                        } else if (c == '"') {
                            i++; // Skip closing quote
                            break;
                        } else {
                            valueBuilder.append(c);
                        }
                        i++;
                    }
                    value = unescapeJsonString(valueBuilder.toString());
                    
                } else if (c == '{') {
                    // Nested object - extract as JSON string
                    int depth = 0;
                    int start = i;
                    boolean inString = false;
                    escaped = false;
                    while (i < content.length()) {
                        c = content.charAt(i);
                        if (escaped) {
                            escaped = false;
                        } else if (c == '\\' && inString) {
                            escaped = true;
                        } else if (c == '"') {
                            inString = !inString;
                        } else if (!inString) {
                            if (c == '{') depth++;
                            else if (c == '}') {
                                depth--;
                                if (depth == 0) {
                                    i++;
                                    break;
                                }
                            }
                        }
                        i++;
                    }
                    value = content.substring(start, i);
                    
                } else if (c == '[') {
                    // Array - extract as JSON string
                    int depth = 0;
                    int start = i;
                    boolean inString = false;
                    escaped = false;
                    while (i < content.length()) {
                        c = content.charAt(i);
                        if (escaped) {
                            escaped = false;
                        } else if (c == '\\' && inString) {
                            escaped = true;
                        } else if (c == '"') {
                            inString = !inString;
                        } else if (!inString) {
                            if (c == '[') depth++;
                            else if (c == ']') {
                                depth--;
                                if (depth == 0) {
                                    i++;
                                    break;
                                }
                            }
                        }
                        i++;
                    }
                    value = content.substring(start, i);
                    
                } else if (c == 't' && content.substring(i).startsWith("true")) {
                    // Boolean true
                    value = Boolean.TRUE;
                    i += 4;
                    
                } else if (c == 'f' && content.substring(i).startsWith("false")) {
                    // Boolean false
                    value = Boolean.FALSE;
                    i += 5;
                    
                } else if (c == 'n' && content.substring(i).startsWith("null")) {
                    // null
                    value = null;
                    i += 4;
                    
                } else if (c == '-' || Character.isDigit(c)) {
                    // Number
                    StringBuilder numBuilder = new StringBuilder();
                    while (i < content.length()) {
                        c = content.charAt(i);
                        if (c == '-' || c == '+' || c == '.' || c == 'e' || c == 'E' || Character.isDigit(c)) {
                            numBuilder.append(c);
                            i++;
                        } else {
                            break;
                        }
                    }
                    String numStr = numBuilder.toString();
                    try {
                        if (numStr.contains(".") || numStr.contains("e") || numStr.contains("E")) {
                            value = Double.parseDouble(numStr);
                        } else {
                            value = Long.parseLong(numStr);
                        }
                    } catch (NumberFormatException e) {
                        value = numStr; // Fallback to string
                    }
                }
            }
            
            map.put(key, value);
            
            // Skip comma and whitespace
            while (i < content.length() && (Character.isWhitespace(content.charAt(i)) || content.charAt(i) == ',')) {
                i++;
            }
        }
        
        return map;
    }

    /**
     * Unescape JSON string.
     * Processes escape sequences correctly by handling \\\\ last to avoid
     * incorrectly interpreting sequences like \\t as tab.
     */
    private static String unescapeJsonString(String escaped) {
        if (escaped == null) return null;
        
        // Use a placeholder for \\ to avoid interference with other escapes
        // e.g., \\t should become \t (backslash + t), not a tab character
        String placeholder = "\u0000BACKSLASH\u0000";
        
        return escaped
            .replace("\\\\", placeholder)  // Temporarily replace \\ with placeholder
            .replace("\\\"", "\"")
            .replace("\\/", "/")
            .replace("\\b", "\b")
            .replace("\\f", "\f")
            .replace("\\n", "\n")
            .replace("\\r", "\r")
            .replace("\\t", "\t")
            .replace(placeholder, "\\");   // Replace placeholder with actual backslash
    }

    /**
     * Serialize a Map to a JSON string.
     */
    public static String toJson(java.util.Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return "{}";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        
        boolean first = true;
        for (java.util.Map.Entry<String, Object> entry : map.entrySet()) {
            if (!first) {
                sb.append(',');
            }
            first = false;
            
            sb.append('"').append(escapeJsonString(entry.getKey())).append('"');
            sb.append(':');
            appendJsonValue(sb, entry.getValue());
        }
        
        sb.append('}');
        return sb.toString();
    }

    /**
     * Serialize a List to a JSON string.
     */
    public static String toJson(java.util.List<?> list) {
        if (list == null || list.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        appendJsonList(sb, list);
        return sb.toString();
    }
    
    /**
     * Append a JSON value (handles different types).
     */
    @SuppressWarnings("unchecked")
    private static void appendJsonValue(StringBuilder sb, Object value) {
        if (value == null) {
            sb.append("null");
        } else if (value instanceof String) {
            sb.append('"').append(escapeJsonString((String) value)).append('"');
        } else if (value instanceof Number) {
            sb.append(value.toString());
        } else if (value instanceof Boolean) {
            sb.append(value.toString());
        } else if (value instanceof java.util.Map) {
            sb.append(toJson((java.util.Map<String, Object>) value));
        } else if (value instanceof java.util.List) {
            appendJsonList(sb, (java.util.List<?>) value);
        } else {
            // Fallback: convert to string
            sb.append('"').append(escapeJsonString(value.toString())).append('"');
        }
    }
    
    /**
     * Append a JSON array.
     */
    private static void appendJsonList(StringBuilder sb, java.util.List<?> list) {
        sb.append('[');
        boolean first = true;
        for (Object item : list) {
            if (!first) {
                sb.append(',');
            }
            first = false;
            appendJsonValue(sb, item);
        }
        sb.append(']');
    }
    
    /**
     * Escape special characters in JSON strings.
     */
    private static String escapeJsonString(String str) {
        if (str == null) {
            return "";
        }
        
        StringBuilder escaped = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            switch (ch) {
                case '"':
                    escaped.append("\\\"");
                    break;
                case '\\':
                    escaped.append("\\\\");
                    break;
                case '\b':
                    escaped.append("\\b");
                    break;
                case '\f':
                    escaped.append("\\f");
                    break;
                case '\n':
                    escaped.append("\\n");
                    break;
                case '\r':
                    escaped.append("\\r");
                    break;
                case '\t':
                    escaped.append("\\t");
                    break;
                default:
                    // Control characters
                    if (ch < ' ') {
                        escaped.append(String.format("\\u%04x", (int) ch));
                    } else {
                        escaped.append(ch);
                    }
            }
        }
        return escaped.toString();
    }
}