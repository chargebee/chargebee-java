package com.chargebee.v4.core.responses.customer;

import com.chargebee.v4.core.models.resourceMigration.ResourceMigration;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CustomerMove operation. Contains the response data from the API.
 */
public final class CustomerMoveResponse {

  private final ResourceMigration resourceMigration;

  private CustomerMoveResponse(Builder builder) {

    this.resourceMigration = builder.resourceMigration;
  }

  /** Parse JSON response into CustomerMoveResponse object. */
  public static CustomerMoveResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __resourceMigrationJson = JsonUtil.getObject(json, "resource_migration");
      if (__resourceMigrationJson != null) {
        builder.resourceMigration(ResourceMigration.fromJson(__resourceMigrationJson));
      }

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

    private Builder() {}

    public Builder resourceMigration(ResourceMigration resourceMigration) {
      this.resourceMigration = resourceMigration;
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
}
