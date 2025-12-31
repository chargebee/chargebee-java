package com.chargebee.v4.models.omnichannelSubscriptionItem.responses;

import java.util.List;

import com.chargebee.v4.models.omnichannelSubscriptionItemScheduledChange.OmnichannelSubscriptionItemScheduledChange;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.OmnichannelSubscriptionItemService;
import com.chargebee.v4.models.omnichannelSubscriptionItem.params.OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesParams;

/**
 * Immutable response object for OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChanges
 * operation. Contains paginated list data.
 */
public final class OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesResponse {

  private final List<OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesItem> list;

  private final String nextOffset;

  private final String omnichannelSubscriptionItemId;

  private final OmnichannelSubscriptionItemService service;
  private final OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesParams
      originalParams;
  private final Response httpResponse;

  private OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesResponse(
      List<OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesItem> list,
      String nextOffset,
      String omnichannelSubscriptionItemId,
      OmnichannelSubscriptionItemService service,
      OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.omnichannelSubscriptionItemId = omnichannelSubscriptionItemId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into
   * OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesResponse fromJson(
      String json) {
    try {

      List<OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesResponse(
          list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesResponse from JSON",
          e);
    }
  }

  /**
   * Parse JSON response into
   * OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesResponse object with service
   * context for pagination (enables nextPage()).
   */
  public static OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesResponse fromJson(
      String json,
      OmnichannelSubscriptionItemService service,
      OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesParams originalParams,
      String omnichannelSubscriptionItemId,
      Response httpResponse) {
    try {

      List<OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesResponse(
          list, nextOffset, omnichannelSubscriptionItemId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesResponse from JSON",
          e);
    }
  }

  /** Get the list from the response. */
  public List<OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesItem> getList() {
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
  public OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesResponse nextPage()
      throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesParams
                    .builder())
            .offset(nextOffset)
            .build();

    return service.listOmniSubscriptionItemScheduleChanges(
        omnichannelSubscriptionItemId, nextParams);
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

  @Override
  public String toString() {
    return "OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesResponse{"
        + "list="
        + list
        + ", nextOffset="
        + nextOffset
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesResponse that =
        (OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesItem {

    private OmnichannelSubscriptionItemScheduledChange omnichannelSubscriptionItemScheduledChange;

    public OmnichannelSubscriptionItemScheduledChange
        getOmnichannelSubscriptionItemScheduledChange() {
      return omnichannelSubscriptionItemScheduledChange;
    }

    public static OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesItem fromJson(
        String json) {
      OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesItem item =
          new OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesItem();

      String __omnichannelSubscriptionItemScheduledChangeJson =
          JsonUtil.getObject(json, "omnichannel_subscription_item_scheduled_change");
      if (__omnichannelSubscriptionItemScheduledChangeJson != null) {
        item.omnichannelSubscriptionItemScheduledChange =
            OmnichannelSubscriptionItemScheduledChange.fromJson(
                __omnichannelSubscriptionItemScheduledChangeJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesItem{"
          + "omnichannelSubscriptionItemScheduledChange="
          + omnichannelSubscriptionItemScheduledChange
          + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesItem that =
          (OmnichannelSubscriptionItemListOmniSubscriptionItemScheduleChangesItem) o;
      return java.util.Objects.equals(
          omnichannelSubscriptionItemScheduledChange,
          that.omnichannelSubscriptionItemScheduledChange);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(omnichannelSubscriptionItemScheduledChange);
    }
  }
}
