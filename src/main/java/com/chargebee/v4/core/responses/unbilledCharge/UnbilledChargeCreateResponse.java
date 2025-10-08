package com.chargebee.v4.core.responses.unbilledCharge;

import java.util.List;

import com.chargebee.v4.core.models.unbilledCharge.UnbilledCharge;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UnbilledChargeCreate operation. Contains the response data from the
 * API.
 */
public final class UnbilledChargeCreateResponse {

  private final List<UnbilledCharge> unbilledCharges;

  private final Response httpResponse;

  private UnbilledChargeCreateResponse(Builder builder) {

    this.unbilledCharges = builder.unbilledCharges;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into UnbilledChargeCreateResponse object. */
  public static UnbilledChargeCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UnbilledChargeCreateResponse object with HTTP response. */
  public static UnbilledChargeCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.unbilledCharges(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "unbilled_charges")).stream()
              .map(UnbilledCharge::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UnbilledChargeCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for UnbilledChargeCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UnbilledChargeCreateResponse. */
  public static class Builder {

    private List<UnbilledCharge> unbilledCharges;

    private Response httpResponse;

    private Builder() {}

    public Builder unbilledCharges(List<UnbilledCharge> unbilledCharges) {
      this.unbilledCharges = unbilledCharges;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public UnbilledChargeCreateResponse build() {
      return new UnbilledChargeCreateResponse(this);
    }
  }

  /** Get the unbilledCharges from the response. */
  public List<UnbilledCharge> getUnbilledCharges() {
    return unbilledCharges;
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
