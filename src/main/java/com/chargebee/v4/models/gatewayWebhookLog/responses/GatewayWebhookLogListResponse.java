package com.chargebee.v4.models.gatewayWebhookLog.responses;

import java.util.List;

import com.chargebee.v4.models.gatewayWebhookLog.GatewayWebhookLog;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.GatewayWebhookLogService;
import com.chargebee.v4.models.gatewayWebhookLog.params.GatewayWebhookLogListParams;

/** Immutable response object for GatewayWebhookLogList operation. Contains paginated list data. */
public final class GatewayWebhookLogListResponse {

  private final List<GatewayWebhookLogListItem> list;

  private final String nextOffset;

  private final GatewayWebhookLogService service;
  private final GatewayWebhookLogListParams originalParams;
  private final Response httpResponse;

  private GatewayWebhookLogListResponse(
      List<GatewayWebhookLogListItem> list,
      String nextOffset,
      GatewayWebhookLogService service,
      GatewayWebhookLogListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into GatewayWebhookLogListResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static GatewayWebhookLogListResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<GatewayWebhookLogListItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), GatewayWebhookLogListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new GatewayWebhookLogListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse GatewayWebhookLogListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into GatewayWebhookLogListResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static GatewayWebhookLogListResponse fromJson(
      String json,
      GatewayWebhookLogService service,
      GatewayWebhookLogListParams originalParams,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<GatewayWebhookLogListItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), GatewayWebhookLogListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new GatewayWebhookLogListResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse GatewayWebhookLogListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<GatewayWebhookLogListItem> getList() {
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
  public GatewayWebhookLogListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    GatewayWebhookLogListParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : GatewayWebhookLogListParams.builder())
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
    return "GatewayWebhookLogListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GatewayWebhookLogListResponse that = (GatewayWebhookLogListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class GatewayWebhookLogListItem {

    private GatewayWebhookLog gatewayWebhookLog;

    public GatewayWebhookLog getGatewayWebhookLog() {
      return gatewayWebhookLog;
    }

    public static GatewayWebhookLogListItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static GatewayWebhookLogListItem fromJson(JsonObject jsonObj) {
      GatewayWebhookLogListItem item = new GatewayWebhookLogListItem();

      JsonObject __gatewayWebhookLogObj = JsonUtil.getJsonObject(jsonObj, "gateway_webhook_log");
      if (__gatewayWebhookLogObj != null) {
        item.gatewayWebhookLog = GatewayWebhookLog.fromJson(__gatewayWebhookLogObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "GatewayWebhookLogListItem{" + "gatewayWebhookLog=" + gatewayWebhookLog + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      GatewayWebhookLogListItem that = (GatewayWebhookLogListItem) o;
      return java.util.Objects.equals(gatewayWebhookLog, that.gatewayWebhookLog);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(gatewayWebhookLog);
    }
  }
}
