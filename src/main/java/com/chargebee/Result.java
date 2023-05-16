package com.chargebee;

import com.chargebee.internal.ResultBase;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.chargebee.IdempotencyConstants.IDEMPOTENCY_REPLAY_HEADER;

public class Result extends ResultBase implements ApiResponse {

    public final int httpCode;
    public Map<String, List<String>> responseHeaders;

    public Result(int httpCode, JSONObject jsonObj) {
        super(jsonObj);
        this.httpCode = httpCode;
    }

    public Result(int httpCode, JSONObject jsonObj, Map<String, List<String>> responseHeaders) {
        this(httpCode,jsonObj);
        this.responseHeaders = responseHeaders;
    }

    public int httpCode() {
        return httpCode;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }

    public boolean isIdempotencyReplayed() {
        Map<String, List<String>> headers = responseHeaders;
        Optional<String> replayedHeader = headers.entrySet().stream()
            .filter(entry -> IDEMPOTENCY_REPLAY_HEADER.equalsIgnoreCase(entry.getKey()))
            .findFirst()
            .flatMap(entry -> entry.getValue().stream().findFirst());

        return replayedHeader.isPresent();
    }
    
    public JSONObject jsonResponse() {
        return jsonObj();
    }
}
