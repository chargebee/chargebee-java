package com.chargebee.v4.models.variant.responses;

import com.chargebee.v4.models.variant.Variant;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for VariantRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class VariantRetrieveResponse extends BaseResponse {
  private final Variant variant;

  private VariantRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.variant = builder.variant;
  }

  /** Parse JSON response into VariantRetrieveResponse object. */
  public static VariantRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into VariantRetrieveResponse object with HTTP response. */
  public static VariantRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __variantJson = JsonUtil.getObject(json, "variant");
      if (__variantJson != null) {
        builder.variant(Variant.fromJson(__variantJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VariantRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for VariantRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for VariantRetrieveResponse. */
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

    public VariantRetrieveResponse build() {
      return new VariantRetrieveResponse(this);
    }
  }

  /** Get the variant from the response. */
  public Variant getVariant() {
    return variant;
  }
}
