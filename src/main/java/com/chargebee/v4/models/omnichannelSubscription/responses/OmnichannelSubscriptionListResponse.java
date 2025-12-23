package com.chargebee.v4.models.omnichannelSubscription.responses;

import java.util.List;

import com.chargebee.v4.models.omnichannelSubscription.OmnichannelSubscription;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.OmnichannelSubscriptionService;
import com.chargebee.v4.models.omnichannelSubscription.params.OmnichannelSubscriptionListParams;

/**
 * Immutable response object for OmnichannelSubscriptionList operation. Contains paginated list
 * data.
 */
public final class OmnichannelSubscriptionListResponse {

  private final List<OmnichannelSubscriptionListItem> list;

  private final String nextOffset;

  private final OmnichannelSubscriptionService service;
  private final OmnichannelSubscriptionListParams originalParams;
  private final Response httpResponse;

  private OmnichannelSubscriptionListResponse(
      List<OmnichannelSubscriptionListItem> list,
      String nextOffset,
      OmnichannelSubscriptionService service,
      OmnichannelSubscriptionListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into OmnichannelSubscriptionListResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static OmnichannelSubscriptionListResponse fromJson(String json) {
    try {

      List<OmnichannelSubscriptionListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OmnichannelSubscriptionListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelSubscriptionListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelSubscriptionListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into OmnichannelSubscriptionListResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static OmnichannelSubscriptionListResponse fromJson(
      String json,
      OmnichannelSubscriptionService service,
      OmnichannelSubscriptionListParams originalParams,
      Response httpResponse) {
    try {

      List<OmnichannelSubscriptionListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OmnichannelSubscriptionListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelSubscriptionListResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelSubscriptionListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<OmnichannelSubscriptionListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public OmnichannelSubscriptionListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    OmnichannelSubscriptionListParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : OmnichannelSubscriptionListParams.builder())
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

  public static class OmnichannelSubscriptionListItem {

    private OmnichannelSubscription omnichannelSubscription;

    public OmnichannelSubscription getOmnichannelSubscription() {
      return omnichannelSubscription;
    }

    public static OmnichannelSubscriptionListItem fromJson(String json) {
      OmnichannelSubscriptionListItem item = new OmnichannelSubscriptionListItem();

      String __omnichannelSubscriptionJson = JsonUtil.getObject(json, "omnichannel_subscription");
      if (__omnichannelSubscriptionJson != null) {
        item.omnichannelSubscription =
            OmnichannelSubscription.fromJson(__omnichannelSubscriptionJson);
      }

      return item;
    }
  }
}
