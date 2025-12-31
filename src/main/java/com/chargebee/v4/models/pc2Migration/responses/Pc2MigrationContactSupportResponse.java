package com.chargebee.v4.models.pc2Migration.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationContactSupport operation. Contains the response data
 * from the API.
 */
public final class Pc2MigrationContactSupportResponse extends BaseResponse {

  private Pc2MigrationContactSupportResponse(Builder builder) {
    super(builder.httpResponse);
  }

  /** Parse JSON response into Pc2MigrationContactSupportResponse object. */
  public static Pc2MigrationContactSupportResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationContactSupportResponse object with HTTP response. */
  public static Pc2MigrationContactSupportResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationContactSupportResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationContactSupportResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationContactSupportResponse. */
  public static class Builder {

    private Response httpResponse;

    private Builder() {}

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public Pc2MigrationContactSupportResponse build() {
      return new Pc2MigrationContactSupportResponse(this);
    }
  }

  @Override
  public String toString() {
    return "Pc2MigrationContactSupportResponse{" + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pc2MigrationContactSupportResponse that = (Pc2MigrationContactSupportResponse) o;
    return;
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash();
  }
}
