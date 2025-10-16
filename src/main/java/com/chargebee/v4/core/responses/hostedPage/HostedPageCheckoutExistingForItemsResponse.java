package com.chargebee.v4.core.responses.hostedPage;

import com.chargebee.v4.core.models.hostedPage.HostedPage;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for HostedPageCheckoutExistingForItems operation. Contains the response
 * data from the API.
 */
public final class HostedPageCheckoutExistingForItemsResponse extends BaseResponse {
  private final HostedPage hostedPage;

  private HostedPageCheckoutExistingForItemsResponse(Builder builder) {
    super(builder.httpResponse);

    this.hostedPage = builder.hostedPage;
  }

  /** Parse JSON response into HostedPageCheckoutExistingForItemsResponse object. */
  public static HostedPageCheckoutExistingForItemsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into HostedPageCheckoutExistingForItemsResponse object with HTTP response.
   */
  public static HostedPageCheckoutExistingForItemsResponse fromJson(
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
          "Failed to parse HostedPageCheckoutExistingForItemsResponse from JSON", e);
    }
  }

  /** Create a new builder for HostedPageCheckoutExistingForItemsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HostedPageCheckoutExistingForItemsResponse. */
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

    public HostedPageCheckoutExistingForItemsResponse build() {
      return new HostedPageCheckoutExistingForItemsResponse(this);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }
}
