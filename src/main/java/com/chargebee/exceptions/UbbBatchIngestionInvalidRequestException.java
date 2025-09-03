/*
 * Copyright (c) 2011 chargebee.com
 * All Rights Reserved.
 */

package com.chargebee.exceptions;

import com.chargebee.APIException;
import org.json.*;
import java.util.List;
import java.util.Map;


public class UbbBatchIngestionInvalidRequestException extends APIException{ 
    public final String batchId;
    public final JSONArray failedEvents;
    public UbbBatchIngestionInvalidRequestException(int httpStatusCode, String message, JSONObject jsonObj, Map<String, List<String>> headers) {
        super(httpStatusCode, message, jsonObj);
        this.batchId=jsonObj.optString("batch_id");
        this.failedEvents=jsonObj.optJSONArray("failed_events");
    }
}