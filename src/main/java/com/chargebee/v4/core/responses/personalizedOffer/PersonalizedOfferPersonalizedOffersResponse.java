package com.chargebee.v4.core.responses.personalizedOffer;

import java.util.List;

import com.chargebee.v4.core.models.brand.Brand;

import com.chargebee.v4.core.models.personalizedOffer.PersonalizedOffer;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.sql.Timestamp;

/**
 * Immutable response object for PersonalizedOfferPersonalizedOffers operation. Contains the
 * response data from the API.
 */
public final class PersonalizedOfferPersonalizedOffersResponse {

  private final List<PersonalizedOffer> personalizedOffers;

  private final Brand brand;

  private final Timestamp expiresAt;

  private final Response httpResponse;

  private PersonalizedOfferPersonalizedOffersResponse(Builder builder) {

    this.personalizedOffers = builder.personalizedOffers;

    this.brand = builder.brand;

    this.expiresAt = builder.expiresAt;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into PersonalizedOfferPersonalizedOffersResponse object. */
  public static PersonalizedOfferPersonalizedOffersResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into PersonalizedOfferPersonalizedOffersResponse object with HTTP response.
   */
  public static PersonalizedOfferPersonalizedOffersResponse fromJson(
      String json, Response httpResponse) {
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
