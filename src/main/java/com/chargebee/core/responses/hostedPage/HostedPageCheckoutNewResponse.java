package com.chargebee.core.responses.hostedPage;

import com.chargebee.core.models.hostedPage.HostedPage;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for HostedPageCheckoutNew operation. Contains the response data from
 * the API.
 */
public final class HostedPageCheckoutNewResponse {

  private final HostedPage hostedPage;

  private HostedPageCheckoutNewResponse(Builder builder) {

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPageCheckoutNewResponse object. */
  public static HostedPageCheckoutNewResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        builder.hostedPage(HostedPage.fromJson(__hostedPageJson));
      }

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

    private Builder() {}

    public Builder hostedPage(HostedPage hostedPage) {
      this.hostedPage = hostedPage;
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
