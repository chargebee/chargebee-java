package com.chargebee.filters;

import com.chargebee.internal.ListRequest;
import java.sql.Timestamp;

/**
 *
 * @author sangeetha
 * @param <U>
 */
public class TimestampFilter<U extends ListRequest> extends DateFilter<Timestamp, U> {

    public TimestampFilter(String paramName, U req) {
        super(paramName, req);
    }
    
    @Override
    public TimestampFilter<U> supportsPresenceOperator(boolean supportsPresenceOperator) {
        super.supportsPresenceOperator(supportsPresenceOperator);
        return this;
    }
}
