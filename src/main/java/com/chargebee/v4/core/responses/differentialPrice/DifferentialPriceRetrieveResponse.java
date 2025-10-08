package com.chargebee.v4.core.responses.differentialPrice;

import com.chargebee.v4.core.models.differentialPrice.DifferentialPrice;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DifferentialPriceRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class DifferentialPriceRetrieveResponse {

  private final DifferentialPrice differentialPrice;

  private final Response httpResponse;

  private DifferentialPriceRetrieveResponse(
      DifferentialPrice differentialPrice, Response httpResponse) {

    this.differentialPrice = differentialPrice;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into DifferentialPriceRetrieveResponse object. */
  public static DifferentialPriceRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DifferentialPriceRetrieveResponse object with HTTP response. */
  public static DifferentialPriceRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      DifferentialPrice differentialPrice =
          DifferentialPrice.fromJson(JsonUtil.getObject(json, "differential_price"));

      return new DifferentialPriceRetrieveResponse(differentialPrice, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DifferentialPriceRetrieveResponse from JSON", e);
    }
  }

  /** Get the differentialPrice from the response. */
  public DifferentialPrice getDifferentialPrice() {
    return differentialPrice;
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
