package com.chargebee.core.responses.event;

import java.util.List;

import com.chargebee.core.models.event.Event;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.EventService;
import com.chargebee.core.models.event.params.EventListParams;

/** Immutable response object for EventList operation. Contains paginated list data. */
public final class EventListResponse {

  private final List<EventListItem> list;

  private final String nextOffset;

  private final EventService service;
  private final EventListParams originalParams;

  private EventListResponse(
      List<EventListItem> list,
      String nextOffset,
      EventService service,
      EventListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into EventListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static EventListResponse fromJson(String json) {
    try {

      List<EventListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(EventListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new EventListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EventListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into EventListResponse object with service context for pagination (enables
   * nextPage()).
   */
  public static EventListResponse fromJson(
      String json, EventService service, EventListParams originalParams) {
    try {

      List<EventListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(EventListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new EventListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EventListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<EventListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<EventListItem> items() {
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
  public EventListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    EventListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class EventListItem {

    private Event event;

    public Event getEvent() {
      return event;
    }

    public static EventListItem fromJson(String json) {
      EventListItem item = new EventListItem();

      String __eventJson = JsonUtil.getObject(json, "event");
      if (__eventJson != null) {
        item.event = Event.fromJson(__eventJson);
      }

      return item;
    }
  }
}
