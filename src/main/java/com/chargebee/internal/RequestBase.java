/*
 * Copyright (c) 2011 chargebee.com
 * All Rights Reserved.
 */

package com.chargebee.internal;

import java.util.*;

import static com.chargebee.IdempotencyConstants.IDEMPOTENCY_HEADER;

public class RequestBase<U extends RequestBase> {

    protected String uri;
    protected Params params = new Params();
    protected Map<String,String> headers = new HashMap();

    public U setIdempotencyKey(String idempotencyKey){
        headers.put(IDEMPOTENCY_HEADER, idempotencyKey);
        return (U)this;
    }

    public Params params() {
        return params;
    }

    public U header(String headerName,String headerValue){
        headers.put(headerName, headerValue);
        return (U)this;
    }

    public String uri() {
        return uri;
    }

    public Map<String, String> headers() {
        return headers;
    }
}
