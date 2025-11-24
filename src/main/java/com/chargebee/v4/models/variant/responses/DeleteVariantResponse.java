package com.chargebee.v4.models.variant.responses;

import com.chargebee.v4.models.variant.Variant;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeleteVariant operation. Contains the response data from the API.
 */
public final class DeleteVariantResponse extends BaseResponse {
  private final Variant variant;

  private DeleteVariantResponse(Builder builder) {
    super(builder.httpResponse);

    this.variant = builder.variant;
  }

  /** Parse JSON response into DeleteVariantResponse object. */
  public static DeleteVariantResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeleteVariantResponse object with HTTP response. */
  public static DeleteVariantResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __variantJson = JsonUtil.getObject(json, "variant");
      if (__variantJson != null) {
        builder.variant(Variant.fromJson(__variantJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeleteVariantResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteVariantResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteVariantResponse. */
  public static class Builder {

    private Variant variant;

    private Response httpResponse;

    private Builder() {}

    public Builder variant(Variant variant) {
      this.variant = variant;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public DeleteVariantResponse build() {
      return new DeleteVariantResponse(this);
    }
  }

  /** Get the variant from the response. */
  public Variant getVariant() {
    return variant;
  }
}
