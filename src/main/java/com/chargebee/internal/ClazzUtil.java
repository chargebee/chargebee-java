package com.chargebee.internal;

import org.json.JSONObject;


/**
 *
 * @author kps
 */
public class ClazzUtil {

    public static String getObjType(Class claz){
        return  toUnderScores(claz.getSimpleName());
    }

    public static Class getClaz(String objType){
        String pkg = "com.chargebee.models.";
        try {
            return Class.forName(pkg + toCamelCase(objType));
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Unknown obj type " + objType,ex);
        }
    }

    public static String toCamelCase(String name) {
        return toCamelCase(name.split("_"));
    }

    public static String toCamelCase(String[] parts) {
        StringBuilder buff = new StringBuilder();
        for (String part : parts) {
            if (part.isEmpty()) {
                continue;
            }
            buff.append(Character.toUpperCase(part.charAt(0)));
            buff.append(part.substring(1));
        }
        return buff.toString();
    }

    public static String toUnderScores(String camelCaseName) {
        StringBuilder buf = new StringBuilder(camelCaseName.length() + 5);
        buf.append(Character.toLowerCase(camelCaseName.charAt(0)));
        for (int i = 1; i < camelCaseName.length(); i++) {
            char c = camelCaseName.charAt(i);
            if (Character.isUpperCase(c)) {
                buf.append('_');
                c = Character.toLowerCase(c);
            }
            buf.append(c);
        }
        return buf.toString();
    }

    public static <T> T createInstance(Class<T> claz, JSONObject _jobj) {
        try {
            return claz.getDeclaredConstructor(JSONObject.class).newInstance(_jobj);
        } catch(Exception exp) {
            throw new RuntimeException(exp);
        }
    }


    public static <T> T createNumberInstance(Class<T> claz, String val) {
        try {
            return claz.getConstructor(String.class).newInstance(val);
        } catch(Exception exp) {
            throw new RuntimeException(exp);
        }
    }
    
}
