package com.chargebee.v4.models.draftRequest.responses;

import com.chargebee.v4.models.draftEstimate.DraftEstimate;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DraftRequestEstimate operation. Contains the response data from the
 * API.
 */
public final class DraftRequestEstimateResponse extends BaseResponse {
  private final DraftEstimate draftEstimate;

  private DraftRequestEstimateResponse(Builder builder) {
    super(builder.httpResponse);

    this.draftEstimate = builder.draftEstimate;
  }

  /** Parse JSON response into DraftRequestEstimateResponse object. */
  public static DraftRequestEstimateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DraftRequestEstimateResponse object with HTTP response. */
  public static DraftRequestEstimateResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __draftEstimateObj = JsonUtil.getJsonObject(jsonObj, "draft_estimate");
      if (__draftEstimateObj != null) {
        builder.draftEstimate(DraftEstimate.fromJson(__draftEstimateObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DraftRequestEstimateResponse from JSON", e);
    }
  }

  /** Create a new builder for DraftRequestEstimateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DraftRequestEstimateResponse. */
  public static class Builder {

    private DraftEstimate draftEstimate;

    private Response httpResponse;

    private Builder() {}

    public Builder draftEstimate(DraftEstimate draftEstimate) {
      this.draftEstimate = draftEstimate;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public DraftRequestEstimateResponse build() {
      return new DraftRequestEstimateResponse(this);
    }
  }

  /** Get the draftEstimate from the response. */
  public DraftEstimate getDraftEstimate() {
    return draftEstimate;
  }

  @Override
  public String toString() {
    return "DraftRequestEstimateResponse{" + "draftEstimate=" + draftEstimate + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DraftRequestEstimateResponse that = (DraftRequestEstimateResponse) o;
    return java.util.Objects.equals(draftEstimate, that.draftEstimate);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(draftEstimate);
  }
}
