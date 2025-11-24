package com.chargebee.v4.models.additionalBillingLogiq.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AdditionalBillingLogiqExecute operation. Contains the response data
 * from a single resource get operation.
 */
public final class AdditionalBillingLogiqExecuteResponse extends BaseResponse {
  private final Object additionalBillingLogiq;

  private AdditionalBillingLogiqExecuteResponse(
      Object additionalBillingLogiq, Response httpResponse) {
    super(httpResponse);

    this.additionalBillingLogiq = additionalBillingLogiq;
  }

  /** Parse JSON response into AdditionalBillingLogiqExecuteResponse object. */
  public static AdditionalBillingLogiqExecuteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AdditionalBillingLogiqExecuteResponse object with HTTP response. */
  public static AdditionalBillingLogiqExecuteResponse fromJson(String json, Response httpResponse) {
    try {

      Object additionalBillingLogiq = JsonUtil.getObject(json, "additional_billing_logiq");

      return new AdditionalBillingLogiqExecuteResponse(additionalBillingLogiq, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse AdditionalBillingLogiqExecuteResponse from JSON", e);
    }
  }

  /** Get the additionalBillingLogiq from the response. */
  public Object getAdditionalBillingLogiq() {
    return additionalBillingLogiq;
  }
}
