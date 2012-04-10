package com.chargebee.internal;

/**
 * Util class to create concatenated string !!
 */
public class StringJoiner {

    private StringBuilder buff = new StringBuilder();
    private String delim;

    public StringJoiner(String delim) {
        this.delim = delim;
    }

    public boolean isEmpty() {
        return buff.length() == 0;
    }

    public StringJoiner add(String element) {
        if(buff.length() != 0) { // not the first-time
            buff.append(delim);
        }
        if(element == null || element.isEmpty()) { // empty
            throw new IllegalArgumentException("Cannot add null/empty element");
        }
        buff.append(element);
        return this;
    }

    @Override
    public String toString() {
        return buff.toString();
    }
}

