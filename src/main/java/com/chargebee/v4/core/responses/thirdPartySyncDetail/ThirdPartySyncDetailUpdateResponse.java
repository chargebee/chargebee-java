package com.chargebee.v4.core.responses.thirdPartySyncDetail;

import com.chargebee.v4.core.models.thirdPartySyncDetail.ThirdPartySyncDetail;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ThirdPartySyncDetailUpdate operation. Contains the response data
 * from the API.
 */
public final class ThirdPartySyncDetailUpdateResponse {

  private final ThirdPartySyncDetail thirdPartySyncDetail;

  private ThirdPartySyncDetailUpdateResponse(Builder builder) {

    this.thirdPartySyncDetail = builder.thirdPartySyncDetail;
  }

  /** Parse JSON response into ThirdPartySyncDetailUpdateResponse object. */
  public static ThirdPartySyncDetailUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __thirdPartySyncDetailJson = JsonUtil.getObject(json, "third_party_sync_detail");
      if (__thirdPartySyncDetailJson != null) {
        builder.thirdPartySyncDetail(ThirdPartySyncDetail.fromJson(__thirdPartySyncDetailJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ThirdPartySyncDetailUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for ThirdPartySyncDetailUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ThirdPartySyncDetailUpdateResponse. */
  public static class Builder {

    private ThirdPartySyncDetail thirdPartySyncDetail;

    private Builder() {}

    public Builder thirdPartySyncDetail(ThirdPartySyncDetail thirdPartySyncDetail) {
      this.thirdPartySyncDetail = thirdPartySyncDetail;
      return this;
    }

    public ThirdPartySyncDetailUpdateResponse build() {
      return new ThirdPartySyncDetailUpdateResponse(this);
    }
  }

  /** Get the thirdPartySyncDetail from the response. */
  public ThirdPartySyncDetail getThirdPartySyncDetail() {
    return thirdPartySyncDetail;
  }
}
