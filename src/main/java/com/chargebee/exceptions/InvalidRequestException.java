/*
 * Copyright (c) 2011 chargebee.com
 * All Rights Reserved.
 */

package com.chargebee.exceptions;

import com.chargebee.APIException;
import org.json.*;
import java.util.List;
import java.util.Map;


public class InvalidRequestException extends APIException{

    public InvalidRequestException(int httpStatusCode, String message, JSONObject jsonObj) {
        super(httpStatusCode, message, jsonObj);
    }
    public InvalidRequestException(int httpStatusCode, String message, JSONObject jsonObj, Map<String, List<String>> responseHeaders) {
        super(httpStatusCode, message, jsonObj, responseHeaders);
    }
    
}
