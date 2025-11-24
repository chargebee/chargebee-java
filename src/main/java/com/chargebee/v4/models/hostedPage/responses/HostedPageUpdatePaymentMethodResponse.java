package com.chargebee.v4.models.hostedPage.responses;

import com.chargebee.v4.models.hostedPage.HostedPage;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for HostedPageUpdatePaymentMethod operation. Contains the response data
 * from the API.
 */
public final class HostedPageUpdatePaymentMethodResponse extends BaseResponse {
  private final HostedPage hostedPage;

  private HostedPageUpdatePaymentMethodResponse(Builder builder) {
    super(builder.httpResponse);

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPageUpdatePaymentMethodResponse object. */
  public static HostedPageUpdatePaymentMethodResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into HostedPageUpdatePaymentMethodResponse object with HTTP response. */
  public static HostedPageUpdatePaymentMethodResponse fromJson(String json, Response httpResponse) {
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
          "Failed to parse HostedPageUpdatePaymentMethodResponse from JSON", e);
    }
  }

  /** Create a new builder for HostedPageUpdatePaymentMethodResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HostedPageUpdatePaymentMethodResponse. */
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

    public HostedPageUpdatePaymentMethodResponse build() {
      return new HostedPageUpdatePaymentMethodResponse(this);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }
}
