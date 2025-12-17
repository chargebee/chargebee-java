package com.chargebee.v4.models.pc2MigrationItemFamily.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationItemFamilyRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class Pc2MigrationItemFamilyRetrieveResponse extends BaseResponse {
  private final Object pc2MigrationItemFamily;

  private Pc2MigrationItemFamilyRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.pc2MigrationItemFamily = builder.pc2MigrationItemFamily;
  }

  /** Parse JSON response into Pc2MigrationItemFamilyRetrieveResponse object. */
  public static Pc2MigrationItemFamilyRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemFamilyRetrieveResponse object with HTTP response. */
  public static Pc2MigrationItemFamilyRetrieveResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.pc2MigrationItemFamily(JsonUtil.getObject(json, "pc2_migration_item_family"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemFamilyRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemFamilyRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemFamilyRetrieveResponse. */
  public static class Builder {

    private Object pc2MigrationItemFamily;

    private Response httpResponse;

    private Builder() {}

    public Builder pc2MigrationItemFamily(Object pc2MigrationItemFamily) {
      this.pc2MigrationItemFamily = pc2MigrationItemFamily;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public Pc2MigrationItemFamilyRetrieveResponse build() {
      return new Pc2MigrationItemFamilyRetrieveResponse(this);
    }
  }

  /** Get the pc2MigrationItemFamily from the response. */
  public Object getPc2MigrationItemFamily() {
    return pc2MigrationItemFamily;
  }
}
