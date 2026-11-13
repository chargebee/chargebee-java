package com.chargebee.v4.models.paymentScheduleScheme.responses;

import java.util.List;

import com.chargebee.v4.models.paymentScheduleScheme.PaymentScheduleScheme;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.PaymentScheduleSchemeService;
import com.chargebee.v4.models.paymentScheduleScheme.params.PaymentScheduleSchemeListParams;

/**
 * Immutable response object for PaymentScheduleSchemeList operation. Contains paginated list data.
 */
public final class PaymentScheduleSchemeListResponse {

  private final List<PaymentScheduleSchemeListItem> list;

  private final String nextOffset;

  private final PaymentScheduleSchemeService service;
  private final PaymentScheduleSchemeListParams originalParams;
  private final Response httpResponse;

  private PaymentScheduleSchemeListResponse(
      List<PaymentScheduleSchemeListItem> list,
      String nextOffset,
      PaymentScheduleSchemeService service,
      PaymentScheduleSchemeListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into PaymentScheduleSchemeListResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static PaymentScheduleSchemeListResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<PaymentScheduleSchemeListItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), PaymentScheduleSchemeListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new PaymentScheduleSchemeListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentScheduleSchemeListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into PaymentScheduleSchemeListResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static PaymentScheduleSchemeListResponse fromJson(
      String json,
      PaymentScheduleSchemeService service,
      PaymentScheduleSchemeListParams originalParams,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<PaymentScheduleSchemeListItem> list =
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "list"), PaymentScheduleSchemeListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new PaymentScheduleSchemeListResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentScheduleSchemeListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<PaymentScheduleSchemeListItem> getList() {
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
  public PaymentScheduleSchemeListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    PaymentScheduleSchemeListParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : PaymentScheduleSchemeListParams.builder())
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
    return "PaymentScheduleSchemeListResponse{"
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

    PaymentScheduleSchemeListResponse that = (PaymentScheduleSchemeListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class PaymentScheduleSchemeListItem {

    private PaymentScheduleScheme paymentScheduleScheme;

    public PaymentScheduleScheme getPaymentScheduleScheme() {
      return paymentScheduleScheme;
    }

    public static PaymentScheduleSchemeListItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static PaymentScheduleSchemeListItem fromJson(JsonObject jsonObj) {
      PaymentScheduleSchemeListItem item = new PaymentScheduleSchemeListItem();

      JsonObject __paymentScheduleSchemeObj =
          JsonUtil.getJsonObject(jsonObj, "payment_schedule_scheme");
      if (__paymentScheduleSchemeObj != null) {
        item.paymentScheduleScheme = PaymentScheduleScheme.fromJson(__paymentScheduleSchemeObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "PaymentScheduleSchemeListItem{"
          + "paymentScheduleScheme="
          + paymentScheduleScheme
          + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      PaymentScheduleSchemeListItem that = (PaymentScheduleSchemeListItem) o;
      return java.util.Objects.equals(paymentScheduleScheme, that.paymentScheduleScheme);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(paymentScheduleScheme);
    }
  }
}
