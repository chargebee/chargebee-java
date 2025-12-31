package com.chargebee.v4.models.hostedPage.responses;

import com.chargebee.v4.models.hostedPage.HostedPage;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for HostedPageClaimGift operation. Contains the response data from the
 * API.
 */
public final class HostedPageClaimGiftResponse extends BaseResponse {
  private final HostedPage hostedPage;

  private HostedPageClaimGiftResponse(Builder builder) {
    super(builder.httpResponse);

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPageClaimGiftResponse object. */
  public static HostedPageClaimGiftResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into HostedPageClaimGiftResponse object with HTTP response. */
  public static HostedPageClaimGiftResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        builder.hostedPage(HostedPage.fromJson(__hostedPageJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPageClaimGiftResponse from JSON", e);
    }
  }

  /** Create a new builder for HostedPageClaimGiftResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HostedPageClaimGiftResponse. */
  public static class Builder {

    private HostedPage hostedPage;

    private Response httpResponse;

    private Builder() {}

    public Builder hostedPage(HostedPage hostedPage) {
      this.hostedPage = hostedPage;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public HostedPageClaimGiftResponse build() {
      return new HostedPageClaimGiftResponse(this);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }

  @Override
  public String toString() {
    return "HostedPageClaimGiftResponse{" + "hostedPage=" + hostedPage + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    HostedPageClaimGiftResponse that = (HostedPageClaimGiftResponse) o;
    return java.util.Objects.equals(hostedPage, that.hostedPage);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(hostedPage);
  }
}
