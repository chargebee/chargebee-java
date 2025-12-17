package com.chargebee.v4.models.pc2MigrationItem.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeletePc2MigrationItem operation. Contains the response data from
 * the API.
 */
public final class DeletePc2MigrationItemResponse extends BaseResponse {
  private final Boolean isDeleted;

  private DeletePc2MigrationItemResponse(Builder builder) {
    super(builder.httpResponse);

    this.isDeleted = builder.isDeleted;
  }

  /** Parse JSON response into DeletePc2MigrationItemResponse object. */
  public static DeletePc2MigrationItemResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeletePc2MigrationItemResponse object with HTTP response. */
  public static DeletePc2MigrationItemResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.isDeleted(JsonUtil.getBoolean(json, "is_deleted"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeletePc2MigrationItemResponse from JSON", e);
    }
  }

  /** Create a new builder for DeletePc2MigrationItemResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeletePc2MigrationItemResponse. */
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

    public DeletePc2MigrationItemResponse build() {
      return new DeletePc2MigrationItemResponse(this);
    }
  }

  /** Get the isDeleted from the response. */
  public Boolean getIsDeleted() {
    return isDeleted;
  }
}
