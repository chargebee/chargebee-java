package com.chargebee.core.responses.plan;

import com.chargebee.core.models.plan.Plan;

import com.chargebee.internal.JsonUtil;

/** Immutable response object for PlanCreate operation. Contains the response data from the API. */
public final class PlanCreateResponse {

  private final Plan plan;

  private PlanCreateResponse(Builder builder) {

    this.plan = builder.plan;
  }

  /** Parse JSON response into PlanCreateResponse object. */
  public static PlanCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __planJson = JsonUtil.getObject(json, "plan");
      if (__planJson != null) {
        builder.plan(Plan.fromJson(__planJson));
      }

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

    private Builder() {}

    public Builder plan(Plan plan) {
      this.plan = plan;
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
