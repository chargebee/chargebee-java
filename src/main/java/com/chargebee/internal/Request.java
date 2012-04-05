/*
 * Copyright (c) 2011 chargebee.com
 * All Rights Reserved.
 */
package com.chargebee.internal;

import com.chargebee.Environment;
import com.chargebee.Result;
import com.chargebee.internal.HttpUtil.Method;
import java.io.IOException;

public class Request {

    private HttpUtil.Method httpMeth;

    private String url;

    public Request(Method httpMeth, String url) {
        this.url = url;
        this.httpMeth = httpMeth;
    }

    public final Result request() throws IOException {
        return request(Environment.defaultConfig());
    }

    public final Result request(Environment env) throws IOException {
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

    protected Params params() {
        return Params.EMPTY;
    }
}
