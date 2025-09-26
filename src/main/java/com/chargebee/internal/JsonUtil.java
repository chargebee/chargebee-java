package com.chargebee.internal;

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
     */
    public static String getString(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*\"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\"");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return unescapeJsonString(matcher.group(1));
        }
        return null;
    }
    
    /**
     * Extract long value from JSON for a given key.
     */
    public static Long getLong(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(-?\\d+)");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return Long.parseLong(matcher.group(1));
        }
        return null;
    }
    
    /**
     * Extract integer value from JSON for a given key.
     */
    public static Integer getInteger(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(-?\\d+)");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return null;
    }
    
    /**
     * Extract boolean value from JSON for a given key.
     */
    public static Boolean getBoolean(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(true|false)");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return Boolean.parseBoolean(matcher.group(1));
        }
        return null;
    }
    
    /**
     * Extract double value from JSON for a given key.
     */
    public static Double getDouble(String json, String key) {   
        if (json == null || key == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(-?\\d+(?:\\.\\d+)?)");
        Matcher matcher = pattern.matcher(json);
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
     */
    public static java.math.BigDecimal getBigDecimal(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(-?\\d+(?:\\.\\d+)?)");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return new java.math.BigDecimal(matcher.group(1));
        }
        return null;
    }

    /**
     * Extract nested object as JSON string for a given key.
     */
    public static String getObject(String json, String key) {   
        if (json == null || key == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(\\{[^}]*\\})");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
    
    /**
     * Extract array as JSON string for a given key.
     */
    public static String getArray(String json, String key) {
        if (json == null || key == null) {
            return null;
        }
        String pattern = "\"" + Pattern.quote(key) + "\"\\s*:\\s*(\\[.*?\\])";
        Pattern p = Pattern.compile(pattern, Pattern.DOTALL);
        Matcher matcher = p.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
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
     */
    public static boolean hasValue(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(?!null\\b)");
        return pattern.matcher(json).find();
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
     * Parse timestamp from JSON string.
     */
    public static Timestamp getTimestamp(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z)");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return Timestamp.valueOf(matcher.group(1));
        }
        return null;
    }

    /**
     * Unescape JSON string.
     */
    private static String unescapeJsonString(String escaped) {
        if (escaped == null) return null;
        return escaped
            .replace("\\\"", "\"")
            .replace("\\\\", "\\")
            .replace("\\/", "/")
            .replace("\\b", "\b")
            .replace("\\f", "\f")
            .replace("\\n", "\n")
            .replace("\\r", "\r")
            .replace("\\t", "\t");
    }
}