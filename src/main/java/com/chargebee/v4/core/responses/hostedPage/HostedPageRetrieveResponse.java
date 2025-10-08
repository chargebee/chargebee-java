package com.chargebee.v4.core.responses.hostedPage;

import com.chargebee.v4.core.models.hostedPage.HostedPage;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for HostedPageRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class HostedPageRetrieveResponse {

  private final HostedPage hostedPage;

  private final Response httpResponse;

  private HostedPageRetrieveResponse(HostedPage hostedPage, Response httpResponse) {

    this.hostedPage = hostedPage;

    this.httpResponse = httpResponse;
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
