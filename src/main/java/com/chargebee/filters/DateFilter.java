package com.chargebee.filters;

import static com.chargebee.internal.Params.toValStr;
import com.chargebee.internal.RequestBase;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author sangeetha
 * @param <T>
 * @param <U>
 */
public class DateFilter<T extends  Date, U extends RequestBase> {

    private U req;
    private String paramName;
    private boolean supportsPresenceOperator;

    public DateFilter(String paramName, U req) {
        this.paramName = paramName;
        this.req = req;
    }

    public DateFilter<T, U> supportsPresenceOperator(boolean supportsPresenceOperator) {
        this.supportsPresenceOperator = supportsPresenceOperator;
        return this;
    }

    public U on(T value) {
        req.params().addOpt(paramName + "[on]", value);
        return req;
    }

    public U before(T value) {
        req.params().addOpt(paramName + "[before]", value);
        return req;
    }

    public U after(T value) {
        req.params().addOpt(paramName + "[after]", value);
        return req;
    }

    public U between(T value1, T value2) {
        JSONArray jArr = serialize(Arrays.asList(value1, value2));
        req.params().addOpt(paramName + "[between]", jArr);
        return req;
    }

    public U isPresent(boolean value) {
        if (!supportsPresenceOperator) {
            throw new UnsupportedOperationException("operator '[is_present]' is not supported for this filter-parameter");
        }
        req.params().addOpt(paramName + "[is_present]", value);
        return req;
    }
    
    private JSONArray serialize(List<T> list){
        JSONArray jArr = new JSONArray();
        for(T dateTime : list){
            jArr.put(toValStr(dateTime));
        }
        return jArr;
    }
}
