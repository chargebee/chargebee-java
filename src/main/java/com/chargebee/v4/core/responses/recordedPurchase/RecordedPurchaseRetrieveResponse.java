package com.chargebee.v4.core.responses.recordedPurchase;

import com.chargebee.v4.core.models.recordedPurchase.RecordedPurchase;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RecordedPurchaseRetrieve operation. Contains the response data from
 * a single resource get operation.
 */
public final class RecordedPurchaseRetrieveResponse {

  private final RecordedPurchase recordedPurchase;

  private final Response httpResponse;

  private RecordedPurchaseRetrieveResponse(
      RecordedPurchase recordedPurchase, Response httpResponse) {

    this.recordedPurchase = recordedPurchase;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into RecordedPurchaseRetrieveResponse object. */
  public static RecordedPurchaseRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RecordedPurchaseRetrieveResponse object with HTTP response. */
  public static RecordedPurchaseRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      RecordedPurchase recordedPurchase =
          RecordedPurchase.fromJson(JsonUtil.getObject(json, "recorded_purchase"));

      return new RecordedPurchaseRetrieveResponse(recordedPurchase, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RecordedPurchaseRetrieveResponse from JSON", e);
    }
  }

  /** Get the recordedPurchase from the response. */
  public RecordedPurchase getRecordedPurchase() {
    return recordedPurchase;
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
