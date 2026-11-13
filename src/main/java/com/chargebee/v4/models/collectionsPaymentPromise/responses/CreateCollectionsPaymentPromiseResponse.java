package com.chargebee.v4.models.collectionsPaymentPromise.responses;

import java.util.List;

import com.chargebee.v4.models.collectionsPaymentPromise.CollectionsPaymentPromise;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreateCollectionsPaymentPromise operation. Contains the response
 * data from the API.
 */
public final class CreateCollectionsPaymentPromiseResponse extends BaseResponse {
  private final List<CollectionsPaymentPromise> collectionsPaymentPromises;

  private CreateCollectionsPaymentPromiseResponse(Builder builder) {
    super(builder.httpResponse);

    this.collectionsPaymentPromises = builder.collectionsPaymentPromises;
  }

  /** Parse JSON response into CreateCollectionsPaymentPromiseResponse object. */
  public static CreateCollectionsPaymentPromiseResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CreateCollectionsPaymentPromiseResponse object with HTTP response. */
  public static CreateCollectionsPaymentPromiseResponse fromJson(
      String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      builder.collectionsPaymentPromises(
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "collections_payment_promises"),
              CollectionsPaymentPromise::fromJson));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CreateCollectionsPaymentPromiseResponse from JSON", e);
    }
  }

  /** Create a new builder for CreateCollectionsPaymentPromiseResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreateCollectionsPaymentPromiseResponse. */
  public static class Builder {

    private List<CollectionsPaymentPromise> collectionsPaymentPromises;

    private Response httpResponse;

    private Builder() {}

    public Builder collectionsPaymentPromises(
        List<CollectionsPaymentPromise> collectionsPaymentPromises) {
      this.collectionsPaymentPromises = collectionsPaymentPromises;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CreateCollectionsPaymentPromiseResponse build() {
      return new CreateCollectionsPaymentPromiseResponse(this);
    }
  }

  /** Get the collectionsPaymentPromises from the response. */
  public List<CollectionsPaymentPromise> getCollectionsPaymentPromises() {
    return collectionsPaymentPromises;
  }

  @Override
  public String toString() {
    return "CreateCollectionsPaymentPromiseResponse{"
        + "collectionsPaymentPromises="
        + collectionsPaymentPromises
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CreateCollectionsPaymentPromiseResponse that = (CreateCollectionsPaymentPromiseResponse) o;
    return java.util.Objects.equals(collectionsPaymentPromises, that.collectionsPaymentPromises);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(collectionsPaymentPromises);
  }
}
