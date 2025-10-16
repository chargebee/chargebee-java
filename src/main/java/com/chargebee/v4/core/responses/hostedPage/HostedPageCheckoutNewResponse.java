package com.chargebee.v4.core.responses.hostedPage;

import com.chargebee.v4.core.models.hostedPage.HostedPage;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for HostedPageCheckoutNew operation. Contains the response data from
 * the API.
 */
public final class HostedPageCheckoutNewResponse extends BaseResponse {
  private final HostedPage hostedPage;

  private HostedPageCheckoutNewResponse(Builder builder) {
    super(builder.httpResponse);

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPageCheckoutNewResponse object. */
  public static HostedPageCheckoutNewResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into HostedPageCheckoutNewResponse object with HTTP response. */
  public static HostedPageCheckoutNewResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        builder.hostedPage(HostedPage.fromJson(__hostedPageJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPageCheckoutNewResponse from JSON", e);
    }
  }

  /** Create a new builder for HostedPageCheckoutNewResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HostedPageCheckoutNewResponse. */
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

    public HostedPageCheckoutNewResponse build() {
      return new HostedPageCheckoutNewResponse(this);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }
}
