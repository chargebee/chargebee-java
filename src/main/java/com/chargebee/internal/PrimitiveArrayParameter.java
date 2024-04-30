package com.chargebee.internal;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class PrimitiveArrayParameter extends Parameter {
    Integer index;
    public PrimitiveArrayParameter(String name, Integer index) {
        this.name = name;
        this.index = index;
    }
    @Override
    Map<String, Object> toFormURLEncoded(Map<String, Object> paramsMap) {
        paramsMap.put(name + "[" + index + "]", value);
        return paramsMap;
    }

    @Override
    JSONObject toJSONBody(JSONObject paramsJSON) {
        JSONArray existingArray = paramsJSON.opt(name) != null ? paramsJSON.optJSONArray(name) : new JSONArray();
        existingArray.put(index, value);
        paramsJSON.put(name, existingArray);
        return paramsJSON;
    }


}
