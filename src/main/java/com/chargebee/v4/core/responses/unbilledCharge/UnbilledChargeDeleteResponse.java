package com.chargebee.v4.core.responses.unbilledCharge;

import com.chargebee.v4.core.models.unbilledCharge.UnbilledCharge;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UnbilledChargeDelete operation. Contains the response data from the
 * API.
 */
public final class UnbilledChargeDeleteResponse {

  private final UnbilledCharge unbilledCharge;

  private final Response httpResponse;

  private UnbilledChargeDeleteResponse(Builder builder) {

    this.unbilledCharge = builder.unbilledCharge;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into UnbilledChargeDeleteResponse object. */
  public static UnbilledChargeDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UnbilledChargeDeleteResponse object with HTTP response. */
  public static UnbilledChargeDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __unbilledChargeJson = JsonUtil.getObject(json, "unbilled_charge");
      if (__unbilledChargeJson != null) {
        builder.unbilledCharge(UnbilledCharge.fromJson(__unbilledChargeJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UnbilledChargeDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for UnbilledChargeDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UnbilledChargeDeleteResponse. */
  public static class Builder {

    private UnbilledCharge unbilledCharge;

    private Response httpResponse;

    private Builder() {}

    public Builder unbilledCharge(UnbilledCharge unbilledCharge) {
      this.unbilledCharge = unbilledCharge;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public UnbilledChargeDeleteResponse build() {
      return new UnbilledChargeDeleteResponse(this);
    }
  }

  /** Get the unbilledCharge from the response. */
  public UnbilledCharge getUnbilledCharge() {
    return unbilledCharge;
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
