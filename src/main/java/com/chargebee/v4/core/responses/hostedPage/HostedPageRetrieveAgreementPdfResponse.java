package com.chargebee.v4.core.responses.hostedPage;

import com.chargebee.v4.core.models.hostedPage.HostedPage;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for HostedPageRetrieveAgreementPdf operation. Contains the response
 * data from the API.
 */
public final class HostedPageRetrieveAgreementPdfResponse {

  private final HostedPage hostedPage;

  private HostedPageRetrieveAgreementPdfResponse(Builder builder) {

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPageRetrieveAgreementPdfResponse object. */
  public static HostedPageRetrieveAgreementPdfResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        builder.hostedPage(HostedPage.fromJson(__hostedPageJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse HostedPageRetrieveAgreementPdfResponse from JSON", e);
    }
  }

  /** Create a new builder for HostedPageRetrieveAgreementPdfResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HostedPageRetrieveAgreementPdfResponse. */
  public static class Builder {

    private HostedPage hostedPage;

    private Builder() {}

    public Builder hostedPage(HostedPage hostedPage) {
      this.hostedPage = hostedPage;
      return this;
    }

    public HostedPageRetrieveAgreementPdfResponse build() {
      return new HostedPageRetrieveAgreementPdfResponse(this);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }
}
