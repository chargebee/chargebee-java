package com.chargebee.v4.models.subscriptionSetting.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RetrieveSubscriptionSetting operation. Contains the response data
 * from a single resource get operation.
 */
public final class RetrieveSubscriptionSettingResponse extends BaseResponse {
  private final Object subscriptionSetting;

  private RetrieveSubscriptionSettingResponse(Object subscriptionSetting, Response httpResponse) {
    super(httpResponse);

    this.subscriptionSetting = subscriptionSetting;
  }

  /** Parse JSON response into RetrieveSubscriptionSettingResponse object. */
  public static RetrieveSubscriptionSettingResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RetrieveSubscriptionSettingResponse object with HTTP response. */
  public static RetrieveSubscriptionSettingResponse fromJson(String json, Response httpResponse) {
    try {

      Object subscriptionSetting = JsonUtil.getObject(json, "subscription_setting");

      return new RetrieveSubscriptionSettingResponse(subscriptionSetting, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse RetrieveSubscriptionSettingResponse from JSON", e);
    }
  }

  /** Get the subscriptionSetting from the response. */
  public Object getSubscriptionSetting() {
    return subscriptionSetting;
  }
}
