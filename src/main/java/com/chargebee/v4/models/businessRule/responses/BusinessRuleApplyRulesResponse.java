package com.chargebee.v4.models.businessRule.responses;

import com.chargebee.v4.models.applyRule.ApplyRule;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BusinessRuleApplyRules operation. Contains the response data from
 * the API.
 */
public final class BusinessRuleApplyRulesResponse extends BaseResponse {
  private final ApplyRule applyRule;

  private BusinessRuleApplyRulesResponse(Builder builder) {
    super(builder.httpResponse);

    this.applyRule = builder.applyRule;
  }

  /** Parse JSON response into BusinessRuleApplyRulesResponse object. */
  public static BusinessRuleApplyRulesResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BusinessRuleApplyRulesResponse object with HTTP response. */
  public static BusinessRuleApplyRulesResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __applyRuleObj = JsonUtil.getJsonObject(jsonObj, "apply_rule");
      if (__applyRuleObj != null) {
        builder.applyRule(ApplyRule.fromJson(__applyRuleObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessRuleApplyRulesResponse from JSON", e);
    }
  }

  /** Create a new builder for BusinessRuleApplyRulesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BusinessRuleApplyRulesResponse. */
  public static class Builder {

    private ApplyRule applyRule;

    private Response httpResponse;

    private Builder() {}

    public Builder applyRule(ApplyRule applyRule) {
      this.applyRule = applyRule;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public BusinessRuleApplyRulesResponse build() {
      return new BusinessRuleApplyRulesResponse(this);
    }
  }

  /** Get the applyRule from the response. */
  public ApplyRule getApplyRule() {
    return applyRule;
  }

  @Override
  public String toString() {
    return "BusinessRuleApplyRulesResponse{" + "applyRule=" + applyRule + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BusinessRuleApplyRulesResponse that = (BusinessRuleApplyRulesResponse) o;
    return java.util.Objects.equals(applyRule, that.applyRule);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(applyRule);
  }
}
