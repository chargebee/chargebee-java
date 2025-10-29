package com.chargebee.v4.core.responses.omnichannelSubscriptionItem;

import java.util.List;

import com.chargebee.v4.core.models.omnichannelSubscriptionItemScheduledChange.OmnichannelSubscriptionItemScheduledChange;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.OmnichannelSubscriptionItemService;
import com.chargebee.v4.core.models.omnichannelSubscriptionItem.params.OmnichannelSubscriptionItemListOmniSubItemScheduleChangesParams;

/**
 * Immutable response object for OmnichannelSubscriptionItemListOmniSubItemScheduleChanges
 * operation. Contains paginated list data.
 */
public final class OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse {

  private final List<OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem> list;

  private final String nextOffset;

  private final String omnichannelSubscriptionItemId;

  private final OmnichannelSubscriptionItemService service;
  private final OmnichannelSubscriptionItemListOmniSubItemScheduleChangesParams originalParams;
  private final Response httpResponse;

  private OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse(
      List<OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem> list,
      String nextOffset,
      String omnichannelSubscriptionItemId,
      OmnichannelSubscriptionItemService service,
      OmnichannelSubscriptionItemListOmniSubItemScheduleChangesParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.omnichannelSubscriptionItemId = omnichannelSubscriptionItemId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse
   * object (no service context). Use this when you only need to read a single page (no nextPage()).
   */
  public static OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse fromJson(
      String json) {
    try {

      List<OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse(
          list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse from JSON",
          e);
    }
  }

  /**
   * Parse JSON response into OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse
   * object with service context for pagination (enables nextPage()).
   */
  public static OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse fromJson(
      String json,
      OmnichannelSubscriptionItemService service,
      OmnichannelSubscriptionItemListOmniSubItemScheduleChangesParams originalParams,
      String omnichannelSubscriptionItemId,
      Response httpResponse) {
    try {

      List<OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse(
          list, nextOffset, omnichannelSubscriptionItemId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse from JSON",
          e);
    }
  }

  /** Get the list from the response. */
  public List<OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem> getList() {
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
   * @throws Exception if unable to fetch next page
   */
  public OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse nextPage()
      throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    OmnichannelSubscriptionItemListOmniSubItemScheduleChangesParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : OmnichannelSubscriptionItemListOmniSubItemScheduleChangesParams.builder())
            .offset(nextOffset)
            .build();

    return service.listOmniSubItemScheduleChanges(omnichannelSubscriptionItemId, nextParams);
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

  public static class OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem {

    private OmnichannelSubscriptionItemScheduledChange omnichannelSubscriptionItemScheduledChange;

    public OmnichannelSubscriptionItemScheduledChange
        getOmnichannelSubscriptionItemScheduledChange() {
      return omnichannelSubscriptionItemScheduledChange;
    }

    public static OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem fromJson(
        String json) {
      OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem item =
          new OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem();

      String __omnichannelSubscriptionItemScheduledChangeJson =
          JsonUtil.getObject(json, "omnichannel_subscription_item_scheduled_change");
      if (__omnichannelSubscriptionItemScheduledChangeJson != null) {
        item.omnichannelSubscriptionItemScheduledChange =
            OmnichannelSubscriptionItemScheduledChange.fromJson(
                __omnichannelSubscriptionItemScheduledChangeJson);
      }

      return item;
    }
  }
}
