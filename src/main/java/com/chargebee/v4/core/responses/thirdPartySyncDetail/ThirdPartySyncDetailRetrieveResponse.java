package com.chargebee.v4.core.responses.thirdPartySyncDetail;

import com.chargebee.v4.core.models.thirdPartySyncDetail.ThirdPartySyncDetail;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ThirdPartySyncDetailRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class ThirdPartySyncDetailRetrieveResponse {

  private final ThirdPartySyncDetail thirdPartySyncDetail;

  private ThirdPartySyncDetailRetrieveResponse(ThirdPartySyncDetail thirdPartySyncDetail) {

    this.thirdPartySyncDetail = thirdPartySyncDetail;
  }

  /** Parse JSON response into ThirdPartySyncDetailRetrieveResponse object. */
  public static ThirdPartySyncDetailRetrieveResponse fromJson(String json) {
    try {

      ThirdPartySyncDetail thirdPartySyncDetail =
          ThirdPartySyncDetail.fromJson(JsonUtil.getObject(json, "third_party_sync_detail"));

      return new ThirdPartySyncDetailRetrieveResponse(thirdPartySyncDetail);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartySyncDetailRetrieveResponse from JSON", e);
    }
  }

  /** Get the thirdPartySyncDetail from the response. */
  public ThirdPartySyncDetail getThirdPartySyncDetail() {
    return thirdPartySyncDetail;
  }
}
