package com.chargebee.v4.models.thirdPartySyncDetail.responses;

import com.chargebee.v4.models.thirdPartySyncDetail.ThirdPartySyncDetail;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartySyncDetailRetrieveLatestSync operation. Contains the
 * response data from a single resource get operation.
 */
public final class ThirdPartySyncDetailRetrieveLatestSyncResponse extends BaseResponse {
  private final ThirdPartySyncDetail thirdPartySyncDetail;

  private ThirdPartySyncDetailRetrieveLatestSyncResponse(
      ThirdPartySyncDetail thirdPartySyncDetail, Response httpResponse) {
    super(httpResponse);

    this.thirdPartySyncDetail = thirdPartySyncDetail;
  }

  /** Parse JSON response into ThirdPartySyncDetailRetrieveLatestSyncResponse object. */
  public static ThirdPartySyncDetailRetrieveLatestSyncResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into ThirdPartySyncDetailRetrieveLatestSyncResponse object with HTTP
   * response.
   */
  public static ThirdPartySyncDetailRetrieveLatestSyncResponse fromJson(
      String json, Response httpResponse) {
    try {

      ThirdPartySyncDetail thirdPartySyncDetail =
          ThirdPartySyncDetail.fromJson(JsonUtil.getObject(json, "third_party_sync_detail"));

      return new ThirdPartySyncDetailRetrieveLatestSyncResponse(thirdPartySyncDetail, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartySyncDetailRetrieveLatestSyncResponse from JSON", e);
    }
  }

  /** Get the thirdPartySyncDetail from the response. */
  public ThirdPartySyncDetail getThirdPartySyncDetail() {
    return thirdPartySyncDetail;
  }
}
