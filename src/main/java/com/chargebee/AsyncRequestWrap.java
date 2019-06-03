package com.chargebee;

import com.chargebee.internal.HttpUtil;
import com.chargebee.internal.ListRequest;
import com.chargebee.internal.RequestBase;

public class AsyncRequestWrap<T extends RequestBase> {
    public final Environment env;
    public final T request;
    public final boolean isListRequest;
    public final HttpUtil.Method method;
    public final String uri;

    public AsyncRequestWrap(Environment env, T request, HttpUtil.Method method, String uri) {
        this.env = env;
        this.request = request;
        this.method = method;
        this.isListRequest = request instanceof ListRequest;
        this.uri = uri;
    }
}
