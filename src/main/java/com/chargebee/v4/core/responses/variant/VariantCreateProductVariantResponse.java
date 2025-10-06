package com.chargebee.v4.core.responses.variant;

import com.chargebee.v4.core.models.variant.Variant;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for VariantCreateProductVariant operation. Contains the response data
 * from the API.
 */
public final class VariantCreateProductVariantResponse {

  private final Variant variant;

  private VariantCreateProductVariantResponse(Builder builder) {

    this.variant = builder.variant;
  }

  /** Parse JSON response into VariantCreateProductVariantResponse object. */
  public static VariantCreateProductVariantResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __variantJson = JsonUtil.getObject(json, "variant");
      if (__variantJson != null) {
        builder.variant(Variant.fromJson(__variantJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse VariantCreateProductVariantResponse from JSON", e);
    }
  }

  /** Create a new builder for VariantCreateProductVariantResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for VariantCreateProductVariantResponse. */
  public static class Builder {

    private Variant variant;

    private Builder() {}

    public Builder variant(Variant variant) {
      this.variant = variant;
      return this;
    }

    public VariantCreateProductVariantResponse build() {
      return new VariantCreateProductVariantResponse(this);
    }
  }

  /** Get the variant from the response. */
  public Variant getVariant() {
    return variant;
  }
}
