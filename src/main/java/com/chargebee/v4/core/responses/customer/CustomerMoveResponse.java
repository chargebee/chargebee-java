package com.chargebee.v4.core.responses.customer;

import com.chargebee.v4.core.models.resourceMigration.ResourceMigration;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CustomerMove operation. Contains the response data from the API.
 */
public final class CustomerMoveResponse {

  private final ResourceMigration resourceMigration;

  private final Response httpResponse;

  private CustomerMoveResponse(Builder builder) {

    this.resourceMigration = builder.resourceMigration;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into CustomerMoveResponse object. */
  public static CustomerMoveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CustomerMoveResponse object with HTTP response. */
  public static CustomerMoveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __resourceMigrationJson = JsonUtil.getObject(json, "resource_migration");
      if (__resourceMigrationJson != null) {
        builder.resourceMigration(ResourceMigration.fromJson(__resourceMigrationJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CustomerMoveResponse from JSON", e);
    }
  }

  /** Create a new builder for CustomerMoveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CustomerMoveResponse. */
  public static class Builder {

    private ResourceMigration resourceMigration;

    private Response httpResponse;

    private Builder() {}

    public Builder resourceMigration(ResourceMigration resourceMigration) {
      this.resourceMigration = resourceMigration;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CustomerMoveResponse build() {
      return new CustomerMoveResponse(this);
    }
  }

  /** Get the resourceMigration from the response. */
  public ResourceMigration getResourceMigration() {
    return resourceMigration;
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
