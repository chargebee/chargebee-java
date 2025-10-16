package com.chargebee.v4.core.responses.pc2Migration;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationCreate operation. Contains the response data from the
 * API.
 */
public final class Pc2MigrationCreateResponse extends BaseResponse {

  private Pc2MigrationCreateResponse(Builder builder) {
    super(builder.httpResponse);
  }

  /** Parse JSON response into Pc2MigrationCreateResponse object. */
  public static Pc2MigrationCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationCreateResponse object with HTTP response. */
  public static Pc2MigrationCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationCreateResponse. */
  public static class Builder {

    private Response httpResponse;

    private Builder() {}

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public Pc2MigrationCreateResponse build() {
      return new Pc2MigrationCreateResponse(this);
    }
  }
}
