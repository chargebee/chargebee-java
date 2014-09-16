/*
 * Copyright (c) 2011 chargebee.com
 * All Rights Reserved.
 */

package com.chargebee.exceptions;

import com.chargebee.APIException;
import org.json.*;


public class InvalidRequestException extends APIException{

    public InvalidRequestException(int httpStatusCode,JSONObject jsonObj) throws Exception {
        super(httpStatusCode,jsonObj);
    }
    
}
