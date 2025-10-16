package com.chargebee.v4.core.responses.thirdPartySyncDetail;

import com.chargebee.v4.core.models.thirdPartySyncDetail.ThirdPartySyncDetail;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartySyncDetailRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class ThirdPartySyncDetailRetrieveResponse extends BaseResponse {
  private final ThirdPartySyncDetail thirdPartySyncDetail;

  private ThirdPartySyncDetailRetrieveResponse(
      ThirdPartySyncDetail thirdPartySyncDetail, Response httpResponse) {
    super(httpResponse);

    this.thirdPartySyncDetail = thirdPartySyncDetail;
  }

  /** Parse JSON response into ThirdPartySyncDetailRetrieveResponse object. */
  public static ThirdPartySyncDetailRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ThirdPartySyncDetailRetrieveResponse object with HTTP response. */
  public static ThirdPartySyncDetailRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      ThirdPartySyncDetail thirdPartySyncDetail =
          ThirdPartySyncDetail.fromJson(JsonUtil.getObject(json, "third_party_sync_detail"));

      return new ThirdPartySyncDetailRetrieveResponse(thirdPartySyncDetail, httpResponse);
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
