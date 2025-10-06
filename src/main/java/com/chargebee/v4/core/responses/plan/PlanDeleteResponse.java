package com.chargebee.v4.core.responses.plan;

import com.chargebee.v4.core.models.plan.Plan;

import com.chargebee.v4.internal.JsonUtil;

/** Immutable response object for PlanDelete operation. Contains the response data from the API. */
public final class PlanDeleteResponse {

  private final Plan plan;

  private PlanDeleteResponse(Builder builder) {

    this.plan = builder.plan;
  }

  /** Parse JSON response into PlanDeleteResponse object. */
  public static PlanDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __planJson = JsonUtil.getObject(json, "plan");
      if (__planJson != null) {
        builder.plan(Plan.fromJson(__planJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PlanDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for PlanDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PlanDeleteResponse. */
  public static class Builder {

    private Plan plan;

    private Builder() {}

    public Builder plan(Plan plan) {
      this.plan = plan;
      return this;
    }

    public PlanDeleteResponse build() {
      return new PlanDeleteResponse(this);
    }
  }

  /** Get the plan from the response. */
  public Plan getPlan() {
    return plan;
  }
}
