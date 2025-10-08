package com.chargebee.v4.core.responses.paymentVoucher;

import com.chargebee.v4.core.models.paymentVoucher.PaymentVoucher;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentVoucherRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PaymentVoucherRetrieveResponse {

  private final PaymentVoucher paymentVoucher;

  private final Response httpResponse;

  private PaymentVoucherRetrieveResponse(PaymentVoucher paymentVoucher, Response httpResponse) {

    this.paymentVoucher = paymentVoucher;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into PaymentVoucherRetrieveResponse object. */
  public static PaymentVoucherRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentVoucherRetrieveResponse object with HTTP response. */
  public static PaymentVoucherRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      PaymentVoucher paymentVoucher =
          PaymentVoucher.fromJson(JsonUtil.getObject(json, "payment_voucher"));

      return new PaymentVoucherRetrieveResponse(paymentVoucher, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentVoucherRetrieveResponse from JSON", e);
    }
  }

  /** Get the paymentVoucher from the response. */
  public PaymentVoucher getPaymentVoucher() {
    return paymentVoucher;
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
}
