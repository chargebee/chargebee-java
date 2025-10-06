package com.chargebee.v4.core.responses.offerEvent;

/**
 * Immutable response object for OfferEventOfferEvents operation. Contains the response data from
 * the API.
 */
public final class OfferEventOfferEventsResponse {

  private OfferEventOfferEventsResponse(Builder builder) {}

  /** Parse JSON response into OfferEventOfferEventsResponse object. */
  public static OfferEventOfferEventsResponse fromJson(String json) {
    try {
      Builder builder = builder();

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

    private Builder() {}

    public OfferEventOfferEventsResponse build() {
      return new OfferEventOfferEventsResponse(this);
    }
  }
}
