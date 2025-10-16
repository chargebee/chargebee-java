package com.chargebee.v4.core.responses.offerEvent;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OfferEventOfferEvents operation. Contains the response data from
 * the API.
 */
public final class OfferEventOfferEventsResponse extends BaseResponse {

  private OfferEventOfferEventsResponse(Builder builder) {
    super(builder.httpResponse);
  }

  /** Parse JSON response into OfferEventOfferEventsResponse object. */
  public static OfferEventOfferEventsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OfferEventOfferEventsResponse object with HTTP response. */
  public static OfferEventOfferEventsResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OfferEventOfferEventsResponse from JSON", e);
    }
  }

  /** Create a new builder for OfferEventOfferEventsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OfferEventOfferEventsResponse. */
  public static class Builder {

    private Response httpResponse;

    private Builder() {}

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public OfferEventOfferEventsResponse build() {
      return new OfferEventOfferEventsResponse(this);
    }
  }
}
