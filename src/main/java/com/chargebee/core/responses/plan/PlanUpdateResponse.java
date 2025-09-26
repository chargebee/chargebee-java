package com.chargebee.core.responses.plan;

import com.chargebee.core.models.plan.Plan;

import com.chargebee.internal.JsonUtil;

/** Immutable response object for PlanUpdate operation. Contains the response data from the API. */
public final class PlanUpdateResponse {

  private final Plan plan;

  private PlanUpdateResponse(Builder builder) {

    this.plan = builder.plan;
  }

  /** Parse JSON response into PlanUpdateResponse object. */
  public static PlanUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __planJson = JsonUtil.getObject(json, "plan");
      if (__planJson != null) {
        builder.plan(Plan.fromJson(__planJson));
      }

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

    private Builder() {}

    public Builder plan(Plan plan) {
      this.plan = plan;
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
}
