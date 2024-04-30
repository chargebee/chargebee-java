package com.chargebee;

import com.chargebee.internal.Params;
import com.chargebee.internal.Request;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

import static com.chargebee.BatchConstants.CORRELATION_ID;


public class BatchEntry<T extends Request> {

    String correlationId;

    public void setParameters(JSONObject parameters) {
        this.parameters = parameters;
    }

    JSONObject parameters;

    public Request<T> getRequestObject() {
        return requestObject;
    }

    Request<T> requestObject;

    public BatchEntry(Request<T> requestObject, String correlationId) {
        this(requestObject);
        this.correlationId = correlationId;
    }

    public BatchEntry(Request<T> requestObject) {
        this.requestObject = requestObject;
        this.parameters = jsonParams(requestObject.params());
    }

    private JSONObject jsonParams(Params params) {
        JSONObject paramJson = new JSONObject();
        Iterator paramIterator = params.entries().iterator();
        while (paramIterator.hasNext()) {
            Map.Entry paramMap = (Map.Entry) paramIterator.next();
            paramJson.put(paramMap.getKey().toString(), paramMap.getValue());
        }
        return paramJson;
    }

    public JSONObject getJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("parameters", this.parameters);
        if(this.correlationId != null ) {
            jsonObject.put(CORRELATION_ID, this.correlationId);
        }
        return jsonObject;
    }
}
