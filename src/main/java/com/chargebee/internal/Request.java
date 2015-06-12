package com.chargebee.internal;

import com.chargebee.*;
import com.chargebee.internal.HttpUtil.Method;
import java.io.*;
import java.util.*;

public class Request<U extends Request> extends RequestBase<U>{

    private final HttpUtil.Method httpMeth;
    
    public Request(Method httpMeth, String uri) {
        this.uri = uri;
        this.httpMeth = httpMeth;
    }

    public U param(String paramName, Object value){
        params.add(paramName, value);
        return (U)this;
    }
        
    public final Result request() throws IOException {
        return request(Environment.defaultConfig());
    }

    public final Result request(Environment env) throws IOException {
        if(env == null) {
            throw new RuntimeException("Environment cannot be null");
        }
        String url = new StringBuilder(env.apiBaseUrl()).append(uri).toString();
        switch(httpMeth) {
            case GET:
                return HttpUtil.get(url, params(),headers, env);
            case POST:
                return HttpUtil.post(url, params(),headers, env);
            default:
                throw new RuntimeException("Not handled type [" + httpMeth + "]");
        }
    }

}
