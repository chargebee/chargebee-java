package com.chargebee.v4.core.responses.hostedPage;

import com.chargebee.v4.core.models.hostedPage.HostedPage;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for HostedPageUpdateCard operation. Contains the response data from the
 * API.
 */
public final class HostedPageUpdateCardResponse {

  private final HostedPage hostedPage;

  private HostedPageUpdateCardResponse(Builder builder) {

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPageUpdateCardResponse object. */
  public static HostedPageUpdateCardResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        builder.hostedPage(HostedPage.fromJson(__hostedPageJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPageUpdateCardResponse from JSON", e);
    }
  }

  /** Create a new builder for HostedPageUpdateCardResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HostedPageUpdateCardResponse. */
  public static class Builder {

    private HostedPage hostedPage;

    private Builder() {}

    public Builder hostedPage(HostedPage hostedPage) {
      this.hostedPage = hostedPage;
      return this;
    }

    public HostedPageUpdateCardResponse build() {
      return new HostedPageUpdateCardResponse(this);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }
}
