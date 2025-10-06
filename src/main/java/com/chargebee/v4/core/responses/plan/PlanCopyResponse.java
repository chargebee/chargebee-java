package com.chargebee.v4.core.responses.plan;

import com.chargebee.v4.core.models.plan.Plan;

import com.chargebee.v4.internal.JsonUtil;

/** Immutable response object for PlanCopy operation. Contains the response data from the API. */
public final class PlanCopyResponse {

  private final Plan plan;

  private PlanCopyResponse(Builder builder) {

    this.plan = builder.plan;
  }

  /** Parse JSON response into PlanCopyResponse object. */
  public static PlanCopyResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __planJson = JsonUtil.getObject(json, "plan");
      if (__planJson != null) {
        builder.plan(Plan.fromJson(__planJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PlanCopyResponse from JSON", e);
    }
  }

  /** Create a new builder for PlanCopyResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PlanCopyResponse. */
  public static class Builder {

    private Plan plan;

    private Builder() {}

    public Builder plan(Plan plan) {
      this.plan = plan;
      return this;
    }

    public PlanCopyResponse build() {
      return new PlanCopyResponse(this);
    }
  }

  /** Get the plan from the response. */
  public Plan getPlan() {
    return plan;
  }
}
