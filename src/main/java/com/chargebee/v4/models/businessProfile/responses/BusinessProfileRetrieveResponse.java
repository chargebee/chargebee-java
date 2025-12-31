package com.chargebee.v4.models.businessProfile.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BusinessProfileRetrieve operation. Contains the response data from
 * a single resource get operation.
 */
public final class BusinessProfileRetrieveResponse extends BaseResponse {
  private final Object businessProfile;

  private BusinessProfileRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.businessProfile = builder.businessProfile;
  }

  /** Parse JSON response into BusinessProfileRetrieveResponse object. */
  public static BusinessProfileRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BusinessProfileRetrieveResponse object with HTTP response. */
  public static BusinessProfileRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.businessProfile(JsonUtil.getObject(json, "business_profile"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessProfileRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for BusinessProfileRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BusinessProfileRetrieveResponse. */
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

    public BusinessProfileRetrieveResponse build() {
      return new BusinessProfileRetrieveResponse(this);
    }
  }

  /** Get the businessProfile from the response. */
  public Object getBusinessProfile() {
    return businessProfile;
  }

  @Override
  public String toString() {
    return "BusinessProfileRetrieveResponse{" + "businessProfile=" + businessProfile + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BusinessProfileRetrieveResponse that = (BusinessProfileRetrieveResponse) o;
    return java.util.Objects.equals(businessProfile, that.businessProfile);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(businessProfile);
  }
}
