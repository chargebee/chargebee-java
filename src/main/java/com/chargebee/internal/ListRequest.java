package com.chargebee.internal;

import com.chargebee.Environment;
import com.chargebee.ListResult;
import com.chargebee.filters.*;
import java.io.IOException;
import java.util.Date;

public class ListRequest<U extends ListRequest> extends RequestBase<U>{

    
    public ListRequest(String uri) {
        this.uri = uri;
    }

    public U limit(int limit) {
        params.addOpt("limit", limit);
        return (U)this;
    }

    public U offset(String offset) {
        params.addOpt("offset", offset);
        return (U)this;
    }
    
    public StringFilter<U> stringFilterParam(String paramName){
        return new StringFilter<U>(paramName, (U)this).supportsPresenceOperator(true).supportsMultiOperators(true);
    }
    
    public BooleanFilter<U> booleanFilterParam(String paramName){
        return new BooleanFilter<U>(paramName, (U)this).supportsPresenceOperator(true);
    }
    
    public NumberFilter<Long, U> longFilterParam(String paramName) {
        return new NumberFilter<Long, U>(paramName, (U) this).supportsPresenceOperator(true);
    }
    
    public TimestampFilter<U> timestampFilterParam(String paramName){
        return new TimestampFilter<U>(paramName, (U)this).supportsPresenceOperator(true);
    }
    
    public DateFilter<Date, U> dateFilterParam(String paramName){
        return new DateFilter<Date, U>(paramName, (U)this).supportsPresenceOperator(true);
    }
    
    public final ListResult request() throws IOException {
        return request(Environment.defaultConfig());
    }

    public final ListResult request(Environment env) throws IOException {
        if(env == null) {
            throw new RuntimeException("Environment cannot be null");
        }
        String url = new StringBuilder(env.apiBaseUrl()).append(uri).toString();
        return HttpUtil.getList(url, params(),headers, env);
    }
    
    @Override
    public Params params() {
        return params;
    }

}
