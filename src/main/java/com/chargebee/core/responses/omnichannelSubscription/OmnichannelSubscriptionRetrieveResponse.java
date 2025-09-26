package com.chargebee.core.responses.omnichannelSubscription;

import com.chargebee.core.models.omnichannelSubscription.OmnichannelSubscription;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for OmnichannelSubscriptionRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class OmnichannelSubscriptionRetrieveResponse {

  private final OmnichannelSubscription omnichannelSubscription;

  private OmnichannelSubscriptionRetrieveResponse(OmnichannelSubscription omnichannelSubscription) {

    this.omnichannelSubscription = omnichannelSubscription;
  }

  /** Parse JSON response into OmnichannelSubscriptionRetrieveResponse object. */
  public static OmnichannelSubscriptionRetrieveResponse fromJson(String json) {
    try {

      OmnichannelSubscription omnichannelSubscription =
          OmnichannelSubscription.fromJson(JsonUtil.getObject(json, "omnichannel_subscription"));

      return new OmnichannelSubscriptionRetrieveResponse(omnichannelSubscription);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelSubscriptionRetrieveResponse from JSON", e);
    }
  }

  /** Get the omnichannelSubscription from the response. */
  public OmnichannelSubscription getOmnichannelSubscription() {
    return omnichannelSubscription;
  }
}
