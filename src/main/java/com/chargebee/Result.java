/*
 * Copyright (c) 2011 chargebee.com
 * All Rights Reserved.
 */
package com.chargebee;

import com.chargebee.internal.ResultBase;
import org.json.JSONObject;

public class Result extends ResultBase {

    public final int httpCode;

    public Result(int httpCode, JSONObject jsonObj) {
        super(jsonObj);
        this.httpCode = httpCode;
    }
}
