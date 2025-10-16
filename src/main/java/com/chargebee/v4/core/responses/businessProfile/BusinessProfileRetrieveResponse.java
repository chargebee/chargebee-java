package com.chargebee.v4.core.responses.businessProfile;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BusinessProfileRetrieve operation. Contains the response data from
 * a single resource get operation.
 */
public final class BusinessProfileRetrieveResponse extends BaseResponse {
  private final Object businessProfile;

  private BusinessProfileRetrieveResponse(Object businessProfile, Response httpResponse) {
    super(httpResponse);

    this.businessProfile = businessProfile;
  }

  /** Parse JSON response into BusinessProfileRetrieveResponse object. */
  public static BusinessProfileRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BusinessProfileRetrieveResponse object with HTTP response. */
  public static BusinessProfileRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Object businessProfile = JsonUtil.getObject(json, "business_profile");

      return new BusinessProfileRetrieveResponse(businessProfile, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessProfileRetrieveResponse from JSON", e);
    }
  }

  /** Get the businessProfile from the response. */
  public Object getBusinessProfile() {
    return businessProfile;
  }
}
