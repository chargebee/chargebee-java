package com.chargebee.v4.models.pc2Migration.responses;

import com.chargebee.v4.models.BaseResponse;
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

  @Override
  public String toString() {
    return "Pc2MigrationCreateResponse{" + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pc2MigrationCreateResponse that = (Pc2MigrationCreateResponse) o;
    return;
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash();
  }
}
