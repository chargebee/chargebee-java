package com.chargebee.v4.core.responses.tpSiteUser;

import com.chargebee.v4.core.models.tpSiteUser.TpSiteUser;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for TpSiteUserPayNowEnableLive operation. Contains the response data
 * from the API.
 */
public final class TpSiteUserPayNowEnableLiveResponse {

  private final TpSiteUser tpSiteUser;

  private TpSiteUserPayNowEnableLiveResponse(Builder builder) {

    this.tpSiteUser = builder.tpSiteUser;
  }

  /** Parse JSON response into TpSiteUserPayNowEnableLiveResponse object. */
  public static TpSiteUserPayNowEnableLiveResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __tpSiteUserJson = JsonUtil.getObject(json, "tp_site_user");
      if (__tpSiteUserJson != null) {
        builder.tpSiteUser(TpSiteUser.fromJson(__tpSiteUserJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TpSiteUserPayNowEnableLiveResponse from JSON", e);
    }
  }

  /** Create a new builder for TpSiteUserPayNowEnableLiveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for TpSiteUserPayNowEnableLiveResponse. */
  public static class Builder {

    private TpSiteUser tpSiteUser;

    private Builder() {}

    public Builder tpSiteUser(TpSiteUser tpSiteUser) {
      this.tpSiteUser = tpSiteUser;
      return this;
    }

    public TpSiteUserPayNowEnableLiveResponse build() {
      return new TpSiteUserPayNowEnableLiveResponse(this);
    }
  }

  /** Get the tpSiteUser from the response. */
  public TpSiteUser getTpSiteUser() {
    return tpSiteUser;
  }
}
