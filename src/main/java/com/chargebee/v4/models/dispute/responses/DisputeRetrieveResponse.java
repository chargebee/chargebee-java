package com.chargebee.v4.models.dispute.responses;

import com.chargebee.v4.models.dispute.Dispute;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DisputeRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class DisputeRetrieveResponse extends BaseResponse {
  private final Dispute dispute;

  private DisputeRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.dispute = builder.dispute;
  }

  /** Parse JSON response into DisputeRetrieveResponse object. */
  public static DisputeRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DisputeRetrieveResponse object with HTTP response. */
  public static DisputeRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __disputeObj = JsonUtil.getJsonObject(jsonObj, "dispute");
      if (__disputeObj != null) {
        builder.dispute(Dispute.fromJson(__disputeObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DisputeRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for DisputeRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DisputeRetrieveResponse. */
  public static class Builder {

    private Dispute dispute;

    private Response httpResponse;

    private Builder() {}

    public Builder dispute(Dispute dispute) {
      this.dispute = dispute;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public DisputeRetrieveResponse build() {
      return new DisputeRetrieveResponse(this);
    }
  }

  /** Get the dispute from the response. */
  public Dispute getDispute() {
    return dispute;
  }

  @Override
  public String toString() {
    return "DisputeRetrieveResponse{" + "dispute=" + dispute + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DisputeRetrieveResponse that = (DisputeRetrieveResponse) o;
    return java.util.Objects.equals(dispute, that.dispute);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(dispute);
  }
}
