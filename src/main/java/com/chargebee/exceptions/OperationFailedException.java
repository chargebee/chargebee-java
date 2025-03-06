/*
 * Copyright (c) 2011 chargebee.com
 * All Rights Reserved.
 */

package com.chargebee.exceptions;

import com.chargebee.APIException;
import org.json.*;
import java.util.List;
import java.util.Map;


public class OperationFailedException extends APIException{

    public OperationFailedException(int httpStatusCode, String message, JSONObject jsonObj) {
        super(httpStatusCode, message, jsonObj);
    }
    public OperationFailedException(int httpStatusCode, String message, JSONObject jsonObj, Map<String, List<String>> responseHeaders) {
        super(httpStatusCode, message, jsonObj, responseHeaders);
    }

}
