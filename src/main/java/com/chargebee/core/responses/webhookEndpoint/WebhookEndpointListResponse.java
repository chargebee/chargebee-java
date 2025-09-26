package com.chargebee.core.responses.webhookEndpoint;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.webhookEndpoint.WebhookEndpoint;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.WebhookEndpointService;
import com.chargebee.core.models.webhookEndpoint.params.WebhookEndpointListParams;

/**
 * Immutable response object for WebhookEndpointList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class WebhookEndpointListResponse
    implements Iterable<WebhookEndpointListResponse.WebhookEndpointListItem> {

  private final List<WebhookEndpointListItem> list;

  private final String nextOffset;

  private final WebhookEndpointService service;
  private final WebhookEndpointListParams originalParams;
  private final boolean isAutoPaginate;

  private WebhookEndpointListResponse(
      List<WebhookEndpointListItem> list,
      String nextOffset,
      WebhookEndpointService service,
      WebhookEndpointListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private WebhookEndpointListResponse(
      List<WebhookEndpointListItem> list,
      String nextOffset,
      WebhookEndpointService service,
      WebhookEndpointListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
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

      return new WebhookEndpointListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse WebhookEndpointListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into WebhookEndpointListResponse object with service context for pagination
   * (enables nextPage(), autoPaginate()).
   */
  public static WebhookEndpointListResponse fromJson(
      String json, WebhookEndpointService service, WebhookEndpointListParams originalParams) {
    try {

      List<WebhookEndpointListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(WebhookEndpointListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new WebhookEndpointListResponse(list, nextOffset, service, originalParams);
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
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    WebhookEndpointListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public WebhookEndpointListResponse autoPaginate() {
    return new WebhookEndpointListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<WebhookEndpointListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<WebhookEndpointListItem> {
    private WebhookEndpointListResponse currentPage = WebhookEndpointListResponse.this;
    private Iterator<WebhookEndpointListItem> currentIterator = currentPage.list.iterator();

    @Override
    public boolean hasNext() {
      if (currentIterator.hasNext()) {
        return true;
      }

      // Try to load next page if available
      if (currentPage.hasNextPage()) {
        try {
          currentPage = currentPage.nextPage();
          currentIterator = currentPage.list.iterator();
          return currentIterator.hasNext();
        } catch (Exception e) {
          throw new RuntimeException("Failed to fetch next page", e);
        }
      }

      return false;
    }

    @Override
    public WebhookEndpointListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
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
