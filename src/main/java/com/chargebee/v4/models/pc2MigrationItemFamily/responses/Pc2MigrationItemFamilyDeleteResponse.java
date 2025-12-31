package com.chargebee.v4.models.pc2MigrationItemFamily.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationItemFamilyDelete operation. Contains the response data
 * from the API.
 */
public final class Pc2MigrationItemFamilyDeleteResponse extends BaseResponse {
  private final Boolean isDeleted;

  private Pc2MigrationItemFamilyDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.isDeleted = builder.isDeleted;
  }

  /** Parse JSON response into Pc2MigrationItemFamilyDeleteResponse object. */
  public static Pc2MigrationItemFamilyDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemFamilyDeleteResponse object with HTTP response. */
  public static Pc2MigrationItemFamilyDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.isDeleted(JsonUtil.getBoolean(json, "is_deleted"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemFamilyDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemFamilyDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemFamilyDeleteResponse. */
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

    public Pc2MigrationItemFamilyDeleteResponse build() {
      return new Pc2MigrationItemFamilyDeleteResponse(this);
    }
  }

  /** Get the isDeleted from the response. */
  public Boolean getIsDeleted() {
    return isDeleted;
  }

  @Override
  public String toString() {
    return "Pc2MigrationItemFamilyDeleteResponse{" + "isDeleted=" + isDeleted + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Pc2MigrationItemFamilyDeleteResponse that = (Pc2MigrationItemFamilyDeleteResponse) o;
    return java.util.Objects.equals(isDeleted, that.isDeleted);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(isDeleted);
  }
}
