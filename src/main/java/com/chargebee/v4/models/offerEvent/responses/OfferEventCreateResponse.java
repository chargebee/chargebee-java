package com.chargebee.v4.models.offerEvent.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OfferEventCreate operation. Contains the response data from the
 * API.
 */
public final class OfferEventCreateResponse extends BaseResponse {

  private OfferEventCreateResponse(Builder builder) {
    super(builder.httpResponse);
  }

  /** Parse JSON response into OfferEventCreateResponse object. */
  public static OfferEventCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OfferEventCreateResponse object with HTTP response. */
  public static OfferEventCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OfferEventCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for OfferEventCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OfferEventCreateResponse. */
  public static class Builder {

    private Response httpResponse;

    private Builder() {}

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public OfferEventCreateResponse build() {
      return new OfferEventCreateResponse(this);
    }
  }
}
