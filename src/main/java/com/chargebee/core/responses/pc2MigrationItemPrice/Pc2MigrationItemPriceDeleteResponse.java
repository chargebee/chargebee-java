package com.chargebee.core.responses.pc2MigrationItemPrice;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for Pc2MigrationItemPriceDelete operation. Contains the response data
 * from the API.
 */
public final class Pc2MigrationItemPriceDeleteResponse {

  private final Boolean isDeleted;

  private Pc2MigrationItemPriceDeleteResponse(Builder builder) {

    this.isDeleted = builder.isDeleted;
  }

  /** Parse JSON response into Pc2MigrationItemPriceDeleteResponse object. */
  public static Pc2MigrationItemPriceDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      builder.isDeleted(JsonUtil.getBoolean(json, "is_deleted"));

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

    private Builder() {}

    public Builder isDeleted(Boolean isDeleted) {
      this.isDeleted = isDeleted;
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
}
