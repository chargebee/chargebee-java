package com.chargebee.v4.models.offerEvent.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.transport.Response;

/** Immutable response object for OfferEvents operation. Contains the response data from the API. */
public final class OfferEventsResponse extends BaseResponse {

  private OfferEventsResponse(Builder builder) {
    super(builder.httpResponse);
  }

  /** Parse JSON response into OfferEventsResponse object. */
  public static OfferEventsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OfferEventsResponse object with HTTP response. */
  public static OfferEventsResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OfferEventsResponse from JSON", e);
    }
  }

  /** Create a new builder for OfferEventsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OfferEventsResponse. */
  public static class Builder {

    private Response httpResponse;

    private Builder() {}

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public OfferEventsResponse build() {
      return new OfferEventsResponse(this);
    }
  }

  @Override
  public String toString() {
    return "OfferEventsResponse{" + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    return true;
  }

  @Override
  public int hashCode() {

    return 0;
  }
}
