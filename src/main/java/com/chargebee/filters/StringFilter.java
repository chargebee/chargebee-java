package com.chargebee.filters;

import com.chargebee.internal.Request;
import com.chargebee.internal.RequestBase;
import java.util.Arrays;
import org.json.JSONArray;

/**
 *
 * @author sangeetha
 * @param <U>
 */
public class StringFilter<U extends RequestBase>{

    private U req;
    private String paramName;
    private boolean supportsMultiOperators;
    private boolean supportsPresenceOperator;

    public StringFilter(String paramName, U req) {
        this.paramName = paramName;
        this.req = req;
        this.supportsPresenceOperator = true;
    }

    public StringFilter<U> supportsPresenceOperator(boolean supportsPresenceOperator) {
        this.supportsPresenceOperator = supportsPresenceOperator;
        return this;
    }

    public StringFilter<U> supportsMultiOperators(boolean supportsMultiOperators) {
        this.supportsMultiOperators = supportsMultiOperators;
        return this;
    }

    public U is(String value) {
        req.params().addOpt(paramName + "[is]", value);
        return req;
    }

    public U isNot(String value) {
        req.params().addOpt(paramName + "[is_not]", value);
        return req;
    }

    public U startsWith(String value) {
        req.params().addOpt(paramName + "[starts_with]", value);
        return req;
    }

    public U isPresent(boolean value) {
        if (!supportsPresenceOperator) {
            throw new UnsupportedOperationException("operator '[is_present]' is not supported for this filter-parameter");
        }
        req.params().addOpt(paramName + "[is_present]", value);
        return req;
    }

    public U in(String... value) {
        if (!supportsMultiOperators) {
            throw new UnsupportedOperationException("operator '[in]' is not supported for this filter-parameter");
        }
        JSONArray jArr = new JSONArray(Arrays.asList(value));
        req.params().addOpt(paramName + "[in]", jArr);
        return req;
    }

    public U notIn(String... value) {
        if (!supportsMultiOperators) {
            throw new UnsupportedOperationException("operator '[not_in]' is not supported for this filter-parameter");
        }
        JSONArray jArr = new JSONArray(Arrays.asList(value));
        req.params().addOpt(paramName + "[not_in]", jArr);
        return req;
    }
}
