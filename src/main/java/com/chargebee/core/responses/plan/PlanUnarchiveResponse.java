package com.chargebee.core.responses.plan;

import com.chargebee.core.models.plan.Plan;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for PlanUnarchive operation. Contains the response data from the API.
 */
public final class PlanUnarchiveResponse {

  private final Plan plan;

  private PlanUnarchiveResponse(Builder builder) {

    this.plan = builder.plan;
  }

  /** Parse JSON response into PlanUnarchiveResponse object. */
  public static PlanUnarchiveResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __planJson = JsonUtil.getObject(json, "plan");
      if (__planJson != null) {
        builder.plan(Plan.fromJson(__planJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PlanUnarchiveResponse from JSON", e);
    }
  }

  /** Create a new builder for PlanUnarchiveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PlanUnarchiveResponse. */
  public static class Builder {

    private Plan plan;

    private Builder() {}

    public Builder plan(Plan plan) {
      this.plan = plan;
      return this;
    }

    public PlanUnarchiveResponse build() {
      return new PlanUnarchiveResponse(this);
    }
  }

  /** Get the plan from the response. */
  public Plan getPlan() {
    return plan;
  }
}
