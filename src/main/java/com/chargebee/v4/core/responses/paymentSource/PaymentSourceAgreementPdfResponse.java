package com.chargebee.v4.core.responses.paymentSource;

import com.chargebee.v4.core.models.download.Download;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for PaymentSourceAgreementPdf operation. Contains the response data
 * from the API.
 */
public final class PaymentSourceAgreementPdfResponse {

  private final Download download;

  private PaymentSourceAgreementPdfResponse(Builder builder) {

    this.download = builder.download;
  }

  /** Parse JSON response into PaymentSourceAgreementPdfResponse object. */
  public static PaymentSourceAgreementPdfResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __downloadJson = JsonUtil.getObject(json, "download");
      if (__downloadJson != null) {
        builder.download(Download.fromJson(__downloadJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentSourceAgreementPdfResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentSourceAgreementPdfResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentSourceAgreementPdfResponse. */
  public static class Builder {

    private Download download;

    private Builder() {}

    public Builder download(Download download) {
      this.download = download;
      return this;
    }

    public PaymentSourceAgreementPdfResponse build() {
      return new PaymentSourceAgreementPdfResponse(this);
    }
  }

  /** Get the download from the response. */
  public Download getDownload() {
    return download;
  }
}
