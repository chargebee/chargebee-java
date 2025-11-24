package com.chargebee.v4.models.pc2Migration.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ContactSupportForPc2Migration operation. Contains the response data
 * from the API.
 */
public final class ContactSupportForPc2MigrationResponse extends BaseResponse {

  private ContactSupportForPc2MigrationResponse(Builder builder) {
    super(builder.httpResponse);
  }

  /** Parse JSON response into ContactSupportForPc2MigrationResponse object. */
  public static ContactSupportForPc2MigrationResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ContactSupportForPc2MigrationResponse object with HTTP response. */
  public static ContactSupportForPc2MigrationResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ContactSupportForPc2MigrationResponse from JSON", e);
    }
  }

  /** Create a new builder for ContactSupportForPc2MigrationResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ContactSupportForPc2MigrationResponse. */
  public static class Builder {

    private Response httpResponse;

    private Builder() {}

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public ContactSupportForPc2MigrationResponse build() {
      return new ContactSupportForPc2MigrationResponse(this);
    }
  }
}
