package com.chargebee.internal;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CompositeArrayParameter extends CompositeParameter {
    Integer index;

    public CompositeArrayParameter(String name, String nestedParameterName, Integer index) {
        super(name, nestedParameterName);
        this.index = index;
    }

    @Override
    Map<String, Object> toFormURLEncoded(Map<String, Object> paramsMap) {
        return new HashMap<String, Object>() {{
            put(name + "[" + nestedParameterName + "][" + index + "]", value);
        }};
    }

    @Override
    JSONObject toJSONBody(JSONObject paramsJSON) {
        JSONArray existingArray = paramsJSON.opt(name) != null ? paramsJSON.optJSONArray(name) : new JSONArray();
        if (index > 0 && existingArray.opt(index - 1) == null) {
            throw new RuntimeException("Please add items to the array with ordered array indices");
        }
        JSONObject existingArrayItem = existingArray.optJSONObject(index) != null ? existingArray.optJSONObject(index) : new JSONObject();
        existingArrayItem.put(nestedParameterName, value);
        existingArray.put(index, existingArrayItem);
        paramsJSON.put(name, existingArray);
        return paramsJSON;
    }
}
