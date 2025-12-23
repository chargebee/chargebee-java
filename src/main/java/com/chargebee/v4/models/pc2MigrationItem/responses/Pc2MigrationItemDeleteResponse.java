package com.chargebee.v4.models.pc2MigrationItem.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationItemDelete operation. Contains the response data from
 * the API.
 */
public final class Pc2MigrationItemDeleteResponse extends BaseResponse {
  private final Boolean isDeleted;

  private Pc2MigrationItemDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.isDeleted = builder.isDeleted;
  }

  /** Parse JSON response into Pc2MigrationItemDeleteResponse object. */
  public static Pc2MigrationItemDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemDeleteResponse object with HTTP response. */
  public static Pc2MigrationItemDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.isDeleted(JsonUtil.getBoolean(json, "is_deleted"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemDeleteResponse. */
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

    public Pc2MigrationItemDeleteResponse build() {
      return new Pc2MigrationItemDeleteResponse(this);
    }
  }

  /** Get the isDeleted from the response. */
  public Boolean getIsDeleted() {
    return isDeleted;
  }
}
