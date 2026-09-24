package com.chargebee.v4.models.businessRule.responses;

import com.chargebee.v4.models.businessRule.BusinessRule;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BusinessRuleUpdateDraft operation. Contains the response data from
 * the API.
 */
public final class BusinessRuleUpdateDraftResponse extends BaseResponse {
  private final BusinessRule businessRule;

  private BusinessRuleUpdateDraftResponse(Builder builder) {
    super(builder.httpResponse);

    this.businessRule = builder.businessRule;
  }

  /** Parse JSON response into BusinessRuleUpdateDraftResponse object. */
  public static BusinessRuleUpdateDraftResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BusinessRuleUpdateDraftResponse object with HTTP response. */
  public static BusinessRuleUpdateDraftResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse BusinessRuleUpdateDraftResponse from JSON", e);
    }
  }

  /** Create a new builder for BusinessRuleUpdateDraftResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BusinessRuleUpdateDraftResponse. */
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

    public BusinessRuleUpdateDraftResponse build() {
      return new BusinessRuleUpdateDraftResponse(this);
    }
  }

  /** Get the businessRule from the response. */
  public BusinessRule getBusinessRule() {
    return businessRule;
  }

  @Override
  public String toString() {
    return "BusinessRuleUpdateDraftResponse{" + "businessRule=" + businessRule + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BusinessRuleUpdateDraftResponse that = (BusinessRuleUpdateDraftResponse) o;
    return java.util.Objects.equals(businessRule, that.businessRule);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(businessRule);
  }
}
