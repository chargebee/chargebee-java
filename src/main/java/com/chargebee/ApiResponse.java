/*
 * Copyright (c) 2017 ChargeBee Inc
 * All Rights Reserved.
 */
package com.chargebee;

import org.json.JSONObject;

/**
 *
 * @author cb-ajit
 */
public interface ApiResponse {

    public int httpCode();

    public JSONObject jsonResponse();

}
