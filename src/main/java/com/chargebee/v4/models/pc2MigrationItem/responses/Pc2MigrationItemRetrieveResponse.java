package com.chargebee.v4.models.pc2MigrationItem.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationItemRetrieve operation. Contains the response data from
 * a single resource get operation.
 */
public final class Pc2MigrationItemRetrieveResponse extends BaseResponse {
  private final Object pc2MigrationItem;

  private Pc2MigrationItemRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.pc2MigrationItem = builder.pc2MigrationItem;
  }

  /** Parse JSON response into Pc2MigrationItemRetrieveResponse object. */
  public static Pc2MigrationItemRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemRetrieveResponse object with HTTP response. */
  public static Pc2MigrationItemRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.pc2MigrationItem(JsonUtil.getObject(json, "pc2_migration_item"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemRetrieveResponse. */
  public static class Builder {

    private Object pc2MigrationItem;

    private Response httpResponse;

    private Builder() {}

    public Builder pc2MigrationItem(Object pc2MigrationItem) {
      this.pc2MigrationItem = pc2MigrationItem;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public Pc2MigrationItemRetrieveResponse build() {
      return new Pc2MigrationItemRetrieveResponse(this);
    }
  }

  /** Get the pc2MigrationItem from the response. */
  public Object getPc2MigrationItem() {
    return pc2MigrationItem;
  }
}
