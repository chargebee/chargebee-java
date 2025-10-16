package com.chargebee.v4.core.responses.omnichannelSubscription;

import com.chargebee.v4.core.models.omnichannelSubscription.OmnichannelSubscription;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OmnichannelSubscriptionRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class OmnichannelSubscriptionRetrieveResponse extends BaseResponse {
  private final OmnichannelSubscription omnichannelSubscription;

  private OmnichannelSubscriptionRetrieveResponse(
      OmnichannelSubscription omnichannelSubscription, Response httpResponse) {
    super(httpResponse);

    this.omnichannelSubscription = omnichannelSubscription;
  }

  /** Parse JSON response into OmnichannelSubscriptionRetrieveResponse object. */
  public static OmnichannelSubscriptionRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OmnichannelSubscriptionRetrieveResponse object with HTTP response. */
  public static OmnichannelSubscriptionRetrieveResponse fromJson(
      String json, Response httpResponse) {
    try {

      OmnichannelSubscription omnichannelSubscription =
          OmnichannelSubscription.fromJson(JsonUtil.getObject(json, "omnichannel_subscription"));

      return new OmnichannelSubscriptionRetrieveResponse(omnichannelSubscription, httpResponse);
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
