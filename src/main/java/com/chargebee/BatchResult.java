package com.chargebee;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.chargebee.BatchConstants.*;

public class BatchResult extends Result {
    List<BatchResponse> results = new ArrayList<>();

    public BatchResult(int httpCode, JSONObject jsonObj) {
        super(httpCode, jsonObj);
    }
    public List<BatchResponse> getResults() {
        return results;
    }

    public BatchResult(int httpCode, JSONObject jsonObj, Map<String, List<String>> responseHeaders) {
        super(httpCode, jsonObj, responseHeaders);
    }

    public BatchResult get() {
        JSONArray resultArray = this.jsonResponse().optJSONArray(RESULTS_KEY);
        for (int i = 0; i < resultArray.length(); i++) {
            JSONObject currentJson = resultArray.getJSONObject(i);
            results.add(new BatchResponse(currentJson.optString(CORRELATION_ID), new Result(this.httpCode, currentJson.optJSONObject(RESULT_CONTENT_KEY), this.responseHeaders)));
        }
        return this;
    }

    public class BatchResponse {
        String correlationId;
        Result content;

        public String getCorrelationId() {
            return correlationId;
        }

        public void setCorrelationId(String correlationId) {
            this.correlationId = correlationId;
        }

        public Result getContent() {
            return content;
        }

        public void setContent(Result content) {
            this.content = content;
        }

        public BatchResponse(String correlationId, Result data) {
            this.correlationId = correlationId;
            this.content = data;
        }
    }

}
