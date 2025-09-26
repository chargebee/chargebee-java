package com.chargebee.core.responses.hostedPage;

import com.chargebee.core.models.hostedPage.HostedPage;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for HostedPageUpdatePaymentMethod operation. Contains the response data
 * from the API.
 */
public final class HostedPageUpdatePaymentMethodResponse {

  private final HostedPage hostedPage;

  private HostedPageUpdatePaymentMethodResponse(Builder builder) {

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPageUpdatePaymentMethodResponse object. */
  public static HostedPageUpdatePaymentMethodResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        builder.hostedPage(HostedPage.fromJson(__hostedPageJson));
      }

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

    private Builder() {}

    public Builder hostedPage(HostedPage hostedPage) {
      this.hostedPage = hostedPage;
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
