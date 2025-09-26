package com.chargebee.core.responses.hostedPage;

import com.chargebee.core.models.hostedPage.HostedPage;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for HostedPageRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class HostedPageRetrieveResponse {

  private final HostedPage hostedPage;

  private HostedPageRetrieveResponse(HostedPage hostedPage) {

    this.hostedPage = hostedPage;
  }

  /** Parse JSON response into HostedPageRetrieveResponse object. */
  public static HostedPageRetrieveResponse fromJson(String json) {
    try {

      HostedPage hostedPage = HostedPage.fromJson(JsonUtil.getObject(json, "hosted_page"));

      return new HostedPageRetrieveResponse(hostedPage);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPageRetrieveResponse from JSON", e);
    }
  }

  /** Get the hostedPage from the response. */
  public HostedPage getHostedPage() {
    return hostedPage;
  }
}
