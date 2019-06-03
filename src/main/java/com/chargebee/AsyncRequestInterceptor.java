package com.chargebee;

import com.chargebee.internal.AsyncResult;

public interface AsyncRequestInterceptor {

    public AsyncResult handleRequest(AsyncRequestWrap requestWrap) throws Exception;
}
