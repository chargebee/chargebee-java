package com.chargebee.v4.models.plan.responses;

import com.chargebee.v4.models.plan.Plan;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for PlanCreate operation. Contains the response data from the API. */
public final class PlanCreateResponse extends BaseResponse {
  private final Plan plan;

  private PlanCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.plan = builder.plan;
  }

  /** Parse JSON response into PlanCreateResponse object. */
  public static PlanCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PlanCreateResponse object with HTTP response. */
  public static PlanCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __planJson = JsonUtil.getObject(json, "plan");
      if (__planJson != null) {
        builder.plan(Plan.fromJson(__planJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PlanCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for PlanCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PlanCreateResponse. */
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

    public PlanCreateResponse build() {
      return new PlanCreateResponse(this);
    }
  }

  /** Get the plan from the response. */
  public Plan getPlan() {
    return plan;
  }
}
