package com.chargebee.core.responses.recordedPurchase;

import com.chargebee.core.models.recordedPurchase.RecordedPurchase;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for RecordedPurchaseRetrieve operation. Contains the response data from
 * a single resource get operation.
 */
public final class RecordedPurchaseRetrieveResponse {

  private final RecordedPurchase recordedPurchase;

  private RecordedPurchaseRetrieveResponse(RecordedPurchase recordedPurchase) {

    this.recordedPurchase = recordedPurchase;
  }

  /** Parse JSON response into RecordedPurchaseRetrieveResponse object. */
  public static RecordedPurchaseRetrieveResponse fromJson(String json) {
    try {

      RecordedPurchase recordedPurchase =
          RecordedPurchase.fromJson(JsonUtil.getObject(json, "recorded_purchase"));

      return new RecordedPurchaseRetrieveResponse(recordedPurchase);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RecordedPurchaseRetrieveResponse from JSON", e);
    }
  }

  /** Get the recordedPurchase from the response. */
  public RecordedPurchase getRecordedPurchase() {
    return recordedPurchase;
  }
}
