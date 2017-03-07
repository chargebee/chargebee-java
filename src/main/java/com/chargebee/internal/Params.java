package com.chargebee.internal;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Params {

    private Map<String, Object> m = new HashMap<String, Object>();

    public void add(String paramName, Object value) {
        if(value == null) {
            throw new RuntimeException("The param {" + paramName + "} cannot be null");
        }
        m.put(paramName, toValStr(value));
    }

    public void addOpt(String paramName, Object value) {
        m.put(paramName, value != null ? toValStr(value) : "");
    }

    public int size() {
        return m.size();
    }

    public boolean isEmpty() {
        return m.isEmpty();
    }

    public Set<String> keys() {
        return m.keySet();
    }

    public Set<Map.Entry<String, Object>> entries() {
        return m.entrySet();
    }

    public static Object toValStr(Object value) {
        Class c = value.getClass();
        if(c == String.class || c == Integer.class || c == Long.class || c == Boolean.class || c == Double.class) {
            return value.toString();
        } else if (c == Date.class) {
            return new SimpleDateFormat("yyyy-MM-dd").format((Date)value);
        } 
        else if(c.isEnum()) {
            return value.toString().toLowerCase();
        } else if(c == Timestamp.class) {
            return String.valueOf(asUnixTimestamp((Timestamp)value));
        } else if(value instanceof List){
            List origList = ((List)value);
            List<String> l = new ArrayList(origList.size());
            for (Object item : origList) {
                l.add((String)toValStr(item));                
            }
            return l;
        } else if(value instanceof Object[]){
            Object[] origList = ((Object[])value);
            List<String> l = new ArrayList(origList.length);
            for (Object item : origList) {
                l.add((String)toValStr(item));                
            }
            return l;            
        } else if(value instanceof JSONObject) {
            return value.toString();
        } else if(value instanceof JSONArray) {
            return value.toString();
        } else {
            throw new RuntimeException("Type [" + c.getName() + "] not handled");
        }
    }
    
    public static Long asUnixTimestamp(Timestamp ts) {
        return ts.getTime() / 1000;
    }

    @Override
    public String toString() {
        return m.toString();
    }


}
