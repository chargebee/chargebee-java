package com.chargebee.v4.models.pc2MigrationItemPrice.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationItemPriceUpdate operation. Contains the response data
 * from the API.
 */
public final class Pc2MigrationItemPriceUpdateResponse extends BaseResponse {

  private Pc2MigrationItemPriceUpdateResponse(Builder builder) {
    super(builder.httpResponse);
  }

  /** Parse JSON response into Pc2MigrationItemPriceUpdateResponse object. */
  public static Pc2MigrationItemPriceUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemPriceUpdateResponse object with HTTP response. */
  public static Pc2MigrationItemPriceUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemPriceUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemPriceUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemPriceUpdateResponse. */
  public static class Builder {

    private Response httpResponse;

    private Builder() {}

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public Pc2MigrationItemPriceUpdateResponse build() {
      return new Pc2MigrationItemPriceUpdateResponse(this);
    }
  }

  @Override
  public String toString() {
    return "Pc2MigrationItemPriceUpdateResponse{" + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pc2MigrationItemPriceUpdateResponse that = (Pc2MigrationItemPriceUpdateResponse) o;
    return;
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash();
  }
}
