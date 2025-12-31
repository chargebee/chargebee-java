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

  private ThirdPartySyncDetailRetrieveLatestSyncResponse(Builder builder) {
    super(builder.httpResponse);

    this.thirdPartySyncDetail = builder.thirdPartySyncDetail;
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
      Builder builder = builder();

      String __thirdPartySyncDetailJson = JsonUtil.getObject(json, "third_party_sync_detail");
      if (__thirdPartySyncDetailJson != null) {
        builder.thirdPartySyncDetail(ThirdPartySyncDetail.fromJson(__thirdPartySyncDetailJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartySyncDetailRetrieveLatestSyncResponse from JSON", e);
    }
  }

  /** Create a new builder for ThirdPartySyncDetailRetrieveLatestSyncResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ThirdPartySyncDetailRetrieveLatestSyncResponse. */
  public static class Builder {

    private ThirdPartySyncDetail thirdPartySyncDetail;

    private Response httpResponse;

    private Builder() {}

    public Builder thirdPartySyncDetail(ThirdPartySyncDetail thirdPartySyncDetail) {
      this.thirdPartySyncDetail = thirdPartySyncDetail;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public ThirdPartySyncDetailRetrieveLatestSyncResponse build() {
      return new ThirdPartySyncDetailRetrieveLatestSyncResponse(this);
    }
  }

  /** Get the thirdPartySyncDetail from the response. */
  public ThirdPartySyncDetail getThirdPartySyncDetail() {
    return thirdPartySyncDetail;
  }

  @Override
  public String toString() {
    return "ThirdPartySyncDetailRetrieveLatestSyncResponse{"
        + "thirdPartySyncDetail="
        + thirdPartySyncDetail
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ThirdPartySyncDetailRetrieveLatestSyncResponse that =
        (ThirdPartySyncDetailRetrieveLatestSyncResponse) o;
    return java.util.Objects.equals(thirdPartySyncDetail, that.thirdPartySyncDetail);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(thirdPartySyncDetail);
  }
}
