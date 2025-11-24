package com.chargebee.v4.models.omnichannelOneTimeOrder.responses;

import com.chargebee.v4.models.omnichannelOneTimeOrder.OmnichannelOneTimeOrder;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OmnichannelOneTimeOrderRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class OmnichannelOneTimeOrderRetrieveResponse extends BaseResponse {
  private final OmnichannelOneTimeOrder omnichannelOneTimeOrder;

  private OmnichannelOneTimeOrderRetrieveResponse(
      OmnichannelOneTimeOrder omnichannelOneTimeOrder, Response httpResponse) {
    super(httpResponse);

    this.omnichannelOneTimeOrder = omnichannelOneTimeOrder;
  }

  /** Parse JSON response into OmnichannelOneTimeOrderRetrieveResponse object. */
  public static OmnichannelOneTimeOrderRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OmnichannelOneTimeOrderRetrieveResponse object with HTTP response. */
  public static OmnichannelOneTimeOrderRetrieveResponse fromJson(
      String json, Response httpResponse) {
    try {

      OmnichannelOneTimeOrder omnichannelOneTimeOrder =
          OmnichannelOneTimeOrder.fromJson(JsonUtil.getObject(json, "omnichannel_one_time_order"));

      return new OmnichannelOneTimeOrderRetrieveResponse(omnichannelOneTimeOrder, httpResponse);
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
