package com.chargebee.core.responses.variant;

import com.chargebee.core.models.variant.Variant;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for VariantUpdate operation. Contains the response data from the API.
 */
public final class VariantUpdateResponse {

  private final Variant variant;

  private VariantUpdateResponse(Builder builder) {

    this.variant = builder.variant;
  }

  /** Parse JSON response into VariantUpdateResponse object. */
  public static VariantUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __variantJson = JsonUtil.getObject(json, "variant");
      if (__variantJson != null) {
        builder.variant(Variant.fromJson(__variantJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VariantUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for VariantUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for VariantUpdateResponse. */
  public static class Builder {

    private Variant variant;

    private Builder() {}

    public Builder variant(Variant variant) {
      this.variant = variant;
      return this;
    }

    public VariantUpdateResponse build() {
      return new VariantUpdateResponse(this);
    }
  }

  /** Get the variant from the response. */
  public Variant getVariant() {
    return variant;
  }
}
