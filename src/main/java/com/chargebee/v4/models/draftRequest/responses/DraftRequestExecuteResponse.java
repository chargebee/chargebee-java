package com.chargebee.v4.models.draftRequest.responses;

import com.chargebee.v4.models.draftRequest.DraftRequest;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DraftRequestExecute operation. Contains the response data from the
 * API.
 */
public final class DraftRequestExecuteResponse extends BaseResponse {
  private final DraftRequest draftRequest;

  private DraftRequestExecuteResponse(Builder builder) {
    super(builder.httpResponse);

    this.draftRequest = builder.draftRequest;
  }

  /** Parse JSON response into DraftRequestExecuteResponse object. */
  public static DraftRequestExecuteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DraftRequestExecuteResponse object with HTTP response. */
  public static DraftRequestExecuteResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __draftRequestObj = JsonUtil.getJsonObject(jsonObj, "draft_request");
      if (__draftRequestObj != null) {
        builder.draftRequest(DraftRequest.fromJson(__draftRequestObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DraftRequestExecuteResponse from JSON", e);
    }
  }

  /** Create a new builder for DraftRequestExecuteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DraftRequestExecuteResponse. */
  public static class Builder {

    private DraftRequest draftRequest;

    private Response httpResponse;

    private Builder() {}

    public Builder draftRequest(DraftRequest draftRequest) {
      this.draftRequest = draftRequest;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public DraftRequestExecuteResponse build() {
      return new DraftRequestExecuteResponse(this);
    }
  }

  /** Get the draftRequest from the response. */
  public DraftRequest getDraftRequest() {
    return draftRequest;
  }

  @Override
  public String toString() {
    return "DraftRequestExecuteResponse{" + "draftRequest=" + draftRequest + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DraftRequestExecuteResponse that = (DraftRequestExecuteResponse) o;
    return java.util.Objects.equals(draftRequest, that.draftRequest);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(draftRequest);
  }
}
