package com.chargebee.filters;

import com.chargebee.internal.RequestBase;

/**
 *
 * @author sangeetha
 * @param <U>
 */
public class BooleanFilter<U extends RequestBase> {

    private U req;
    private String paramName;
    private boolean supportsPresenceOperator;

    public BooleanFilter(String paramName, U req) {
        this.paramName = paramName;
        this.req = req;
    }

    public U is(Boolean value) {
        req.params().addOpt(paramName + "[is]", value);
        return req;
    }
    
    public BooleanFilter<U> supportsPresenceOperator(boolean supportsPresenceOperator) {
        this.supportsPresenceOperator = supportsPresenceOperator;
        return this;
    }
    
    public U isPresent(boolean value) {
        if (!supportsPresenceOperator) {
            throw new UnsupportedOperationException("operator '[is_present]' is not supported for this filter-parameter");
        }
        req.params().addOpt(paramName + "[is_present]", value);
        return req;
    }

}
