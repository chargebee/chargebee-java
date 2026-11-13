package com.chargebee.v4.models.retrySegment.responses;

import com.chargebee.v4.models.retrySegment.RetrySegment;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RetrySegmentRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class RetrySegmentRetrieveResponse extends BaseResponse {
  private final RetrySegment retrySegment;

  private RetrySegmentRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.retrySegment = builder.retrySegment;
  }

  /** Parse JSON response into RetrySegmentRetrieveResponse object. */
  public static RetrySegmentRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RetrySegmentRetrieveResponse object with HTTP response. */
  public static RetrySegmentRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __retrySegmentObj = JsonUtil.getJsonObject(jsonObj, "retry_segment");
      if (__retrySegmentObj != null) {
        builder.retrySegment(RetrySegment.fromJson(__retrySegmentObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RetrySegmentRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for RetrySegmentRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for RetrySegmentRetrieveResponse. */
  public static class Builder {

    private RetrySegment retrySegment;

    private Response httpResponse;

    private Builder() {}

    public Builder retrySegment(RetrySegment retrySegment) {
      this.retrySegment = retrySegment;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public RetrySegmentRetrieveResponse build() {
      return new RetrySegmentRetrieveResponse(this);
    }
  }

  /** Get the retrySegment from the response. */
  public RetrySegment getRetrySegment() {
    return retrySegment;
  }

  @Override
  public String toString() {
    return "RetrySegmentRetrieveResponse{" + "retrySegment=" + retrySegment + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RetrySegmentRetrieveResponse that = (RetrySegmentRetrieveResponse) o;
    return java.util.Objects.equals(retrySegment, that.retrySegment);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(retrySegment);
  }
}
