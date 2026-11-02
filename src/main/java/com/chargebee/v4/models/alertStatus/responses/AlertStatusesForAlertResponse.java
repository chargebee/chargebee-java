package com.chargebee.v4.models.alertStatus.responses;

import java.util.List;

import com.chargebee.v4.models.alertStatus.AlertStatus;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.AlertStatusService;
import com.chargebee.v4.models.alertStatus.params.AlertStatusesForAlertParams;

/** Immutable response object for AlertStatusesForAlert operation. Contains paginated list data. */
public final class AlertStatusesForAlertResponse {

  private final List<AlertStatusAlertStatusesForAlertItem> list;

  private final String nextOffset;

  private final String alertId;

  private final AlertStatusService service;
  private final AlertStatusesForAlertParams originalParams;
  private final Response httpResponse;

  private AlertStatusesForAlertResponse(
      List<AlertStatusAlertStatusesForAlertItem> list,
      String nextOffset,
      String alertId,
      AlertStatusService service,
      AlertStatusesForAlertParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.alertId = alertId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into AlertStatusesForAlertResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static AlertStatusesForAlertResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<AlertStatusAlertStatusesForAlertItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              AlertStatusAlertStatusesForAlertItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new AlertStatusesForAlertResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AlertStatusesForAlertResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into AlertStatusesForAlertResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static AlertStatusesForAlertResponse fromJson(
      String json,
      AlertStatusService service,
      AlertStatusesForAlertParams originalParams,
      String alertId,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<AlertStatusAlertStatusesForAlertItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              AlertStatusAlertStatusesForAlertItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new AlertStatusesForAlertResponse(
          list, nextOffset, alertId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AlertStatusesForAlertResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<AlertStatusAlertStatusesForAlertItem> getList() {
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
  public AlertStatusesForAlertResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    AlertStatusesForAlertParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : AlertStatusesForAlertParams.builder())
            .offset(nextOffset)
            .build();

    return service.alertStatusesForAlert(alertId, nextParams);
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
    return "AlertStatusesForAlertResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AlertStatusesForAlertResponse that = (AlertStatusesForAlertResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class AlertStatusAlertStatusesForAlertItem {

    private AlertStatus alertStatus;

    public AlertStatus getAlertStatus() {
      return alertStatus;
    }

    public static AlertStatusAlertStatusesForAlertItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static AlertStatusAlertStatusesForAlertItem fromJson(JsonObject jsonObj) {
      AlertStatusAlertStatusesForAlertItem item = new AlertStatusAlertStatusesForAlertItem();

      JsonObject __alertStatusObj = JsonUtil.getJsonObject(jsonObj, "alert_status");
      if (__alertStatusObj != null) {
        item.alertStatus = AlertStatus.fromJson(__alertStatusObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "AlertStatusAlertStatusesForAlertItem{" + "alertStatus=" + alertStatus + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      AlertStatusAlertStatusesForAlertItem that = (AlertStatusAlertStatusesForAlertItem) o;
      return java.util.Objects.equals(alertStatus, that.alertStatus);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(alertStatus);
    }
  }
}
