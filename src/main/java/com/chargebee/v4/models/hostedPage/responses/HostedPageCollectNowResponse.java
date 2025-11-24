package com.chargebee.v4.models.hostedPage.responses;

import com.chargebee.v4.models.hostedPage.HostedPage;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for HostedPageCollectNow operation. Contains the response data from the
 * API.
 */
public final class HostedPageCollectNowResponse extends BaseResponse {
  private final HostedPage hostedPage;

  private HostedPageCollectNowResponse(Builder builder) {
    super(builder.httpResponse);

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPageCollectNowResponse object. */
  public static HostedPageCollectNowResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into HostedPageCollectNowResponse object with HTTP response. */
  public static HostedPageCollectNowResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        builder.hostedPage(HostedPage.fromJson(__hostedPageJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPageCollectNowResponse from JSON", e);
    }
  }

  /** Create a new builder for HostedPageCollectNowResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HostedPageCollectNowResponse. */
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

    public HostedPageCollectNowResponse build() {
      return new HostedPageCollectNowResponse(this);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }
}
