package com.chargebee.v4.core.responses.resourceMigration;

import com.chargebee.v4.core.models.resourceMigration.ResourceMigration;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ResourceMigrationRetrieveLatest operation. Contains the response
 * data from a single resource get operation.
 */
public final class ResourceMigrationRetrieveLatestResponse {

  private final ResourceMigration resourceMigration;

  private ResourceMigrationRetrieveLatestResponse(ResourceMigration resourceMigration) {

    this.resourceMigration = resourceMigration;
  }

  /** Parse JSON response into ResourceMigrationRetrieveLatestResponse object. */
  public static ResourceMigrationRetrieveLatestResponse fromJson(String json) {
    try {

      ResourceMigration resourceMigration =
          ResourceMigration.fromJson(JsonUtil.getObject(json, "resource_migration"));

      return new ResourceMigrationRetrieveLatestResponse(resourceMigration);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ResourceMigrationRetrieveLatestResponse from JSON", e);
    }
  }

  /** Get the resourceMigration from the response. */
  public ResourceMigration getResourceMigration() {
    return resourceMigration;
  }
}
