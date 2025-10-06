package com.chargebee.v4.core.responses.personalizedOffer;

import java.util.List;

import com.chargebee.v4.core.models.brand.Brand;

import com.chargebee.v4.core.models.personalizedOffer.PersonalizedOffer;

import com.chargebee.v4.internal.JsonUtil;
import java.sql.Timestamp;

/**
 * Immutable response object for PersonalizedOfferPersonalizedOffers operation. Contains the
 * response data from the API.
 */
public final class PersonalizedOfferPersonalizedOffersResponse {

  private final List<PersonalizedOffer> personalizedOffers;

  private final Brand brand;

  private final Timestamp expiresAt;

  private PersonalizedOfferPersonalizedOffersResponse(Builder builder) {

    this.personalizedOffers = builder.personalizedOffers;

    this.brand = builder.brand;

    this.expiresAt = builder.expiresAt;
  }

  /** Parse JSON response into PersonalizedOfferPersonalizedOffersResponse object. */
  public static PersonalizedOfferPersonalizedOffersResponse fromJson(String json) {
    try {
      Builder builder = builder();

      builder.personalizedOffers(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "personalized_offers")).stream()
              .map(PersonalizedOffer::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      String __brandJson = JsonUtil.getObject(json, "brand");
      if (__brandJson != null) {
        builder.brand(Brand.fromJson(__brandJson));
      }

      builder.expiresAt(JsonUtil.getTimestamp(json, "expires_at"));

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PersonalizedOfferPersonalizedOffersResponse from JSON", e);
    }
  }

  /** Create a new builder for PersonalizedOfferPersonalizedOffersResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PersonalizedOfferPersonalizedOffersResponse. */
  public static class Builder {

    private List<PersonalizedOffer> personalizedOffers;

    private Brand brand;

    private Timestamp expiresAt;

    private Builder() {}

    public Builder personalizedOffers(List<PersonalizedOffer> personalizedOffers) {
      this.personalizedOffers = personalizedOffers;
      return this;
    }

    public Builder brand(Brand brand) {
      this.brand = brand;
      return this;
    }

    public Builder expiresAt(Timestamp expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public PersonalizedOfferPersonalizedOffersResponse build() {
      return new PersonalizedOfferPersonalizedOffersResponse(this);
    }
  }

  /** Get the personalizedOffers from the response. */
  public List<PersonalizedOffer> getPersonalizedOffers() {
    return personalizedOffers;
  }

  /** Get the brand from the response. */
  public Brand getBrand() {
    return brand;
  }

  /** Get the expiresAt from the response. */
  public Timestamp getExpiresAt() {
    return expiresAt;
  }
}
