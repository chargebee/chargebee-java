package com.chargebee.v4.models.businessProfile.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BusinessProfileExecute operation. Contains the response data from a
 * single resource get operation.
 */
public final class BusinessProfileExecuteResponse extends BaseResponse {
  private final Object businessProfile;

  private BusinessProfileExecuteResponse(Builder builder) {
    super(builder.httpResponse);

    this.businessProfile = builder.businessProfile;
  }

  /** Parse JSON response into BusinessProfileExecuteResponse object. */
  public static BusinessProfileExecuteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BusinessProfileExecuteResponse object with HTTP response. */
  public static BusinessProfileExecuteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.businessProfile(JsonUtil.getObject(json, "business_profile"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessProfileExecuteResponse from JSON", e);
    }
  }

  /** Create a new builder for BusinessProfileExecuteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BusinessProfileExecuteResponse. */
  public static class Builder {

    private Object businessProfile;

    private Response httpResponse;

    private Builder() {}

    public Builder businessProfile(Object businessProfile) {
      this.businessProfile = businessProfile;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public BusinessProfileExecuteResponse build() {
      return new BusinessProfileExecuteResponse(this);
    }
  }

  /** Get the businessProfile from the response. */
  public Object getBusinessProfile() {
    return businessProfile;
  }
}
