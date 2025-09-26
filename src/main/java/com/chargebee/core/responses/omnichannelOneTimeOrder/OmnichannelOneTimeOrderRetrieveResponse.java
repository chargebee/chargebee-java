package com.chargebee.core.responses.omnichannelOneTimeOrder;

import com.chargebee.core.models.omnichannelOneTimeOrder.OmnichannelOneTimeOrder;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for OmnichannelOneTimeOrderRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class OmnichannelOneTimeOrderRetrieveResponse {

  private final OmnichannelOneTimeOrder omnichannelOneTimeOrder;

  private OmnichannelOneTimeOrderRetrieveResponse(OmnichannelOneTimeOrder omnichannelOneTimeOrder) {

    this.omnichannelOneTimeOrder = omnichannelOneTimeOrder;
  }

  /** Parse JSON response into OmnichannelOneTimeOrderRetrieveResponse object. */
  public static OmnichannelOneTimeOrderRetrieveResponse fromJson(String json) {
    try {

      OmnichannelOneTimeOrder omnichannelOneTimeOrder =
          OmnichannelOneTimeOrder.fromJson(JsonUtil.getObject(json, "omnichannel_one_time_order"));

      return new OmnichannelOneTimeOrderRetrieveResponse(omnichannelOneTimeOrder);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelOneTimeOrderRetrieveResponse from JSON", e);
    }
  }

  /** Get the omnichannelOneTimeOrder from the response. */
  public OmnichannelOneTimeOrder getOmnichannelOneTimeOrder() {
    return omnichannelOneTimeOrder;
  }
}
