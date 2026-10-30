package com.chargebee.v4.models.businessRuleset.responses;

import com.chargebee.v4.models.businessRulesetRule.BusinessRulesetRule;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BusinessRulesetListRules operation. Contains the response data from
 * a single resource get operation.
 */
public final class BusinessRulesetListRulesResponse extends BaseResponse {
  private final BusinessRulesetRule businessRulesetRule;

  private BusinessRulesetListRulesResponse(Builder builder) {
    super(builder.httpResponse);

    this.businessRulesetRule = builder.businessRulesetRule;
  }

  /** Parse JSON response into BusinessRulesetListRulesResponse object. */
  public static BusinessRulesetListRulesResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BusinessRulesetListRulesResponse object with HTTP response. */
  public static BusinessRulesetListRulesResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __businessRulesetRuleObj =
          JsonUtil.getJsonObject(jsonObj, "business_ruleset_rule");
      if (__businessRulesetRuleObj != null) {
        builder.businessRulesetRule(BusinessRulesetRule.fromJson(__businessRulesetRuleObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessRulesetListRulesResponse from JSON", e);
    }
  }

  /** Create a new builder for BusinessRulesetListRulesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BusinessRulesetListRulesResponse. */
  public static class Builder {

    private BusinessRulesetRule businessRulesetRule;

    private Response httpResponse;

    private Builder() {}

    public Builder businessRulesetRule(BusinessRulesetRule businessRulesetRule) {
      this.businessRulesetRule = businessRulesetRule;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public BusinessRulesetListRulesResponse build() {
      return new BusinessRulesetListRulesResponse(this);
    }
  }

  /** Get the businessRulesetRule from the response. */
  public BusinessRulesetRule getBusinessRulesetRule() {
    return businessRulesetRule;
  }

  @Override
  public String toString() {
    return "BusinessRulesetListRulesResponse{" + "businessRulesetRule=" + businessRulesetRule + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BusinessRulesetListRulesResponse that = (BusinessRulesetListRulesResponse) o;
    return java.util.Objects.equals(businessRulesetRule, that.businessRulesetRule);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(businessRulesetRule);
  }
}
