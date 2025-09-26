package com.chargebee.core.responses.hostedPage;

import com.chargebee.core.models.hostedPage.HostedPage;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for HostedPageCheckoutOneTimeForItems operation. Contains the response
 * data from the API.
 */
public final class HostedPageCheckoutOneTimeForItemsResponse {

  private final HostedPage hostedPage;

  private HostedPageCheckoutOneTimeForItemsResponse(Builder builder) {

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPageCheckoutOneTimeForItemsResponse object. */
  public static HostedPageCheckoutOneTimeForItemsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        builder.hostedPage(HostedPage.fromJson(__hostedPageJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse HostedPageCheckoutOneTimeForItemsResponse from JSON", e);
    }
  }

  /** Create a new builder for HostedPageCheckoutOneTimeForItemsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HostedPageCheckoutOneTimeForItemsResponse. */
  public static class Builder {

    private HostedPage hostedPage;

    private Builder() {}

    public Builder hostedPage(HostedPage hostedPage) {
      this.hostedPage = hostedPage;
      return this;
    }

    public HostedPageCheckoutOneTimeForItemsResponse build() {
      return new HostedPageCheckoutOneTimeForItemsResponse(this);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }
}
