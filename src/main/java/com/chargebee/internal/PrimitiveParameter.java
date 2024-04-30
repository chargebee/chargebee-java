package com.chargebee.internal;

import org.json.JSONObject;

import java.util.Map;

public class PrimitiveParameter extends Parameter {

    public PrimitiveParameter(String name) {
        this.name = name;
    }

    @Override
    Map<String, Object> toFormURLEncoded(Map<String, Object> paramsMap) {
        paramsMap.put(name, value);
        return paramsMap;
    }

    @Override
    JSONObject toJSONBody(JSONObject paramsJSON) {
        paramsJSON.put(name, value);
        return paramsJSON;
    }
}
