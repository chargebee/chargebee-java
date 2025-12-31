package com.chargebee.v4.models.pc2Migration.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class Pc2MigrationRetrieveResponse extends BaseResponse {
  private final Object pc2Migration;

  private Pc2MigrationRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.pc2Migration = builder.pc2Migration;
  }

  /** Parse JSON response into Pc2MigrationRetrieveResponse object. */
  public static Pc2MigrationRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationRetrieveResponse object with HTTP response. */
  public static Pc2MigrationRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.pc2Migration(JsonUtil.getObject(json, "pc2_migration"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationRetrieveResponse. */
  public static class Builder {

    private Object pc2Migration;

    private Response httpResponse;

    private Builder() {}

    public Builder pc2Migration(Object pc2Migration) {
      this.pc2Migration = pc2Migration;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public Pc2MigrationRetrieveResponse build() {
      return new Pc2MigrationRetrieveResponse(this);
    }
  }

  /** Get the pc2Migration from the response. */
  public Object getPc2Migration() {
    return pc2Migration;
  }

  @Override
  public String toString() {
    return "Pc2MigrationRetrieveResponse{" + "pc2Migration=" + pc2Migration + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pc2MigrationRetrieveResponse that = (Pc2MigrationRetrieveResponse) o;
    return java.util.Objects.equals(pc2Migration, that.pc2Migration);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(pc2Migration);
  }
}
