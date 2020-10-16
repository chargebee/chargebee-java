/*
 * Copyright (c) 2017 ChargeBee Inc
 * All Rights Reserved.
 */
package com.chargebee;

import com.chargebee.internal.RequestBase;
import java.util.concurrent.Callable;

/**
 *
 * @author cb-ajit
 * @param <T>
 */
public abstract class RequestWrap<T extends RequestBase> implements Callable<ApiResponse> {

    public final Environment env;
    public final T request;

    public RequestWrap(Environment env, T request) {
        this.env = env;
        this.request = request;
    }

    public abstract ApiResponse call() throws Exception;

}
