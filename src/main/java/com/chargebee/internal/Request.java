package com.chargebee.internal;

import com.chargebee.*;
import com.chargebee.internal.HttpUtil.Method;

import java.io.*;

public class Request<U extends Request> extends RequestBase<U>{

    private final HttpUtil.Method httpMeth;
    private String pathParam = null;
    public Request(Method httpMeth, String uri) {
        this.uri = uri;
        this.httpMeth = httpMeth;
    }

    public Request(Method httpMeth, String uri, String pathParam) {
        this(httpMeth, uri);
        this.pathParam = pathParam;
    }

    public U param(String paramName, Object value){
        params.add(paramName, value);
        return (U)this;
    }
        
    public Result request() throws Exception {
        return request(Environment.defaultConfig());
    }

    public Result request(Environment env) throws Exception {
        RequestWrap c = new RequestWrap<Request>(env, this) {

            @Override
            public Result call() throws Exception {
                return _request(env, request);
            }
        };
        return (Result) (env.reqInterceptor() != null ? env.reqInterceptor().handleRequest(c) : c.call());
    }

    private static Result _request(Environment env, Request<?> req) throws IOException {
        if (env == null) {
            throw new RuntimeException("Environment cannot be null");
        }
        String url = new StringBuilder(env.apiBaseUrl()).append(req.uri).toString();
        switch (req.httpMeth) {
            case GET:
                return HttpUtil.get(url, req.params(), req.headers, env);
            case POST:
                if(req instanceof BatchRequest) {
                    return HttpUtil.post(url, ((BatchRequest<?>) req).buildRequest(), req.headers, env);
                } else {
                    return HttpUtil.post(url, req.params(), req.headers, env);
                }
            default:
                throw new RuntimeException("Not handled type [" + req.httpMeth + "]");
        }
    }
    
    @Override
    public Params params() {
        return params;
    }

    public HttpUtil.Method httpMeth() {
        return httpMeth;
    }

    public String pathParam() {
        return pathParam;
    }
}
