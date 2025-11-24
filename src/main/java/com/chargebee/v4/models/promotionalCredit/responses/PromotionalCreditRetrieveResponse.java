package com.chargebee.v4.models.promotionalCredit.responses;

import com.chargebee.v4.models.promotionalCredit.PromotionalCredit;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PromotionalCreditRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class PromotionalCreditRetrieveResponse extends BaseResponse {
  private final PromotionalCredit promotionalCredit;

  private PromotionalCreditRetrieveResponse(
      PromotionalCredit promotionalCredit, Response httpResponse) {
    super(httpResponse);

    this.promotionalCredit = promotionalCredit;
  }

  /** Parse JSON response into PromotionalCreditRetrieveResponse object. */
  public static PromotionalCreditRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PromotionalCreditRetrieveResponse object with HTTP response. */
  public static PromotionalCreditRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      PromotionalCredit promotionalCredit =
          PromotionalCredit.fromJson(JsonUtil.getObject(json, "promotional_credit"));

      return new PromotionalCreditRetrieveResponse(promotionalCredit, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PromotionalCreditRetrieveResponse from JSON", e);
    }
  }

  /** Get the promotionalCredit from the response. */
  public PromotionalCredit getPromotionalCredit() {
    return promotionalCredit;
  }
}
