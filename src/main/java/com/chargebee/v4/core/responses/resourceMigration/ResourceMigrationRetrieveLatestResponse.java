package com.chargebee.v4.core.responses.resourceMigration;

import com.chargebee.v4.core.models.resourceMigration.ResourceMigration;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ResourceMigrationRetrieveLatest operation. Contains the response
 * data from a single resource get operation.
 */
public final class ResourceMigrationRetrieveLatestResponse {

  private final ResourceMigration resourceMigration;

  private final Response httpResponse;

  private ResourceMigrationRetrieveLatestResponse(
      ResourceMigration resourceMigration, Response httpResponse) {

    this.resourceMigration = resourceMigration;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into ResourceMigrationRetrieveLatestResponse object. */
  public static ResourceMigrationRetrieveLatestResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ResourceMigrationRetrieveLatestResponse object with HTTP response. */
  public static ResourceMigrationRetrieveLatestResponse fromJson(
      String json, Response httpResponse) {
    try {

      ResourceMigration resourceMigration =
          ResourceMigration.fromJson(JsonUtil.getObject(json, "resource_migration"));

      return new ResourceMigrationRetrieveLatestResponse(resourceMigration, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ResourceMigrationRetrieveLatestResponse from JSON", e);
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
