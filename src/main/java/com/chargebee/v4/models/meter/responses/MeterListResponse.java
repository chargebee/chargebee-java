package com.chargebee.v4.models.meter.responses;

import java.util.List;

import com.chargebee.v4.models.meter.Meter;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.MeterService;
import com.chargebee.v4.models.meter.params.MeterListParams;

/** Immutable response object for MeterList operation. Contains paginated list data. */
public final class MeterListResponse {

  private final List<MeterListItem> list;

  private final String nextOffset;

  private final MeterService service;
  private final MeterListParams originalParams;
  private final Response httpResponse;

  private MeterListResponse(
      List<MeterListItem> list,
      String nextOffset,
      MeterService service,
      MeterListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into MeterListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static MeterListResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<MeterListItem> list =
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "list"), MeterListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new MeterListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse MeterListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into MeterListResponse object with service context for pagination (enables
   * nextPage()).
   */
  public static MeterListResponse fromJson(
      String json, MeterService service, MeterListParams originalParams, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<MeterListItem> list =
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "list"), MeterListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new MeterListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse MeterListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<MeterListItem> getList() {
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
  public MeterListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    MeterListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : MeterListParams.builder())
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

  @Override
  public String toString() {
    return "MeterListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MeterListResponse that = (MeterListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class MeterListItem {

    private Meter meter;

    public Meter getMeter() {
      return meter;
    }

    public static MeterListItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static MeterListItem fromJson(JsonObject jsonObj) {
      MeterListItem item = new MeterListItem();

      JsonObject __meterObj = JsonUtil.getJsonObject(jsonObj, "meter");
      if (__meterObj != null) {
        item.meter = Meter.fromJson(__meterObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "MeterListItem{" + "meter=" + meter + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      MeterListItem that = (MeterListItem) o;
      return java.util.Objects.equals(meter, that.meter);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(meter);
    }
  }
}
