package com.chargebee.v4.models.hostedPage.responses;

import com.chargebee.v4.models.hostedPage.HostedPage;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for HostedPagePreCancel operation. Contains the response data from the
 * API.
 */
public final class HostedPagePreCancelResponse extends BaseResponse {
  private final HostedPage hostedPage;

  private HostedPagePreCancelResponse(Builder builder) {
    super(builder.httpResponse);

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPagePreCancelResponse object. */
  public static HostedPagePreCancelResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into HostedPagePreCancelResponse object with HTTP response. */
  public static HostedPagePreCancelResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        builder.hostedPage(HostedPage.fromJson(__hostedPageJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPagePreCancelResponse from JSON", e);
    }
  }

  /** Create a new builder for HostedPagePreCancelResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HostedPagePreCancelResponse. */
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

    public HostedPagePreCancelResponse build() {
      return new HostedPagePreCancelResponse(this);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }

  @Override
  public String toString() {
    return "HostedPagePreCancelResponse{" + "hostedPage=" + hostedPage + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    HostedPagePreCancelResponse that = (HostedPagePreCancelResponse) o;
    return java.util.Objects.equals(hostedPage, that.hostedPage);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(hostedPage);
  }
}
