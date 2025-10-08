package com.chargebee.v4.core.responses.promotionalCredit;

import com.chargebee.v4.core.models.promotionalCredit.PromotionalCredit;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PromotionalCreditRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class PromotionalCreditRetrieveResponse {

  private final PromotionalCredit promotionalCredit;

  private final Response httpResponse;

  private PromotionalCreditRetrieveResponse(
      PromotionalCredit promotionalCredit, Response httpResponse) {

    this.promotionalCredit = promotionalCredit;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into PromotionalCreditRetrieveResponse object. */
  public static PromotionalCreditRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PromotionalCreditRetrieveResponse object with HTTP response. */
  public static PromotionalCreditRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      PromotionalCredit promotionalCredit =
          PromotionalCredit.fromJson(JsonUtil.getObject(json, "promotional_credit"));

      return new PromotionalCreditRetrieveResponse(promotionalCredit, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PromotionalCreditRetrieveResponse from JSON", e);
    }
  }

  /** Get the promotionalCredit from the response. */
  public PromotionalCredit getPromotionalCredit() {
    return promotionalCredit;
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
