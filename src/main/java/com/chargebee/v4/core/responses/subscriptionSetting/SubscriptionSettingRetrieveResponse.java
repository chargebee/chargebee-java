package com.chargebee.v4.core.responses.subscriptionSetting;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionSettingRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class SubscriptionSettingRetrieveResponse extends BaseResponse {
  private final Object subscriptionSetting;

  private SubscriptionSettingRetrieveResponse(Object subscriptionSetting, Response httpResponse) {
    super(httpResponse);

    this.subscriptionSetting = subscriptionSetting;
  }

  /** Parse JSON response into SubscriptionSettingRetrieveResponse object. */
  public static SubscriptionSettingRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into SubscriptionSettingRetrieveResponse object with HTTP response. */
  public static SubscriptionSettingRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Object subscriptionSetting = JsonUtil.getObject(json, "subscription_setting");

      return new SubscriptionSettingRetrieveResponse(subscriptionSetting, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionSettingRetrieveResponse from JSON", e);
    }
  }

  /** Get the subscriptionSetting from the response. */
  public Object getSubscriptionSetting() {
    return subscriptionSetting;
  }
}
