package com.chargebee.v4.core.responses.hostedPage;

import com.chargebee.v4.core.models.hostedPage.HostedPage;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for HostedPageCheckoutNewForItems operation. Contains the response data
 * from the API.
 */
public final class HostedPageCheckoutNewForItemsResponse extends BaseResponse {
  private final HostedPage hostedPage;

  private HostedPageCheckoutNewForItemsResponse(Builder builder) {
    super(builder.httpResponse);

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPageCheckoutNewForItemsResponse object. */
  public static HostedPageCheckoutNewForItemsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into HostedPageCheckoutNewForItemsResponse object with HTTP response. */
  public static HostedPageCheckoutNewForItemsResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        builder.hostedPage(HostedPage.fromJson(__hostedPageJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse HostedPageCheckoutNewForItemsResponse from JSON", e);
    }
  }

  /** Create a new builder for HostedPageCheckoutNewForItemsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HostedPageCheckoutNewForItemsResponse. */
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

    public HostedPageCheckoutNewForItemsResponse build() {
      return new HostedPageCheckoutNewForItemsResponse(this);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }
}
