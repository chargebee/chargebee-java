package com.chargebee.v4.core.responses.plan;

import com.chargebee.v4.core.models.plan.Plan;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PlanRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class PlanRetrieveResponse extends BaseResponse {
  private final Plan plan;

  private PlanRetrieveResponse(Plan plan, Response httpResponse) {
    super(httpResponse);

    this.plan = plan;
  }

  /** Parse JSON response into PlanRetrieveResponse object. */
  public static PlanRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PlanRetrieveResponse object with HTTP response. */
  public static PlanRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Plan plan = Plan.fromJson(JsonUtil.getObject(json, "plan"));

      return new PlanRetrieveResponse(plan, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PlanRetrieveResponse from JSON", e);
    }
  }

  /** Get the plan from the response. */
  public Plan getPlan() {
    return plan;
  }
}
