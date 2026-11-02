package com.chargebee.v4.models.draftRequest.responses;

import com.chargebee.v4.models.draftRequest.DraftRequest;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DraftRequestComments operation. Contains the response data from the
 * API.
 */
public final class DraftRequestCommentsResponse extends BaseResponse {
  private final DraftRequest draftRequest;

  private DraftRequestCommentsResponse(Builder builder) {
    super(builder.httpResponse);

    this.draftRequest = builder.draftRequest;
  }

  /** Parse JSON response into DraftRequestCommentsResponse object. */
  public static DraftRequestCommentsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DraftRequestCommentsResponse object with HTTP response. */
  public static DraftRequestCommentsResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse DraftRequestCommentsResponse from JSON", e);
    }
  }

  /** Create a new builder for DraftRequestCommentsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DraftRequestCommentsResponse. */
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

    public DraftRequestCommentsResponse build() {
      return new DraftRequestCommentsResponse(this);
    }
  }

  /** Get the draftRequest from the response. */
  public DraftRequest getDraftRequest() {
    return draftRequest;
  }

  @Override
  public String toString() {
    return "DraftRequestCommentsResponse{" + "draftRequest=" + draftRequest + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DraftRequestCommentsResponse that = (DraftRequestCommentsResponse) o;
    return java.util.Objects.equals(draftRequest, that.draftRequest);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(draftRequest);
  }
}
