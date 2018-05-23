package com.chargebee.filters;

import com.chargebee.internal.RequestBase;
import java.sql.Timestamp;

/**
 *
 * @author sangeetha
 * @param <U>
 */
public class TimestampFilter<U extends RequestBase> extends DateFilter<Timestamp, U> {

    public TimestampFilter(String paramName, U req) {
        super(paramName, req);
    }
    
    @Override
    public TimestampFilter<U> supportsPresenceOperator(boolean supportsPresenceOperator) {
        super.supportsPresenceOperator(supportsPresenceOperator);
        return this;
    }
}
