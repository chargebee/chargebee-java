package com.chargebee.v4.models.businessRule.responses;

import com.chargebee.v4.models.businessRule.BusinessRule;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BusinessRuleCreate operation. Contains the response data from the
 * API.
 */
public final class BusinessRuleCreateResponse extends BaseResponse {
  private final BusinessRule businessRule;

  private BusinessRuleCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.businessRule = builder.businessRule;
  }

  /** Parse JSON response into BusinessRuleCreateResponse object. */
  public static BusinessRuleCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BusinessRuleCreateResponse object with HTTP response. */
  public static BusinessRuleCreateResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __businessRuleObj = JsonUtil.getJsonObject(jsonObj, "business_rule");
      if (__businessRuleObj != null) {
        builder.businessRule(BusinessRule.fromJson(__businessRuleObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessRuleCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for BusinessRuleCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BusinessRuleCreateResponse. */
  public static class Builder {

    private BusinessRule businessRule;

    private Response httpResponse;

    private Builder() {}

    public Builder businessRule(BusinessRule businessRule) {
      this.businessRule = businessRule;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public BusinessRuleCreateResponse build() {
      return new BusinessRuleCreateResponse(this);
    }
  }

  /** Get the businessRule from the response. */
  public BusinessRule getBusinessRule() {
    return businessRule;
  }

  @Override
  public String toString() {
    return "BusinessRuleCreateResponse{" + "businessRule=" + businessRule + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BusinessRuleCreateResponse that = (BusinessRuleCreateResponse) o;
    return java.util.Objects.equals(businessRule, that.businessRule);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(businessRule);
  }
}
