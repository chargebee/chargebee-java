package com.chargebee.v4.core.responses.pc2MigrationItemPrice;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationItemPriceDelete operation. Contains the response data
 * from the API.
 */
public final class Pc2MigrationItemPriceDeleteResponse {

  private final Boolean isDeleted;

  private final Response httpResponse;

  private Pc2MigrationItemPriceDeleteResponse(Builder builder) {

    this.isDeleted = builder.isDeleted;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into Pc2MigrationItemPriceDeleteResponse object. */
  public static Pc2MigrationItemPriceDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemPriceDeleteResponse object with HTTP response. */
  public static Pc2MigrationItemPriceDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.isDeleted(JsonUtil.getBoolean(json, "is_deleted"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemPriceDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemPriceDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemPriceDeleteResponse. */
  public static class Builder {

    private Boolean isDeleted;

    private Response httpResponse;

    private Builder() {}

    public Builder isDeleted(Boolean isDeleted) {
      this.isDeleted = isDeleted;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public Pc2MigrationItemPriceDeleteResponse build() {
      return new Pc2MigrationItemPriceDeleteResponse(this);
    }
  }

  /** Get the isDeleted from the response. */
  public Boolean getIsDeleted() {
    return isDeleted;
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
