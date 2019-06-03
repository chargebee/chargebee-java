/*
 * Copyright (c) 2011 chargebee.com
 * All Rights Reserved.
 */

package com.chargebee.internal;

import java.util.*;


public class RequestBase<U extends RequestBase> {

    protected String uri;
    protected Params params = new Params();   
    protected Map<String,String> headers = new HashMap();

    public Params params() {
        return params;
    }

    public Map<String,String> headers() {
        return headers;
    }

    public U header(String headerName,String headerValue){        
        headers.put(headerName, headerValue);
        return (U)this;
    }
    

}
