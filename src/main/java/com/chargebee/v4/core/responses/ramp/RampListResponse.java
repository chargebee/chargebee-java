package com.chargebee.v4.core.responses.ramp;

import java.util.List;

import com.chargebee.v4.core.models.ramp.Ramp;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.RampService;
import com.chargebee.v4.core.models.ramp.params.RampListParams;

/** Immutable response object for RampList operation. Contains paginated list data. */
public final class RampListResponse {

  private final List<RampListItem> list;

  private final String nextOffset;

  private final RampService service;
  private final RampListParams originalParams;
  private final Response httpResponse;

  private RampListResponse(
      List<RampListItem> list,
      String nextOffset,
      RampService service,
      RampListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into RampListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static RampListResponse fromJson(String json) {
    try {

      List<RampListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(RampListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new RampListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RampListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into RampListResponse object with service context for pagination (enables
   * nextPage()).
   */
  public static RampListResponse fromJson(
      String json, RampService service, RampListParams originalParams, Response httpResponse) {
    try {

      List<RampListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(RampListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new RampListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RampListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<RampListItem> getList() {
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
  public RampListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    RampListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : RampListParams.builder())
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

  public static class RampListItem {

    private Ramp ramp;

    public Ramp getRamp() {
      return ramp;
    }

    public static RampListItem fromJson(String json) {
      RampListItem item = new RampListItem();

      String __rampJson = JsonUtil.getObject(json, "ramp");
      if (__rampJson != null) {
        item.ramp = Ramp.fromJson(__rampJson);
      }

      return item;
    }
  }
}
