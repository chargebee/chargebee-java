package com.chargebee.v4.core.responses.additionalBillingLogiq;

import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AdditionalBillingLogiqRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class AdditionalBillingLogiqRetrieveResponse {

  private final Response httpResponse;

  private AdditionalBillingLogiqRetrieveResponse(Response httpResponse) {

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into AdditionalBillingLogiqRetrieveResponse object. */
  public static AdditionalBillingLogiqRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AdditionalBillingLogiqRetrieveResponse object with HTTP response. */
  public static AdditionalBillingLogiqRetrieveResponse fromJson(
      String json, Response httpResponse) {
    try {

      return new AdditionalBillingLogiqRetrieveResponse(httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse AdditionalBillingLogiqRetrieveResponse from JSON", e);
    }
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
