package com.chargebee.v4.core.responses.variant;

import com.chargebee.v4.core.models.variant.Variant;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for VariantUpdate operation. Contains the response data from the API.
 */
public final class VariantUpdateResponse {

  private final Variant variant;

  private final Response httpResponse;

  private VariantUpdateResponse(Builder builder) {

    this.variant = builder.variant;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into VariantUpdateResponse object. */
  public static VariantUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into VariantUpdateResponse object with HTTP response. */
  public static VariantUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __variantJson = JsonUtil.getObject(json, "variant");
      if (__variantJson != null) {
        builder.variant(Variant.fromJson(__variantJson));
      }

      builder.httpResponse(httpResponse);
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

    public VariantUpdateResponse build() {
      return new VariantUpdateResponse(this);
    }
  }

  /** Get the variant from the response. */
  public Variant getVariant() {
    return variant;
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
