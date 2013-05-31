package com.chargebee.internal;

import com.chargebee.Environment;
import com.chargebee.Result;
import com.chargebee.internal.HttpUtil.Method;
import java.io.IOException;

public class Request<U extends Request> {

    private HttpUtil.Method httpMeth;

    private String uri;
    
    protected Params params = new Params();

    public Request(Method httpMeth, String uri) {
        this.uri = uri;
        this.httpMeth = httpMeth;
    }

    public U param(String paramName, Object value){
        params.add(paramName, value);
        return (U)this;
    }
    
    protected Params params() {
        return params;
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
                return HttpUtil.get(url, params(), env);
            case POST:
                return HttpUtil.post(url, params(), env);
            case PUT:
                return HttpUtil.put(url, params(), env);
            default:
                throw new RuntimeException("Not handled type [" + httpMeth + "]");
        }
    }

}
