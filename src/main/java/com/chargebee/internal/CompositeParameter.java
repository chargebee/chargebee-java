package com.chargebee.internal;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CompositeParameter extends Parameter {
    String nestedParameterName;

    public CompositeParameter(String name, String nestedParameterName) {
        this.name = name;
        this.nestedParameterName = nestedParameterName;
    }

    @Override
    Map<String, Object> toFormURLEncoded(Map<String, Object> paramsMap) {
        return new HashMap<String, Object>() {{
            put(name + "[" + nestedParameterName + "]", value);
        }};
    }

    @Override
    JSONObject toJSONBody(JSONObject paramsJSON) {
        return paramsJSON.put(name, new JSONObject().put(nestedParameterName, value));
    }
}
