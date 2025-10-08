package com.chargebee.v4.core.responses.estimate;

import com.chargebee.v4.core.models.estimate.Estimate;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EstimateRenewalEstimate operation. Contains the response data from
 * a single resource get operation.
 */
public final class EstimateRenewalEstimateResponse {

  private final Estimate estimate;

  private final Response httpResponse;

  private EstimateRenewalEstimateResponse(Estimate estimate, Response httpResponse) {

    this.estimate = estimate;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into EstimateRenewalEstimateResponse object. */
  public static EstimateRenewalEstimateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into EstimateRenewalEstimateResponse object with HTTP response. */
  public static EstimateRenewalEstimateResponse fromJson(String json, Response httpResponse) {
    try {

      Estimate estimate = Estimate.fromJson(JsonUtil.getObject(json, "estimate"));

      return new EstimateRenewalEstimateResponse(estimate, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EstimateRenewalEstimateResponse from JSON", e);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
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
