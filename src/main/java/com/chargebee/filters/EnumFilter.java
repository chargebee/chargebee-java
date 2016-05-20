package com.chargebee.filters;

import com.chargebee.internal.ListRequest;

/**
 *
 * @author sangeetha
 * @param <T> The enum type
 * @param <U>
 */
public class EnumFilter<T, U extends ListRequest> {

    private U req;
    private String paramName;
    private boolean supportsPresenceOperator;

    public EnumFilter(String paramName, U req) {
        this.paramName = paramName;
        this.req = req;
    }

    public EnumFilter<T, U> supportsPresenceOperator(boolean supportsPresenceOperator) {
        this.supportsPresenceOperator = supportsPresenceOperator;
        return this;
    }

    public U is(T value) {
        req.params().addOpt(paramName + "[is]", value);
        return req;
    }

    public U isNot(T value) {
        req.params().addOpt(paramName + "[is_not]", value);
        return req;
    }

    public U in(T... value) {
        req.params().addOpt(paramName + "[in]", value);
        return req;
    }

    public U notIn(T... value) {
        req.params().addOpt(paramName + "[not_in]", value);
        return req;
    }

    public U isPresent(boolean value) {
        if (!supportsPresenceOperator) {
            throw new UnsupportedOperationException("operator '[is_present]' is not supported for this filter-parameter");
        }
        req.params().addOpt(paramName + "[is_present]", value);
        return req;
    }

}
