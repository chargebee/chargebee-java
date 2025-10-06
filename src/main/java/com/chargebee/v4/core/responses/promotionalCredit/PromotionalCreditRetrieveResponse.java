package com.chargebee.v4.core.responses.promotionalCredit;

import com.chargebee.v4.core.models.promotionalCredit.PromotionalCredit;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for PromotionalCreditRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class PromotionalCreditRetrieveResponse {

  private final PromotionalCredit promotionalCredit;

  private PromotionalCreditRetrieveResponse(PromotionalCredit promotionalCredit) {

    this.promotionalCredit = promotionalCredit;
  }

  /** Parse JSON response into PromotionalCreditRetrieveResponse object. */
  public static PromotionalCreditRetrieveResponse fromJson(String json) {
    try {

      PromotionalCredit promotionalCredit =
          PromotionalCredit.fromJson(JsonUtil.getObject(json, "promotional_credit"));

      return new PromotionalCreditRetrieveResponse(promotionalCredit);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PromotionalCreditRetrieveResponse from JSON", e);
    }
  }

  /** Get the promotionalCredit from the response. */
  public PromotionalCredit getPromotionalCredit() {
    return promotionalCredit;
  }
}
