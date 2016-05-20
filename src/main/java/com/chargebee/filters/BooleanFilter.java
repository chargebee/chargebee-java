package com.chargebee.filters;

import com.chargebee.internal.ListRequest;

/**
 *
 * @author sangeetha
 * @param <T>
 * @param <U>
 */
public class BooleanFilter<U extends ListRequest> {

    private U req;
    private String paramName;

    public BooleanFilter(String paramName, U req) {
        this.paramName = paramName;
        this.req = req;
    }

    public U is(Boolean value) {
        req.params().addOpt(paramName + "[is]", value);
        return req;
    }

}
