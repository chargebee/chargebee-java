package com.chargebee.v4.core.responses.webhookEndpoint;

import java.util.List;

import com.chargebee.v4.core.models.webhookEndpoint.WebhookEndpoint;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.WebhookEndpointService;
import com.chargebee.v4.core.models.webhookEndpoint.params.WebhookEndpointListParams;

/** Immutable response object for WebhookEndpointList operation. Contains paginated list data. */
public final class WebhookEndpointListResponse {

  private final List<WebhookEndpointListItem> list;

  private final String nextOffset;

  private final WebhookEndpointService service;
  private final WebhookEndpointListParams originalParams;
  private final Response httpResponse;

  private WebhookEndpointListResponse(
      List<WebhookEndpointListItem> list,
      String nextOffset,
      WebhookEndpointService service,
      WebhookEndpointListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into WebhookEndpointListResponse object (no service context). Use this when
   * you only need to read a single page (no nextPage()).
   */
  public static WebhookEndpointListResponse fromJson(String json) {
    try {

      List<WebhookEndpointListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(WebhookEndpointListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new WebhookEndpointListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse WebhookEndpointListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into WebhookEndpointListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static WebhookEndpointListResponse fromJson(
      String json,
      WebhookEndpointService service,
      WebhookEndpointListParams originalParams,
      Response httpResponse) {
    try {

      List<WebhookEndpointListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(WebhookEndpointListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new WebhookEndpointListResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse WebhookEndpointListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<WebhookEndpointListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<WebhookEndpointListItem> items() {
    return list;
  }

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws Exception if unable to fetch next page
   */
  public WebhookEndpointListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    // Create new params with the next offset
    WebhookEndpointListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : WebhookEndpointListParams.builder())
            .offset(nextOffset)
            .build();

    return service.list(nextParams);
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

  public static class WebhookEndpointListItem {

    private WebhookEndpoint webhookEndpoint;

    public WebhookEndpoint getWebhookEndpoint() {
      return webhookEndpoint;
    }

    public static WebhookEndpointListItem fromJson(String json) {
      WebhookEndpointListItem item = new WebhookEndpointListItem();

      String __webhookEndpointJson = JsonUtil.getObject(json, "webhook_endpoint");
      if (__webhookEndpointJson != null) {
        item.webhookEndpoint = WebhookEndpoint.fromJson(__webhookEndpointJson);
      }

      return item;
    }
  }
}
