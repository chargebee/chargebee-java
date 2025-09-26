package com.chargebee.core.responses.hostedPage;

import com.chargebee.core.models.hostedPage.HostedPage;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for HostedPageAcknowledge operation. Contains the response data from
 * the API.
 */
public final class HostedPageAcknowledgeResponse {

  private final HostedPage hostedPage;

  private HostedPageAcknowledgeResponse(Builder builder) {

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPageAcknowledgeResponse object. */
  public static HostedPageAcknowledgeResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        builder.hostedPage(HostedPage.fromJson(__hostedPageJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPageAcknowledgeResponse from JSON", e);
    }
  }

  /** Create a new builder for HostedPageAcknowledgeResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HostedPageAcknowledgeResponse. */
  public static class Builder {

    private HostedPage hostedPage;

    private Builder() {}

    public Builder hostedPage(HostedPage hostedPage) {
      this.hostedPage = hostedPage;
      return this;
    }

    public HostedPageAcknowledgeResponse build() {
      return new HostedPageAcknowledgeResponse(this);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }
}
