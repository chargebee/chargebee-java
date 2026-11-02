package com.chargebee.v4.models.collectionsPaymentPromise.responses;

import com.chargebee.v4.models.collectionsPaymentPromise.CollectionsPaymentPromise;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UpdateCollectionsPaymentPromise operation. Contains the response
 * data from the API.
 */
public final class UpdateCollectionsPaymentPromiseResponse extends BaseResponse {
  private final CollectionsPaymentPromise collectionsPaymentPromise;

  private UpdateCollectionsPaymentPromiseResponse(Builder builder) {
    super(builder.httpResponse);

    this.collectionsPaymentPromise = builder.collectionsPaymentPromise;
  }

  /** Parse JSON response into UpdateCollectionsPaymentPromiseResponse object. */
  public static UpdateCollectionsPaymentPromiseResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UpdateCollectionsPaymentPromiseResponse object with HTTP response. */
  public static UpdateCollectionsPaymentPromiseResponse fromJson(
      String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __collectionsPaymentPromiseObj =
          JsonUtil.getJsonObject(jsonObj, "collections_payment_promise");
      if (__collectionsPaymentPromiseObj != null) {
        builder.collectionsPaymentPromise(
            CollectionsPaymentPromise.fromJson(__collectionsPaymentPromiseObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse UpdateCollectionsPaymentPromiseResponse from JSON", e);
    }
  }

  /** Create a new builder for UpdateCollectionsPaymentPromiseResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UpdateCollectionsPaymentPromiseResponse. */
  public static class Builder {

    private CollectionsPaymentPromise collectionsPaymentPromise;

    private Response httpResponse;

    private Builder() {}

    public Builder collectionsPaymentPromise(CollectionsPaymentPromise collectionsPaymentPromise) {
      this.collectionsPaymentPromise = collectionsPaymentPromise;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public UpdateCollectionsPaymentPromiseResponse build() {
      return new UpdateCollectionsPaymentPromiseResponse(this);
    }
  }

  /** Get the collectionsPaymentPromise from the response. */
  public CollectionsPaymentPromise getCollectionsPaymentPromise() {
    return collectionsPaymentPromise;
  }

  @Override
  public String toString() {
    return "UpdateCollectionsPaymentPromiseResponse{"
        + "collectionsPaymentPromise="
        + collectionsPaymentPromise
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UpdateCollectionsPaymentPromiseResponse that = (UpdateCollectionsPaymentPromiseResponse) o;
    return java.util.Objects.equals(collectionsPaymentPromise, that.collectionsPaymentPromise);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(collectionsPaymentPromise);
  }
}
