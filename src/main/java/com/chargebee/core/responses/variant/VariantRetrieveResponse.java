package com.chargebee.core.responses.variant;

import com.chargebee.core.models.variant.Variant;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for VariantRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class VariantRetrieveResponse {

  private final Variant variant;

  private VariantRetrieveResponse(Variant variant) {

    this.variant = variant;
  }

  /** Parse JSON response into VariantRetrieveResponse object. */
  public static VariantRetrieveResponse fromJson(String json) {
    try {

      Variant variant = Variant.fromJson(JsonUtil.getObject(json, "variant"));

      return new VariantRetrieveResponse(variant);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VariantRetrieveResponse from JSON", e);
    }
  }

  /** Get the variant from the response. */
  public Variant getVariant() {
    return variant;
  }
}
