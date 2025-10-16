package com.chargebee.v4.core.responses.additionalBillingLogiq;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AdditionalBillingLogiqRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class AdditionalBillingLogiqRetrieveResponse extends BaseResponse {
  private final Object additionalBillingLogiq;

  private AdditionalBillingLogiqRetrieveResponse(
      Object additionalBillingLogiq, Response httpResponse) {
    super(httpResponse);

    this.additionalBillingLogiq = additionalBillingLogiq;
  }

  /** Parse JSON response into AdditionalBillingLogiqRetrieveResponse object. */
  public static AdditionalBillingLogiqRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AdditionalBillingLogiqRetrieveResponse object with HTTP response. */
  public static AdditionalBillingLogiqRetrieveResponse fromJson(
      String json, Response httpResponse) {
    try {

      Object additionalBillingLogiq = JsonUtil.getObject(json, "additional_billing_logiq");

      return new AdditionalBillingLogiqRetrieveResponse(additionalBillingLogiq, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse AdditionalBillingLogiqRetrieveResponse from JSON", e);
    }
  }

  /** Get the additionalBillingLogiq from the response. */
  public Object getAdditionalBillingLogiq() {
    return additionalBillingLogiq;
  }
}
