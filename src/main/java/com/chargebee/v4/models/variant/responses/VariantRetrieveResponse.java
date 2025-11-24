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

  private VariantRetrieveResponse(Variant variant, Response httpResponse) {
    super(httpResponse);

    this.variant = variant;
  }

  /** Parse JSON response into VariantRetrieveResponse object. */
  public static VariantRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into VariantRetrieveResponse object with HTTP response. */
  public static VariantRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Variant variant = Variant.fromJson(JsonUtil.getObject(json, "variant"));

      return new VariantRetrieveResponse(variant, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VariantRetrieveResponse from JSON", e);
    }
  }

  /** Get the variant from the response. */
  public Variant getVariant() {
    return variant;
  }
}
