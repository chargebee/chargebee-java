package com.chargebee.v4.core.responses.omnichannelSubscription;

import com.chargebee.v4.core.models.omnichannelSubscription.OmnichannelSubscription;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OmnichannelSubscriptionRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class OmnichannelSubscriptionRetrieveResponse {

  private final OmnichannelSubscription omnichannelSubscription;

  private final Response httpResponse;

  private OmnichannelSubscriptionRetrieveResponse(
      OmnichannelSubscription omnichannelSubscription, Response httpResponse) {

    this.omnichannelSubscription = omnichannelSubscription;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into OmnichannelSubscriptionRetrieveResponse object. */
  public static OmnichannelSubscriptionRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OmnichannelSubscriptionRetrieveResponse object with HTTP response. */
  public static OmnichannelSubscriptionRetrieveResponse fromJson(
      String json, Response httpResponse) {
    try {

      OmnichannelSubscription omnichannelSubscription =
          OmnichannelSubscription.fromJson(JsonUtil.getObject(json, "omnichannel_subscription"));

      return new OmnichannelSubscriptionRetrieveResponse(omnichannelSubscription, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelSubscriptionRetrieveResponse from JSON", e);
    }
  }

  /** Get the omnichannelSubscription from the response. */
  public OmnichannelSubscription getOmnichannelSubscription() {
    return omnichannelSubscription;
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
