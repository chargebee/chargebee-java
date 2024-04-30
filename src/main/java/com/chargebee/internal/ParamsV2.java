package com.chargebee.internal;

import java.util.ArrayList;
import java.util.List;

public class ParamsV2 {

    private List<Parameter> parameters = new ArrayList<Parameter>();

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void add(Parameter p, Object value) {
        if(value == null) {
            throw new RuntimeException("The param {" + p.name + "} cannot be null");
        }
        p.setValue(Params.toValStr(value));
        parameters.add(p);
    }

    public void addOpt(Parameter p, Object value) {
        p.setValue(value == null ? "" : Params.toValStr(value));
        parameters.add(p);
    }


}
