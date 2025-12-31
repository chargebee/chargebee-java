package com.chargebee.v4.models.hostedPage.responses;

import com.chargebee.v4.models.hostedPage.HostedPage;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for HostedPageUpdateCard operation. Contains the response data from the
 * API.
 */
public final class HostedPageUpdateCardResponse extends BaseResponse {
  private final HostedPage hostedPage;

  private HostedPageUpdateCardResponse(Builder builder) {
    super(builder.httpResponse);

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPageUpdateCardResponse object. */
  public static HostedPageUpdateCardResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into HostedPageUpdateCardResponse object with HTTP response. */
  public static HostedPageUpdateCardResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        builder.hostedPage(HostedPage.fromJson(__hostedPageJson));
      }

      builder.httpResponse(httpResponse);
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

    public HostedPageUpdateCardResponse build() {
      return new HostedPageUpdateCardResponse(this);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }

  @Override
  public String toString() {
    return "HostedPageUpdateCardResponse{" + "hostedPage=" + hostedPage + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HostedPageUpdateCardResponse that = (HostedPageUpdateCardResponse) o;
    return java.util.Objects.equals(hostedPage, that.hostedPage);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(hostedPage);
  }
}
