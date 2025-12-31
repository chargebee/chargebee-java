package com.chargebee.v4.models.plan.responses;

import com.chargebee.v4.models.plan.Plan;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for PlanCopy operation. Contains the response data from the API. */
public final class PlanCopyResponse extends BaseResponse {
  private final Plan plan;

  private PlanCopyResponse(Builder builder) {
    super(builder.httpResponse);

    this.plan = builder.plan;
  }

  /** Parse JSON response into PlanCopyResponse object. */
  public static PlanCopyResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PlanCopyResponse object with HTTP response. */
  public static PlanCopyResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __planJson = JsonUtil.getObject(json, "plan");
      if (__planJson != null) {
        builder.plan(Plan.fromJson(__planJson));
      }

      builder.httpResponse(httpResponse);
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

    public PlanCopyResponse build() {
      return new PlanCopyResponse(this);
    }
  }

  /** Get the plan from the response. */
  public Plan getPlan() {
    return plan;
  }

  @Override
  public String toString() {
    return "PlanCopyResponse{" + "plan=" + plan + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PlanCopyResponse that = (PlanCopyResponse) o;
    return java.util.Objects.equals(plan, that.plan);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(plan);
  }
}
