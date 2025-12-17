package com.chargebee.v4.models.pc2MigrationItem.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationItemUpdate operation. Contains the response data from
 * the API.
 */
public final class Pc2MigrationItemUpdateResponse extends BaseResponse {

  private Pc2MigrationItemUpdateResponse(Builder builder) {
    super(builder.httpResponse);
  }

  /** Parse JSON response into Pc2MigrationItemUpdateResponse object. */
  public static Pc2MigrationItemUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemUpdateResponse object with HTTP response. */
  public static Pc2MigrationItemUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemUpdateResponse. */
  public static class Builder {

    private Response httpResponse;

    private Builder() {}

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public Pc2MigrationItemUpdateResponse build() {
      return new Pc2MigrationItemUpdateResponse(this);
    }
  }
}
