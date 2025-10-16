package com.chargebee.v4.core.responses.paymentSource;

import com.chargebee.v4.core.models.paymentSource.PaymentSource;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentSourceRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PaymentSourceRetrieveResponse extends BaseResponse {
  private final PaymentSource paymentSource;

  private PaymentSourceRetrieveResponse(PaymentSource paymentSource, Response httpResponse) {
    super(httpResponse);

    this.paymentSource = paymentSource;
  }

  /** Parse JSON response into PaymentSourceRetrieveResponse object. */
  public static PaymentSourceRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentSourceRetrieveResponse object with HTTP response. */
  public static PaymentSourceRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      PaymentSource paymentSource =
          PaymentSource.fromJson(JsonUtil.getObject(json, "payment_source"));

      return new PaymentSourceRetrieveResponse(paymentSource, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentSourceRetrieveResponse from JSON", e);
    }
  }

  /** Get the paymentSource from the response. */
  public PaymentSource getPaymentSource() {
    return paymentSource;
  }
}
