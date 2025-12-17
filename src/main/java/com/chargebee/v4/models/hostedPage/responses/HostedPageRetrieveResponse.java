package com.chargebee.v4.models.hostedPage.responses;

import com.chargebee.v4.models.hostedPage.HostedPage;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for HostedPageRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class HostedPageRetrieveResponse extends BaseResponse {
  private final HostedPage hostedPage;

  private HostedPageRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPageRetrieveResponse object. */
  public static HostedPageRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into HostedPageRetrieveResponse object with HTTP response. */
  public static HostedPageRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        builder.hostedPage(HostedPage.fromJson(__hostedPageJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPageRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for HostedPageRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HostedPageRetrieveResponse. */
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

    public HostedPageRetrieveResponse build() {
      return new HostedPageRetrieveResponse(this);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }
}
