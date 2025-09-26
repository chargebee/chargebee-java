package com.chargebee.core.responses.plan;

import com.chargebee.core.models.plan.Plan;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for PlanRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class PlanRetrieveResponse {

  private final Plan plan;

  private PlanRetrieveResponse(Plan plan) {

    this.plan = plan;
  }

  /** Parse JSON response into PlanRetrieveResponse object. */
  public static PlanRetrieveResponse fromJson(String json) {
    try {

      Plan plan = Plan.fromJson(JsonUtil.getObject(json, "plan"));

      return new PlanRetrieveResponse(plan);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PlanRetrieveResponse from JSON", e);
    }
  }

  /** Get the plan from the response. */
  public Plan getPlan() {
    return plan;
  }
}
