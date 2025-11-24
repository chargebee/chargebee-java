package com.chargebee.v4.models.recordedPurchase.responses;

import com.chargebee.v4.models.recordedPurchase.RecordedPurchase;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RecordedPurchaseRetrieve operation. Contains the response data from
 * a single resource get operation.
 */
public final class RecordedPurchaseRetrieveResponse extends BaseResponse {
  private final RecordedPurchase recordedPurchase;

  private RecordedPurchaseRetrieveResponse(
      RecordedPurchase recordedPurchase, Response httpResponse) {
    super(httpResponse);

    this.recordedPurchase = recordedPurchase;
  }

  /** Parse JSON response into RecordedPurchaseRetrieveResponse object. */
  public static RecordedPurchaseRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RecordedPurchaseRetrieveResponse object with HTTP response. */
  public static RecordedPurchaseRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      RecordedPurchase recordedPurchase =
          RecordedPurchase.fromJson(JsonUtil.getObject(json, "recorded_purchase"));

      return new RecordedPurchaseRetrieveResponse(recordedPurchase, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RecordedPurchaseRetrieveResponse from JSON", e);
    }
  }

  /** Get the recordedPurchase from the response. */
  public RecordedPurchase getRecordedPurchase() {
    return recordedPurchase;
  }
}
