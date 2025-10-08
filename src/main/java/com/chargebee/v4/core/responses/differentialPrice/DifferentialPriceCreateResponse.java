package com.chargebee.v4.core.responses.differentialPrice;

import com.chargebee.v4.core.models.differentialPrice.DifferentialPrice;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DifferentialPriceCreate operation. Contains the response data from
 * the API.
 */
public final class DifferentialPriceCreateResponse {

  private final DifferentialPrice differentialPrice;

  private final Response httpResponse;

  private DifferentialPriceCreateResponse(Builder builder) {

    this.differentialPrice = builder.differentialPrice;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into DifferentialPriceCreateResponse object. */
  public static DifferentialPriceCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DifferentialPriceCreateResponse object with HTTP response. */
  public static DifferentialPriceCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __differentialPriceJson = JsonUtil.getObject(json, "differential_price");
      if (__differentialPriceJson != null) {
        builder.differentialPrice(DifferentialPrice.fromJson(__differentialPriceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DifferentialPriceCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for DifferentialPriceCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DifferentialPriceCreateResponse. */
  public static class Builder {

    private DifferentialPrice differentialPrice;

    private Response httpResponse;

    private Builder() {}

    public Builder differentialPrice(DifferentialPrice differentialPrice) {
      this.differentialPrice = differentialPrice;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public DifferentialPriceCreateResponse build() {
      return new DifferentialPriceCreateResponse(this);
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
