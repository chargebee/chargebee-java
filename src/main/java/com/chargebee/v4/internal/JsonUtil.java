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
            
    /**
     * Extract string value from JSON for a given key.
     * Only matches top-level keys (not inside nested objects/arrays).
     */
    public static String getString(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        String flat = stripNested(json);
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*\"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\"");
        Matcher matcher = pattern.matcher(flat);
        if (matcher.find()) {
            return unescapeJsonString(matcher.group(1));
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
        String flat = stripNested(json);
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(-?\\d+)");
        Matcher matcher = pattern.matcher(flat);
        if (matcher.find()) {
            return Long.parseLong(matcher.group(1));
        }
        return null;
    }
    
    /**
     * Extract integer value from JSON for a given key.
     * Only matches top-level keys (not inside nested objects/arrays).
     */
    public static Integer getInteger(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        String flat = stripNested(json);
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(-?\\d+)");
        Matcher matcher = pattern.matcher(flat);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return null;
    }
    
    /**
     * Extract boolean value from JSON for a given key.
     * Only matches top-level keys (not inside nested objects/arrays).
     */
    public static Boolean getBoolean(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        String flat = stripNested(json);
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(true|false)");
        Matcher matcher = pattern.matcher(flat);
        if (matcher.find()) {
            return Boolean.parseBoolean(matcher.group(1));
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
        String flat = stripNested(json);
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(-?\\d+(?:\\.\\d+)?)");
        Matcher matcher = pattern.matcher(flat);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }
        return null;
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
        String flat = stripNested(json);
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(-?\\d+(?:\\.\\d+)?)");
        Matcher matcher = pattern.matcher(flat);
        if (matcher.find()) {
            return new java.math.BigDecimal(matcher.group(1));
        }
        return null;
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
        String flat = stripNested(json);
        // First check if the key exists with null value
        Pattern nullPattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*null\\b");
        if (nullPattern.matcher(flat).find()) {
            return false;
        }
        // Then check if the key exists at all
        Pattern keyPattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:");
        return keyPattern.matcher(flat).find();
    }
    
    /**
     * Parse array of strings from JSON array string.
     */
    public static List<String> parseArrayOfString(String arrayJson) {
        List<String> result = new ArrayList<>();
        if (arrayJson == null || arrayJson.trim().equals("[]")) {
            return result;
        }
        
        // Extract string values from array
        Pattern pattern = Pattern.compile("\"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\"");
        Matcher matcher = pattern.matcher(arrayJson);
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
        
        // Extract integer values from array
        Pattern pattern = Pattern.compile("(-?\\d+)(?![.\\d])");
        Matcher matcher = pattern.matcher(arrayJson);
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
        
        // Extract long values from array
        Pattern pattern = Pattern.compile("(-?\\d+)(?![.\\d])");
        Matcher matcher = pattern.matcher(arrayJson);
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
        
        // Extract boolean values from array
        Pattern pattern = Pattern.compile("\\b(true|false)\\b");
        Matcher matcher = pattern.matcher(arrayJson);
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
        
        // Extract double values from array
        Pattern pattern = Pattern.compile("(-?\\d+(?:\\.\\d+)?)");
        Matcher matcher = pattern.matcher(arrayJson);
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

        // Extract BigDecimal values from array
        Pattern pattern = Pattern.compile("(-?\\d+(?:\\.\\d+)?)");
        Matcher matcher = pattern.matcher(arrayJson);
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
     * Returns a flattened version of the JSON with nested objects and arrays
     * replaced by {@code null}, so regex-based extraction only matches
     * top-level keys.
     */
    private static String stripNested(String json) {
        if (json == null) return null;
        StringBuilder sb = new StringBuilder();
        int depth = 0;
        boolean inString = false;
        boolean escaped = false;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);

            if (escaped) {
                escaped = false;
                if (depth == 1) sb.append(c);
                continue;
            }

            if (c == '\\' && inString) {
                escaped = true;
                if (depth == 1) sb.append(c);
                continue;
            }

            if (c == '"') {
                inString = !inString;
                if (depth == 1) sb.append(c);
                continue;
            }

            if (!inString) {
                if (c == '{' || c == '[') {
                    if (depth == 0) {
                        sb.append(c);
                    } else if (depth == 1) {
                        sb.append("null");
                    }
                    depth++;
                    continue;
                }
                if (c == '}' || c == ']') {
                    depth--;
                    if (depth == 0) {
                        sb.append(c);
                    }
                    continue;
                }
            }

            if (depth == 1) sb.append(c);
        }

        return sb.toString();
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