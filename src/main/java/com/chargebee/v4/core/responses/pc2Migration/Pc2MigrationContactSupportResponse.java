package com.chargebee.v4.core.responses.pc2Migration;

import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationContactSupport operation. Contains the response data
 * from the API.
 */
public final class Pc2MigrationContactSupportResponse {

  private final Response httpResponse;

  private Pc2MigrationContactSupportResponse(Builder builder) {

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into Pc2MigrationContactSupportResponse object. */
  public static Pc2MigrationContactSupportResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationContactSupportResponse object with HTTP response. */
  public static Pc2MigrationContactSupportResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationContactSupportResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationContactSupportResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationContactSupportResponse. */
  public static class Builder {

    private Response httpResponse;

    private Builder() {}

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public Pc2MigrationContactSupportResponse build() {
      return new Pc2MigrationContactSupportResponse(this);
    }
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
