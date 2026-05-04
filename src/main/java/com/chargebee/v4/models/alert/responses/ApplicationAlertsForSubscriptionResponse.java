package com.chargebee.v4.models.alert.responses;

import java.util.List;

import com.chargebee.v4.models.alert.Alert;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.AlertService;
import com.chargebee.v4.models.alert.params.ApplicationAlertsForSubscriptionParams;

/**
 * Immutable response object for ApplicationAlertsForSubscription operation. Contains paginated list
 * data.
 */
public final class ApplicationAlertsForSubscriptionResponse {

  private final List<AlertApplicationAlertsForSubscriptionItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final AlertService service;
  private final ApplicationAlertsForSubscriptionParams originalParams;
  private final Response httpResponse;

  private ApplicationAlertsForSubscriptionResponse(
      List<AlertApplicationAlertsForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      AlertService service,
      ApplicationAlertsForSubscriptionParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ApplicationAlertsForSubscriptionResponse object (no service context).
   * Use this when you only need to read a single page (no nextPage()).
   */
  public static ApplicationAlertsForSubscriptionResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<AlertApplicationAlertsForSubscriptionItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              AlertApplicationAlertsForSubscriptionItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ApplicationAlertsForSubscriptionResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ApplicationAlertsForSubscriptionResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ApplicationAlertsForSubscriptionResponse object with service context
   * for pagination (enables nextPage()).
   */
  public static ApplicationAlertsForSubscriptionResponse fromJson(
      String json,
      AlertService service,
      ApplicationAlertsForSubscriptionParams originalParams,
      String subscriptionId,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<AlertApplicationAlertsForSubscriptionItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"),
              AlertApplicationAlertsForSubscriptionItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ApplicationAlertsForSubscriptionResponse(
          list, nextOffset, subscriptionId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ApplicationAlertsForSubscriptionResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<AlertApplicationAlertsForSubscriptionItem> getList() {
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
  public ApplicationAlertsForSubscriptionResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ApplicationAlertsForSubscriptionParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : ApplicationAlertsForSubscriptionParams.builder())
            .offset(nextOffset)
            .build();

    return service.applicationAlertsForSubscription(subscriptionId, nextParams);
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
    return "ApplicationAlertsForSubscriptionResponse{"
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

    ApplicationAlertsForSubscriptionResponse that = (ApplicationAlertsForSubscriptionResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class AlertApplicationAlertsForSubscriptionItem {

    private Alert alert;

    public Alert getAlert() {
      return alert;
    }

    public static AlertApplicationAlertsForSubscriptionItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static AlertApplicationAlertsForSubscriptionItem fromJson(JsonObject jsonObj) {
      AlertApplicationAlertsForSubscriptionItem item =
          new AlertApplicationAlertsForSubscriptionItem();

      JsonObject __alertObj = JsonUtil.getJsonObject(jsonObj, "alert");
      if (__alertObj != null) {
        item.alert = Alert.fromJson(__alertObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "AlertApplicationAlertsForSubscriptionItem{" + "alert=" + alert + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      AlertApplicationAlertsForSubscriptionItem that =
          (AlertApplicationAlertsForSubscriptionItem) o;
      return java.util.Objects.equals(alert, that.alert);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(alert);
    }
  }
}
