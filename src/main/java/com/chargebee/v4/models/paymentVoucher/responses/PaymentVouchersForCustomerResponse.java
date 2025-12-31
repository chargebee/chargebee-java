package com.chargebee.v4.models.paymentVoucher.responses;

import java.util.List;

import com.chargebee.v4.models.paymentVoucher.PaymentVoucher;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.PaymentVoucherService;
import com.chargebee.v4.models.paymentVoucher.params.PaymentVouchersForCustomerParams;

/**
 * Immutable response object for PaymentVouchersForCustomer operation. Contains paginated list data.
 */
public final class PaymentVouchersForCustomerResponse {

  private final List<PaymentVoucherPaymentVouchersForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final PaymentVoucherService service;
  private final PaymentVouchersForCustomerParams originalParams;
  private final Response httpResponse;

  private PaymentVouchersForCustomerResponse(
      List<PaymentVoucherPaymentVouchersForCustomerItem> list,
      String nextOffset,
      String customerId,
      PaymentVoucherService service,
      PaymentVouchersForCustomerParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into PaymentVouchersForCustomerResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static PaymentVouchersForCustomerResponse fromJson(String json) {
    try {

      List<PaymentVoucherPaymentVouchersForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PaymentVoucherPaymentVouchersForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PaymentVouchersForCustomerResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentVouchersForCustomerResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into PaymentVouchersForCustomerResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static PaymentVouchersForCustomerResponse fromJson(
      String json,
      PaymentVoucherService service,
      PaymentVouchersForCustomerParams originalParams,
      String customerId,
      Response httpResponse) {
    try {

      List<PaymentVoucherPaymentVouchersForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PaymentVoucherPaymentVouchersForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PaymentVouchersForCustomerResponse(
          list, nextOffset, customerId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentVouchersForCustomerResponse from JSON", e);
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

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public PaymentVouchersForCustomerResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    PaymentVouchersForCustomerParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : PaymentVouchersForCustomerParams.builder())
            .offset(nextOffset)
            .build();

    return service.paymentVouchersForCustomer(customerId, nextParams);
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
    return "PaymentVouchersForCustomerResponse{"
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
    PaymentVouchersForCustomerResponse that = (PaymentVouchersForCustomerResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(list, nextOffset);
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

    @Override
    public String toString() {
      return "PaymentVoucherPaymentVouchersForCustomerItem{"
          + "paymentVoucher="
          + paymentVoucher
          + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      PaymentVoucherPaymentVouchersForCustomerItem that =
          (PaymentVoucherPaymentVouchersForCustomerItem) o;
      return java.util.Objects.equals(paymentVoucher, that.paymentVoucher);
    }

    @Override
    public int hashCode() {
      return java.util.Objects.hash(paymentVoucher);
    }
  }
}
