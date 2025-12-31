package com.chargebee.v4.models.hostedPage.responses;

import com.chargebee.v4.models.hostedPage.HostedPage;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for HostedPageAcceptQuote operation. Contains the response data from
 * the API.
 */
public final class HostedPageAcceptQuoteResponse extends BaseResponse {
  private final HostedPage hostedPage;

  private HostedPageAcceptQuoteResponse(Builder builder) {
    super(builder.httpResponse);

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPageAcceptQuoteResponse object. */
  public static HostedPageAcceptQuoteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into HostedPageAcceptQuoteResponse object with HTTP response. */
  public static HostedPageAcceptQuoteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        builder.hostedPage(HostedPage.fromJson(__hostedPageJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPageAcceptQuoteResponse from JSON", e);
    }
  }

  /** Create a new builder for HostedPageAcceptQuoteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HostedPageAcceptQuoteResponse. */
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

    public HostedPageAcceptQuoteResponse build() {
      return new HostedPageAcceptQuoteResponse(this);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }

  @Override
  public String toString() {
    return "HostedPageAcceptQuoteResponse{" + "hostedPage=" + hostedPage + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HostedPageAcceptQuoteResponse that = (HostedPageAcceptQuoteResponse) o;
    return java.util.Objects.equals(hostedPage, that.hostedPage);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(hostedPage);
  }
}
