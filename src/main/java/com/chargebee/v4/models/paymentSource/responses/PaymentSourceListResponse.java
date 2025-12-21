package com.chargebee.v4.models.paymentSource.responses;

import java.util.List;

import com.chargebee.v4.models.paymentSource.PaymentSource;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.PaymentSourceService;
import com.chargebee.v4.models.paymentSource.params.PaymentSourceListParams;

/** Immutable response object for PaymentSourceList operation. Contains paginated list data. */
public final class PaymentSourceListResponse {

  private final List<PaymentSourceListItem> list;

  private final String nextOffset;

  private final PaymentSourceService service;
  private final PaymentSourceListParams originalParams;
  private final Response httpResponse;

  private PaymentSourceListResponse(
      List<PaymentSourceListItem> list,
      String nextOffset,
      PaymentSourceService service,
      PaymentSourceListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into PaymentSourceListResponse object (no service context). Use this when
   * you only need to read a single page (no nextPage()).
   */
  public static PaymentSourceListResponse fromJson(String json) {
    try {

      List<PaymentSourceListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PaymentSourceListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PaymentSourceListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentSourceListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into PaymentSourceListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static PaymentSourceListResponse fromJson(
      String json,
      PaymentSourceService service,
      PaymentSourceListParams originalParams,
      Response httpResponse) {
    try {

      List<PaymentSourceListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PaymentSourceListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PaymentSourceListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentSourceListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<PaymentSourceListItem> getList() {
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
  public PaymentSourceListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    PaymentSourceListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : PaymentSourceListParams.builder())
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

  public static class PaymentSourceListItem {

    private PaymentSource paymentSource;

    public PaymentSource getPaymentSource() {
      return paymentSource;
    }

    public static PaymentSourceListItem fromJson(String json) {
      PaymentSourceListItem item = new PaymentSourceListItem();

      String __paymentSourceJson = JsonUtil.getObject(json, "payment_source");
      if (__paymentSourceJson != null) {
        item.paymentSource = PaymentSource.fromJson(__paymentSourceJson);
      }

      return item;
    }
  }
}
