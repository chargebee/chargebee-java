package com.chargebee.v4.models.tpSiteUser.responses;

import com.chargebee.v4.models.tpSiteUser.TpSiteUser;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for TpSiteUserPayNowEnableLive operation. Contains the response data
 * from the API.
 */
public final class TpSiteUserPayNowEnableLiveResponse extends BaseResponse {
  private final TpSiteUser tpSiteUser;

  private TpSiteUserPayNowEnableLiveResponse(Builder builder) {
    super(builder.httpResponse);

    this.tpSiteUser = builder.tpSiteUser;
  }

  /** Parse JSON response into TpSiteUserPayNowEnableLiveResponse object. */
  public static TpSiteUserPayNowEnableLiveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into TpSiteUserPayNowEnableLiveResponse object with HTTP response. */
  public static TpSiteUserPayNowEnableLiveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __tpSiteUserJson = JsonUtil.getObject(json, "tp_site_user");
      if (__tpSiteUserJson != null) {
        builder.tpSiteUser(TpSiteUser.fromJson(__tpSiteUserJson));
      }

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder tpSiteUser(TpSiteUser tpSiteUser) {
      this.tpSiteUser = tpSiteUser;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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

  @Override
  public String toString() {
    return "TpSiteUserPayNowEnableLiveResponse{" + "tpSiteUser=" + tpSiteUser + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TpSiteUserPayNowEnableLiveResponse that = (TpSiteUserPayNowEnableLiveResponse) o;
    return java.util.Objects.equals(tpSiteUser, that.tpSiteUser);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(tpSiteUser);
  }
}
