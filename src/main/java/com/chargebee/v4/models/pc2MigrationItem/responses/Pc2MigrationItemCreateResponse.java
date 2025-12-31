package com.chargebee.v4.models.pc2MigrationItem.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationItemCreate operation. Contains the response data from
 * the API.
 */
public final class Pc2MigrationItemCreateResponse extends BaseResponse {

  private Pc2MigrationItemCreateResponse(Builder builder) {
    super(builder.httpResponse);
  }

  /** Parse JSON response into Pc2MigrationItemCreateResponse object. */
  public static Pc2MigrationItemCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemCreateResponse object with HTTP response. */
  public static Pc2MigrationItemCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemCreateResponse. */
  public static class Builder {

    private Response httpResponse;

    private Builder() {}

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public Pc2MigrationItemCreateResponse build() {
      return new Pc2MigrationItemCreateResponse(this);
    }
  }

  @Override
  public String toString() {
    return "Pc2MigrationItemCreateResponse{" + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pc2MigrationItemCreateResponse that = (Pc2MigrationItemCreateResponse) o;
    return;
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash();
  }
}
