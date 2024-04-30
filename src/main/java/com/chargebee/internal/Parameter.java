package com.chargebee.internal;

import org.json.JSONObject;

import java.util.Map;

public abstract class Parameter {

    String name;

    Object value;

    public void setValue(Object value) {
        this.value = value;
    }

    abstract Map<String, Object> toFormURLEncoded(Map<String, Object> paramsMap);

    abstract JSONObject toJSONBody(JSONObject requestJSON);
}
