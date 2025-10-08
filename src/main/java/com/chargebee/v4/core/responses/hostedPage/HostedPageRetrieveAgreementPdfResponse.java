package com.chargebee.v4.core.responses.hostedPage;

import com.chargebee.v4.core.models.hostedPage.HostedPage;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for HostedPageRetrieveAgreementPdf operation. Contains the response
 * data from the API.
 */
public final class HostedPageRetrieveAgreementPdfResponse {

  private final HostedPage hostedPage;

  private final Response httpResponse;

  private HostedPageRetrieveAgreementPdfResponse(Builder builder) {

    this.hostedPage = builder.hostedPage;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into HostedPageRetrieveAgreementPdfResponse object. */
  public static HostedPageRetrieveAgreementPdfResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into HostedPageRetrieveAgreementPdfResponse object with HTTP response. */
  public static HostedPageRetrieveAgreementPdfResponse fromJson(
      String json, Response httpResponse) {
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

    public HostedPageRetrieveAgreementPdfResponse build() {
      return new HostedPageRetrieveAgreementPdfResponse(this);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
