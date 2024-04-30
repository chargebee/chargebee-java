package com.chargebee.internal;


import com.chargebee.BatchEntry;
import com.chargebee.BatchResult;
import com.chargebee.Environment;
import com.chargebee.Result;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.chargebee.BatchConstants.REQUESTS_KEY;


public class BatchRequest<T extends Request> extends Request<BatchRequest<T>> {

    List<BatchEntry> entries = new ArrayList<BatchEntry>();

    JSONObject jsonPayload = new JSONObject();

    String pathParamName = null;

    public BatchRequest(HttpUtil.Method httpMeth, String uri) {
        super(httpMeth, uri);
        this.uri = "/batch" + uri;
        this.headers.put(HttpUtil.CONTENT_TYPE_HEADER_NAME, "application/json;charset=" + Environment.CHARSET);
    }

    public BatchRequest(HttpUtil.Method httpMeth, String uri, String pathParamName) {
        this(httpMeth, uri);
        this.pathParamName = pathParamName;
    }

    public BatchRequest<T> addEntry(T requestObject) {
        entries.add(new BatchEntry(requestObject));
        return this;
    }

    public BatchRequest<T> addEntry(T requestObject, String correlationId) {
        entries.add(new BatchEntry(requestObject, correlationId));
        return this;
    }

    public BatchRequest<T> addEntries(List<BatchEntry<?>> requestObjects) {
        entries.addAll(requestObjects);
        return this;
    }

    @Override
    public BatchResult request() throws Exception {
        Result result = super.request();
        return new BatchResult(result.httpCode, result.jsonObj(), result.responseHeaders).get();
    }


    @Override
    public BatchResult request(Environment environment) throws Exception {
        Result result = super.request(environment);
        return new BatchResult(result.httpCode, result.jsonObj(), result.responseHeaders).get();
    }

    public String buildRequest() {
        JSONArray batchRequestArray = new JSONArray();
        for (Object batchEntry : this.entries) {
            JSONObject batchRequest = new JSONObject();
            List<Parameter> parameterList = ((BatchEntry) batchEntry).getRequestObject().paramsV2().getParameters();
            for (Parameter p : parameterList) {
                batchRequest = p.toJSONBody(batchRequest);
            }

            if (pathParamName != null) {
                batchRequest.put(pathParamName, ((BatchEntry) batchEntry).getRequestObject().pathParam());
            }

            ((BatchEntry<?>) batchEntry).setParameters(batchRequest);
            batchRequestArray.put(((BatchEntry<?>) batchEntry).getJSON());
        }
        jsonPayload.put(REQUESTS_KEY, batchRequestArray);
        return jsonPayload.toString();
    }

}
