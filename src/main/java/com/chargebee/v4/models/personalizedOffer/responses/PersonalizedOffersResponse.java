package com.chargebee.v4.models.personalizedOffer.responses;

import java.util.List;

import com.chargebee.v4.models.brand.Brand;

import com.chargebee.v4.models.personalizedOffer.PersonalizedOffer;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.sql.Timestamp;

/**
 * Immutable response object for PersonalizedOffers operation. Contains the response data from the
 * API.
 */
public final class PersonalizedOffersResponse extends BaseResponse {
  private final List<PersonalizedOffer> personalizedOffers;

  private final Brand brand;

  private final Timestamp expiresAt;

  private PersonalizedOffersResponse(Builder builder) {
    super(builder.httpResponse);

    this.personalizedOffers = builder.personalizedOffers;

    this.brand = builder.brand;

    this.expiresAt = builder.expiresAt;
  }

  /** Parse JSON response into PersonalizedOffersResponse object. */
  public static PersonalizedOffersResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PersonalizedOffersResponse object with HTTP response. */
  public static PersonalizedOffersResponse fromJson(String json, Response httpResponse) {
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

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PersonalizedOffersResponse from JSON", e);
    }
  }

  /** Create a new builder for PersonalizedOffersResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PersonalizedOffersResponse. */
  public static class Builder {

    private List<PersonalizedOffer> personalizedOffers;

    private Brand brand;

    private Timestamp expiresAt;

    private Response httpResponse;

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

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public PersonalizedOffersResponse build() {
      return new PersonalizedOffersResponse(this);
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

  @Override
  public String toString() {
    return "PersonalizedOffersResponse{"
        + "personalizedOffers="
        + personalizedOffers
        + ", brand="
        + brand
        + ", expiresAt="
        + expiresAt
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PersonalizedOffersResponse that = (PersonalizedOffersResponse) o;
    return java.util.Objects.equals(personalizedOffers, that.personalizedOffers)
        && java.util.Objects.equals(brand, that.brand)
        && java.util.Objects.equals(expiresAt, that.expiresAt);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(personalizedOffers, brand, expiresAt);
  }
}
