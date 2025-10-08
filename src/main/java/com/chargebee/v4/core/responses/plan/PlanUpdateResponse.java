package com.chargebee.v4.core.responses.plan;

import com.chargebee.v4.core.models.plan.Plan;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for PlanUpdate operation. Contains the response data from the API. */
public final class PlanUpdateResponse {

  private final Plan plan;

  private final Response httpResponse;

  private PlanUpdateResponse(Builder builder) {

    this.plan = builder.plan;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into PlanUpdateResponse object. */
  public static PlanUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PlanUpdateResponse object with HTTP response. */
  public static PlanUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __planJson = JsonUtil.getObject(json, "plan");
      if (__planJson != null) {
        builder.plan(Plan.fromJson(__planJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PlanUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for PlanUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PlanUpdateResponse. */
  public static class Builder {

    private Plan plan;

    private Response httpResponse;

    private Builder() {}

    public Builder plan(Plan plan) {
      this.plan = plan;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public PlanUpdateResponse build() {
      return new PlanUpdateResponse(this);
    }
  }

  /** Get the plan from the response. */
  public Plan getPlan() {
    return plan;
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
