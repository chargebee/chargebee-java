package com.chargebee.v4.models.gatewayRequestLog.responses;

import java.util.List;

import com.chargebee.v4.models.gatewayRequestLog.GatewayRequestLog;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.GatewayRequestLogService;
import com.chargebee.v4.models.gatewayRequestLog.params.GatewayRequestLogListParams;

/** Immutable response object for GatewayRequestLogList operation. Contains paginated list data. */
public final class GatewayRequestLogListResponse {

  private final List<GatewayRequestLogListItem> list;

  private final String nextOffset;

  private final GatewayRequestLogService service;
  private final GatewayRequestLogListParams originalParams;
  private final Response httpResponse;

  private GatewayRequestLogListResponse(
      List<GatewayRequestLogListItem> list,
      String nextOffset,
      GatewayRequestLogService service,
      GatewayRequestLogListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into GatewayRequestLogListResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static GatewayRequestLogListResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<GatewayRequestLogListItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), GatewayRequestLogListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new GatewayRequestLogListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse GatewayRequestLogListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into GatewayRequestLogListResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static GatewayRequestLogListResponse fromJson(
      String json,
      GatewayRequestLogService service,
      GatewayRequestLogListParams originalParams,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<GatewayRequestLogListItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), GatewayRequestLogListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new GatewayRequestLogListResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse GatewayRequestLogListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<GatewayRequestLogListItem> getList() {
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
  public GatewayRequestLogListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    GatewayRequestLogListParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : GatewayRequestLogListParams.builder())
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
    return "GatewayRequestLogListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GatewayRequestLogListResponse that = (GatewayRequestLogListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class GatewayRequestLogListItem {

    private GatewayRequestLog gatewayRequestLog;

    public GatewayRequestLog getGatewayRequestLog() {
      return gatewayRequestLog;
    }

    public static GatewayRequestLogListItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static GatewayRequestLogListItem fromJson(JsonObject jsonObj) {
      GatewayRequestLogListItem item = new GatewayRequestLogListItem();

      JsonObject __gatewayRequestLogObj = JsonUtil.getJsonObject(jsonObj, "gateway_request_log");
      if (__gatewayRequestLogObj != null) {
        item.gatewayRequestLog = GatewayRequestLog.fromJson(__gatewayRequestLogObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "GatewayRequestLogListItem{" + "gatewayRequestLog=" + gatewayRequestLog + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      GatewayRequestLogListItem that = (GatewayRequestLogListItem) o;
      return java.util.Objects.equals(gatewayRequestLog, that.gatewayRequestLog);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(gatewayRequestLog);
    }
  }
}
