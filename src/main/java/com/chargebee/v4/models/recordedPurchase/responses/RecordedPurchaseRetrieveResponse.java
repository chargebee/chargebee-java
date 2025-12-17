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

  private RecordedPurchaseRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.recordedPurchase = builder.recordedPurchase;
  }

  /** Parse JSON response into RecordedPurchaseRetrieveResponse object. */
  public static RecordedPurchaseRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RecordedPurchaseRetrieveResponse object with HTTP response. */
  public static RecordedPurchaseRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __recordedPurchaseJson = JsonUtil.getObject(json, "recorded_purchase");
      if (__recordedPurchaseJson != null) {
        builder.recordedPurchase(RecordedPurchase.fromJson(__recordedPurchaseJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RecordedPurchaseRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for RecordedPurchaseRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for RecordedPurchaseRetrieveResponse. */
  public static class Builder {

    private RecordedPurchase recordedPurchase;

    private Response httpResponse;

    private Builder() {}

    public Builder recordedPurchase(RecordedPurchase recordedPurchase) {
      this.recordedPurchase = recordedPurchase;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public RecordedPurchaseRetrieveResponse build() {
      return new RecordedPurchaseRetrieveResponse(this);
    }
  }

  /** Get the recordedPurchase from the response. */
  public RecordedPurchase getRecordedPurchase() {
    return recordedPurchase;
  }
}
