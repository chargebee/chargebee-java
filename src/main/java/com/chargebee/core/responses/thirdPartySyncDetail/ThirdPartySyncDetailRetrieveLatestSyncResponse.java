package com.chargebee.core.responses.thirdPartySyncDetail;

import com.chargebee.core.models.thirdPartySyncDetail.ThirdPartySyncDetail;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ThirdPartySyncDetailRetrieveLatestSync operation. Contains the
 * response data from a single resource get operation.
 */
public final class ThirdPartySyncDetailRetrieveLatestSyncResponse {

  private final ThirdPartySyncDetail thirdPartySyncDetail;

  private ThirdPartySyncDetailRetrieveLatestSyncResponse(
      ThirdPartySyncDetail thirdPartySyncDetail) {

    this.thirdPartySyncDetail = thirdPartySyncDetail;
  }

  /** Parse JSON response into ThirdPartySyncDetailRetrieveLatestSyncResponse object. */
  public static ThirdPartySyncDetailRetrieveLatestSyncResponse fromJson(String json) {
    try {

      ThirdPartySyncDetail thirdPartySyncDetail =
          ThirdPartySyncDetail.fromJson(JsonUtil.getObject(json, "third_party_sync_detail"));

      return new ThirdPartySyncDetailRetrieveLatestSyncResponse(thirdPartySyncDetail);
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
