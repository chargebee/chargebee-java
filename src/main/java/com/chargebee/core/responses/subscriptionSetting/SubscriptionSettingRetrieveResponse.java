package com.chargebee.core.responses.subscriptionSetting;

/**
 * Immutable response object for SubscriptionSettingRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class SubscriptionSettingRetrieveResponse {

  private SubscriptionSettingRetrieveResponse() {}

  /** Parse JSON response into SubscriptionSettingRetrieveResponse object. */
  public static SubscriptionSettingRetrieveResponse fromJson(String json) {
    try {

      return new SubscriptionSettingRetrieveResponse();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionSettingRetrieveResponse from JSON", e);
    }
  }
}
