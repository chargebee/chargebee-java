package com.chargebee.v4.models.invoice.responses;

import java.util.List;

import com.chargebee.v4.models.paymentReferenceNumber.PaymentReferenceNumber;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.InvoiceService;
import com.chargebee.v4.models.invoice.params.InvoiceListPaymentReferenceNumbersParams;

/**
 * Immutable response object for InvoiceListPaymentReferenceNumbers operation. Contains paginated
 * list data.
 */
public final class InvoiceListPaymentReferenceNumbersResponse {

  private final List<InvoiceListPaymentReferenceNumbersItem> list;

  private final String nextOffset;

  private final InvoiceService service;
  private final InvoiceListPaymentReferenceNumbersParams originalParams;
  private final Response httpResponse;

  private InvoiceListPaymentReferenceNumbersResponse(
      List<InvoiceListPaymentReferenceNumbersItem> list,
      String nextOffset,
      InvoiceService service,
      InvoiceListPaymentReferenceNumbersParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into InvoiceListPaymentReferenceNumbersResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static InvoiceListPaymentReferenceNumbersResponse fromJson(String json) {
    try {

      List<InvoiceListPaymentReferenceNumbersItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(InvoiceListPaymentReferenceNumbersItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new InvoiceListPaymentReferenceNumbersResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse InvoiceListPaymentReferenceNumbersResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into InvoiceListPaymentReferenceNumbersResponse object with service context
   * for pagination (enables nextPage()).
   */
  public static InvoiceListPaymentReferenceNumbersResponse fromJson(
      String json,
      InvoiceService service,
      InvoiceListPaymentReferenceNumbersParams originalParams,
      Response httpResponse) {
    try {

      List<InvoiceListPaymentReferenceNumbersItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(InvoiceListPaymentReferenceNumbersItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new InvoiceListPaymentReferenceNumbersResponse(
          list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse InvoiceListPaymentReferenceNumbersResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<InvoiceListPaymentReferenceNumbersItem> getList() {
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
  public InvoiceListPaymentReferenceNumbersResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    InvoiceListPaymentReferenceNumbersParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : InvoiceListPaymentReferenceNumbersParams.builder())
            .offset(nextOffset)
            .build();

    return service.listPaymentReferenceNumbers(nextParams);
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

  public static class InvoiceListPaymentReferenceNumbersItem {

    private PaymentReferenceNumber paymentReferenceNumber;

    public PaymentReferenceNumber getPaymentReferenceNumber() {
      return paymentReferenceNumber;
    }

    public static InvoiceListPaymentReferenceNumbersItem fromJson(String json) {
      InvoiceListPaymentReferenceNumbersItem item = new InvoiceListPaymentReferenceNumbersItem();

      String __paymentReferenceNumberJson = JsonUtil.getObject(json, "payment_reference_number");
      if (__paymentReferenceNumberJson != null) {
        item.paymentReferenceNumber = PaymentReferenceNumber.fromJson(__paymentReferenceNumberJson);
      }

      return item;
    }
  }
}
