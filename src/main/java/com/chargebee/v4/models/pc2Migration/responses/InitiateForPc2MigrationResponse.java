package com.chargebee.v4.models.pc2Migration.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InitiateForPc2Migration operation. Contains the response data from
 * the API.
 */
public final class InitiateForPc2MigrationResponse extends BaseResponse {

  private InitiateForPc2MigrationResponse(Builder builder) {
    super(builder.httpResponse);
  }

  /** Parse JSON response into InitiateForPc2MigrationResponse object. */
  public static InitiateForPc2MigrationResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InitiateForPc2MigrationResponse object with HTTP response. */
  public static InitiateForPc2MigrationResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InitiateForPc2MigrationResponse from JSON", e);
    }
  }

  /** Create a new builder for InitiateForPc2MigrationResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InitiateForPc2MigrationResponse. */
  public static class Builder {

    private Response httpResponse;

    private Builder() {}

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public InitiateForPc2MigrationResponse build() {
      return new InitiateForPc2MigrationResponse(this);
    }
  }
}
