/*
 * Copyright (c) 2017 ChargeBee Inc
 * All Rights Reserved.
 */
package com.chargebee;

import com.chargebee.internal.RequestBase;

/**
 *
 * @author cb-ajit
 */
public interface RequestInterceptor {

    public ApiResponse handleRequest(RequestWrap<? extends RequestBase> requestWrap) throws Exception;

}
