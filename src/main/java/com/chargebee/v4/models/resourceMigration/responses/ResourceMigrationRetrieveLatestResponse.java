package com.chargebee.v4.models.resourceMigration.responses;

import com.chargebee.v4.models.resourceMigration.ResourceMigration;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ResourceMigrationRetrieveLatest operation. Contains the response
 * data from a single resource get operation.
 */
public final class ResourceMigrationRetrieveLatestResponse extends BaseResponse {
  private final ResourceMigration resourceMigration;

  private ResourceMigrationRetrieveLatestResponse(Builder builder) {
    super(builder.httpResponse);

    this.resourceMigration = builder.resourceMigration;
  }

  /** Parse JSON response into ResourceMigrationRetrieveLatestResponse object. */
  public static ResourceMigrationRetrieveLatestResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ResourceMigrationRetrieveLatestResponse object with HTTP response. */
  public static ResourceMigrationRetrieveLatestResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __resourceMigrationJson = JsonUtil.getObject(json, "resource_migration");
      if (__resourceMigrationJson != null) {
        builder.resourceMigration(ResourceMigration.fromJson(__resourceMigrationJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ResourceMigrationRetrieveLatestResponse from JSON", e);
    }
  }

  /** Create a new builder for ResourceMigrationRetrieveLatestResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ResourceMigrationRetrieveLatestResponse. */
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

    public ResourceMigrationRetrieveLatestResponse build() {
      return new ResourceMigrationRetrieveLatestResponse(this);
    }
  }

  /** Get the resourceMigration from the response. */
  public ResourceMigration getResourceMigration() {
    return resourceMigration;
  }

  @Override
  public String toString() {
    return "ResourceMigrationRetrieveLatestResponse{"
        + "resourceMigration="
        + resourceMigration
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ResourceMigrationRetrieveLatestResponse that = (ResourceMigrationRetrieveLatestResponse) o;
    return java.util.Objects.equals(resourceMigration, that.resourceMigration);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(resourceMigration);
  }
}
