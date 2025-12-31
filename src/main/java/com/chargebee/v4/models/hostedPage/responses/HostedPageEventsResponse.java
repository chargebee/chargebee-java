package com.chargebee.v4.models.hostedPage.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for HostedPageEvents operation. Contains the response data from the
 * API.
 */
public final class HostedPageEventsResponse extends BaseResponse {
  private final Boolean success;

  private HostedPageEventsResponse(Builder builder) {
    super(builder.httpResponse);

    this.success = builder.success;
  }

  /** Parse JSON response into HostedPageEventsResponse object. */
  public static HostedPageEventsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into HostedPageEventsResponse object with HTTP response. */
  public static HostedPageEventsResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.success(JsonUtil.getBoolean(json, "success"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPageEventsResponse from JSON", e);
    }
  }

  /** Create a new builder for HostedPageEventsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HostedPageEventsResponse. */
  public static class Builder {

    private Boolean success;

    private Response httpResponse;

    private Builder() {}

    public Builder success(Boolean success) {
      this.success = success;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public HostedPageEventsResponse build() {
      return new HostedPageEventsResponse(this);
    }
  }

  /** Get the success from the response. */
  public Boolean getSuccess() {
    return success;
  }

  @Override
  public String toString() {
    return "HostedPageEventsResponse{" + "success=" + success + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    HostedPageEventsResponse that = (HostedPageEventsResponse) o;
    return java.util.Objects.equals(success, that.success);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(success);
  }
}
