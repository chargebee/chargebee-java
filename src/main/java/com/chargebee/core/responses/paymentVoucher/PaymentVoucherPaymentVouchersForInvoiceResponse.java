package com.chargebee.core.responses.paymentVoucher;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.paymentVoucher.PaymentVoucher;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.PaymentVoucherService;
import com.chargebee.core.models.paymentVoucher.params.PaymentVoucherPaymentVouchersForInvoiceParams;

/**
 * Immutable response object for PaymentVoucherPaymentVouchersForInvoice operation. Contains
 * paginated list data with auto-pagination support.
 */
public final class PaymentVoucherPaymentVouchersForInvoiceResponse
    implements Iterable<
        PaymentVoucherPaymentVouchersForInvoiceResponse
            .PaymentVoucherPaymentVouchersForInvoiceItem> {

  private final List<PaymentVoucherPaymentVouchersForInvoiceItem> list;

  private final String nextOffset;

  private final String invoiceId;

  private final PaymentVoucherService service;
  private final PaymentVoucherPaymentVouchersForInvoiceParams originalParams;
  private final boolean isAutoPaginate;

  private PaymentVoucherPaymentVouchersForInvoiceResponse(
      List<PaymentVoucherPaymentVouchersForInvoiceItem> list,
      String nextOffset,
      String invoiceId,
      PaymentVoucherService service,
      PaymentVoucherPaymentVouchersForInvoiceParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.invoiceId = invoiceId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private PaymentVoucherPaymentVouchersForInvoiceResponse(
      List<PaymentVoucherPaymentVouchersForInvoiceItem> list,
      String nextOffset,
      String invoiceId,
      PaymentVoucherService service,
      PaymentVoucherPaymentVouchersForInvoiceParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.invoiceId = invoiceId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into PaymentVoucherPaymentVouchersForInvoiceResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static PaymentVoucherPaymentVouchersForInvoiceResponse fromJson(String json) {
    try {

      List<PaymentVoucherPaymentVouchersForInvoiceItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PaymentVoucherPaymentVouchersForInvoiceItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PaymentVoucherPaymentVouchersForInvoiceResponse(
          list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PaymentVoucherPaymentVouchersForInvoiceResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into PaymentVoucherPaymentVouchersForInvoiceResponse object with service
   * context for pagination (enables nextPage(), autoPaginate()).
   */
  public static PaymentVoucherPaymentVouchersForInvoiceResponse fromJson(
      String json,
      PaymentVoucherService service,
      PaymentVoucherPaymentVouchersForInvoiceParams originalParams,
      String invoiceId) {
    try {

      List<PaymentVoucherPaymentVouchersForInvoiceItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PaymentVoucherPaymentVouchersForInvoiceItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PaymentVoucherPaymentVouchersForInvoiceResponse(
          list, nextOffset, invoiceId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PaymentVoucherPaymentVouchersForInvoiceResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<PaymentVoucherPaymentVouchersForInvoiceItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<PaymentVoucherPaymentVouchersForInvoiceItem> items() {
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
  public PaymentVoucherPaymentVouchersForInvoiceResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    PaymentVoucherPaymentVouchersForInvoiceParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.payment_vouchersForInvoice(invoiceId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public PaymentVoucherPaymentVouchersForInvoiceResponse autoPaginate() {
    return new PaymentVoucherPaymentVouchersForInvoiceResponse(
        list, nextOffset, invoiceId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<PaymentVoucherPaymentVouchersForInvoiceItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator
      implements Iterator<PaymentVoucherPaymentVouchersForInvoiceItem> {
    private PaymentVoucherPaymentVouchersForInvoiceResponse currentPage =
        PaymentVoucherPaymentVouchersForInvoiceResponse.this;
    private Iterator<PaymentVoucherPaymentVouchersForInvoiceItem> currentIterator =
        currentPage.list.iterator();

    @Override
    public boolean hasNext() {
      if (currentIterator.hasNext()) {
        return true;
      }

      // Try to load next page if available
      if (currentPage.hasNextPage()) {
        try {
          currentPage = currentPage.nextPage();
          currentIterator = currentPage.list.iterator();
          return currentIterator.hasNext();
        } catch (Exception e) {
          throw new RuntimeException("Failed to fetch next page", e);
        }
      }

      return false;
    }

    @Override
    public PaymentVoucherPaymentVouchersForInvoiceItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class PaymentVoucherPaymentVouchersForInvoiceItem {

    private PaymentVoucher paymentVoucher;

    public PaymentVoucher getPaymentVoucher() {
      return paymentVoucher;
    }

    public static PaymentVoucherPaymentVouchersForInvoiceItem fromJson(String json) {
      PaymentVoucherPaymentVouchersForInvoiceItem item =
          new PaymentVoucherPaymentVouchersForInvoiceItem();

      String __paymentVoucherJson = JsonUtil.getObject(json, "payment_voucher");
      if (__paymentVoucherJson != null) {
        item.paymentVoucher = PaymentVoucher.fromJson(__paymentVoucherJson);
      }

      return item;
    }
  }
}
