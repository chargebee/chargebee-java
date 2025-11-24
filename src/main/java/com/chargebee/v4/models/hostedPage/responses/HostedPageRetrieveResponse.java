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

  private HostedPageRetrieveResponse(HostedPage hostedPage, Response httpResponse) {
    super(httpResponse);

    this.hostedPage = hostedPage;
  }

  /** Parse JSON response into HostedPageRetrieveResponse object. */
  public static HostedPageRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into HostedPageRetrieveResponse object with HTTP response. */
  public static HostedPageRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      HostedPage hostedPage = HostedPage.fromJson(JsonUtil.getObject(json, "hosted_page"));

      return new HostedPageRetrieveResponse(hostedPage, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPageRetrieveResponse from JSON", e);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }
}
