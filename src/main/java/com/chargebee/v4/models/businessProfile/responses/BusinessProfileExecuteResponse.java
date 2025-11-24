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

  private BusinessProfileExecuteResponse(Object businessProfile, Response httpResponse) {
    super(httpResponse);

    this.businessProfile = businessProfile;
  }

  /** Parse JSON response into BusinessProfileExecuteResponse object. */
  public static BusinessProfileExecuteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BusinessProfileExecuteResponse object with HTTP response. */
  public static BusinessProfileExecuteResponse fromJson(String json, Response httpResponse) {
    try {

      Object businessProfile = JsonUtil.getObject(json, "business_profile");

      return new BusinessProfileExecuteResponse(businessProfile, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessProfileExecuteResponse from JSON", e);
    }
  }

  /** Get the businessProfile from the response. */
  public Object getBusinessProfile() {
    return businessProfile;
  }
}
