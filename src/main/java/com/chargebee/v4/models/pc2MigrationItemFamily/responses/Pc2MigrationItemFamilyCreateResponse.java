package com.chargebee.v4.models.pc2MigrationItemFamily.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationItemFamilyCreate operation. Contains the response data
 * from the API.
 */
public final class Pc2MigrationItemFamilyCreateResponse extends BaseResponse {

  private Pc2MigrationItemFamilyCreateResponse(Builder builder) {
    super(builder.httpResponse);
  }

  /** Parse JSON response into Pc2MigrationItemFamilyCreateResponse object. */
  public static Pc2MigrationItemFamilyCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemFamilyCreateResponse object with HTTP response. */
  public static Pc2MigrationItemFamilyCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemFamilyCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemFamilyCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemFamilyCreateResponse. */
  public static class Builder {

    private Response httpResponse;

    private Builder() {}

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public Pc2MigrationItemFamilyCreateResponse build() {
      return new Pc2MigrationItemFamilyCreateResponse(this);
    }
  }

  @Override
  public String toString() {
    return "Pc2MigrationItemFamilyCreateResponse{" + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    return true;
  }

  @Override
  public int hashCode() {

    return 0;
  }
}
