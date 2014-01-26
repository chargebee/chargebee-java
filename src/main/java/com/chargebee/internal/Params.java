package com.chargebee.internal;

import java.sql.Timestamp;
import java.util.*;

public class Params {

    private Map<String, String> m = new HashMap<String, String>();

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

    public Set<Map.Entry<String, String>> entries() {
        return m.entrySet();
    }

    private static String toValStr(Object value) {
        Class c = value.getClass();
        if(c == String.class || c == Integer.class || c == Long.class || c == Boolean.class || c == Double.class) {
            return value.toString();
        } else if(c.isEnum()) {
            return value.toString().toLowerCase();
        } else if(c == Timestamp.class) {
            return asUnixTimestamp((Timestamp)value);
        } else {
            throw new RuntimeException("Type [" + c.getName() + "] not handled");
        }
    }
    
    private static String asUnixTimestamp(Timestamp ts) {
        return String.valueOf(ts.getTime() / 1000);
    }

    @Override
    public String toString() {
        return m.toString();
    }


}
