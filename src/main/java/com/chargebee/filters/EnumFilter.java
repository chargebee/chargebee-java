package com.chargebee.filters;

import com.chargebee.internal.Params;
import com.chargebee.internal.RequestBase;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author sangeetha
 * @param <T> The enum type
 * @param <U>
 */
public class EnumFilter<T, U extends RequestBase> {

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
        JSONArray jArr = serialize(Arrays.asList(value));
        req.params().addOpt(paramName + "[in]", jArr);
        return req;
    }

    public U notIn(T... value) {
        JSONArray jArr = serialize(Arrays.asList(value));
        req.params().addOpt(paramName + "[not_in]", jArr);
        return req;
    }

    public U isPresent(boolean value) {
        if (!supportsPresenceOperator) {
            throw new UnsupportedOperationException("operator '[is_present]' is not supported for this filter-parameter");
        }
        req.params().addOpt(paramName + "[is_present]", value);
        return req;
    }
    
    private JSONArray serialize(List<T> list) {
        JSONArray jArr = new JSONArray();
        for (T val : list) {
            jArr.put(Params.toValStr(val));
        }
        return jArr;
    }

}
