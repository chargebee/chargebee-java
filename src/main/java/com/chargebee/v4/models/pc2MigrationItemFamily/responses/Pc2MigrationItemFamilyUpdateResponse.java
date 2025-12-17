package com.chargebee.v4.models.pc2MigrationItemFamily.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationItemFamilyUpdate operation. Contains the response data
 * from the API.
 */
public final class Pc2MigrationItemFamilyUpdateResponse extends BaseResponse {

  private Pc2MigrationItemFamilyUpdateResponse(Builder builder) {
    super(builder.httpResponse);
  }

  /** Parse JSON response into Pc2MigrationItemFamilyUpdateResponse object. */
  public static Pc2MigrationItemFamilyUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemFamilyUpdateResponse object with HTTP response. */
  public static Pc2MigrationItemFamilyUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemFamilyUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemFamilyUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemFamilyUpdateResponse. */
  public static class Builder {

    private Response httpResponse;

    private Builder() {}

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public Pc2MigrationItemFamilyUpdateResponse build() {
      return new Pc2MigrationItemFamilyUpdateResponse(this);
    }
  }
}
