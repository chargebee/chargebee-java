package com.chargebee.v4.core.responses.paymentVoucher;

import java.util.List;

import com.chargebee.v4.core.models.paymentVoucher.PaymentVoucher;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.PaymentVoucherService;
import com.chargebee.v4.core.models.paymentVoucher.params.PaymentVoucherPaymentVouchersForCustomerParams;

/**
 * Immutable response object for PaymentVoucherPaymentVouchersForCustomer operation. Contains
 * paginated list data.
 */
public final class PaymentVoucherPaymentVouchersForCustomerResponse {

  private final List<PaymentVoucherPaymentVouchersForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final PaymentVoucherService service;
  private final PaymentVoucherPaymentVouchersForCustomerParams originalParams;

  private PaymentVoucherPaymentVouchersForCustomerResponse(
      List<PaymentVoucherPaymentVouchersForCustomerItem> list,
      String nextOffset,
      String customerId,
      PaymentVoucherService service,
      PaymentVoucherPaymentVouchersForCustomerParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into PaymentVoucherPaymentVouchersForCustomerResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static PaymentVoucherPaymentVouchersForCustomerResponse fromJson(String json) {
    try {

      List<PaymentVoucherPaymentVouchersForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PaymentVoucherPaymentVouchersForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PaymentVoucherPaymentVouchersForCustomerResponse(
          list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PaymentVoucherPaymentVouchersForCustomerResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into PaymentVoucherPaymentVouchersForCustomerResponse object with service
   * context for pagination (enables nextPage()).
   */
  public static PaymentVoucherPaymentVouchersForCustomerResponse fromJson(
      String json,
      PaymentVoucherService service,
      PaymentVoucherPaymentVouchersForCustomerParams originalParams,
      String customerId) {
    try {

      List<PaymentVoucherPaymentVouchersForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PaymentVoucherPaymentVouchersForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PaymentVoucherPaymentVouchersForCustomerResponse(
          list, nextOffset, customerId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PaymentVoucherPaymentVouchersForCustomerResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<PaymentVoucherPaymentVouchersForCustomerItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<PaymentVoucherPaymentVouchersForCustomerItem> items() {
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
  public PaymentVoucherPaymentVouchersForCustomerResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    PaymentVoucherPaymentVouchersForCustomerParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.payment_vouchersForCustomer(customerId, nextParams);
  }

  public static class PaymentVoucherPaymentVouchersForCustomerItem {

    private PaymentVoucher paymentVoucher;

    public PaymentVoucher getPaymentVoucher() {
      return paymentVoucher;
    }

    public static PaymentVoucherPaymentVouchersForCustomerItem fromJson(String json) {
      PaymentVoucherPaymentVouchersForCustomerItem item =
          new PaymentVoucherPaymentVouchersForCustomerItem();

      String __paymentVoucherJson = JsonUtil.getObject(json, "payment_voucher");
      if (__paymentVoucherJson != null) {
        item.paymentVoucher = PaymentVoucher.fromJson(__paymentVoucherJson);
      }

      return item;
    }
  }
}
