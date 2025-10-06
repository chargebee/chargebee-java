package com.chargebee.core.responses.invoice;

import java.util.List;

import com.chargebee.core.models.paymentReferenceNumber.PaymentReferenceNumber;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.InvoiceService;
import com.chargebee.core.models.invoice.params.InvoiceListPaymentReferenceNumbersParams;

/**
 * Immutable response object for InvoiceListPaymentReferenceNumbers operation. Contains paginated
 * list data.
 */
public final class InvoiceListPaymentReferenceNumbersResponse {

  private final List<InvoiceListPaymentReferenceNumbersItem> list;

  private final String nextOffset;

  private final InvoiceService service;
  private final InvoiceListPaymentReferenceNumbersParams originalParams;

  private InvoiceListPaymentReferenceNumbersResponse(
      List<InvoiceListPaymentReferenceNumbersItem> list,
      String nextOffset,
      InvoiceService service,
      InvoiceListPaymentReferenceNumbersParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
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

      return new InvoiceListPaymentReferenceNumbersResponse(list, nextOffset, null, null);
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
      InvoiceListPaymentReferenceNumbersParams originalParams) {
    try {

      List<InvoiceListPaymentReferenceNumbersItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(InvoiceListPaymentReferenceNumbersItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new InvoiceListPaymentReferenceNumbersResponse(
          list, nextOffset, service, originalParams);
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

  /** Get the list of items in this page (alias). */
  public List<InvoiceListPaymentReferenceNumbersItem> items() {
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
  public InvoiceListPaymentReferenceNumbersResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    InvoiceListPaymentReferenceNumbersParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.listPaymentReferenceNumbers(nextParams);
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
