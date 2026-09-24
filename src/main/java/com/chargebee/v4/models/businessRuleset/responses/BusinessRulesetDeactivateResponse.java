package com.chargebee.v4.models.businessRuleset.responses;

import com.chargebee.v4.models.businessRuleset.BusinessRuleset;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BusinessRulesetDeactivate operation. Contains the response data
 * from the API.
 */
public final class BusinessRulesetDeactivateResponse extends BaseResponse {
  private final BusinessRuleset businessRuleset;

  private BusinessRulesetDeactivateResponse(Builder builder) {
    super(builder.httpResponse);

    this.businessRuleset = builder.businessRuleset;
  }

  /** Parse JSON response into BusinessRulesetDeactivateResponse object. */
  public static BusinessRulesetDeactivateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BusinessRulesetDeactivateResponse object with HTTP response. */
  public static BusinessRulesetDeactivateResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __businessRulesetObj = JsonUtil.getJsonObject(jsonObj, "business_ruleset");
      if (__businessRulesetObj != null) {
        builder.businessRuleset(BusinessRuleset.fromJson(__businessRulesetObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BusinessRulesetDeactivateResponse from JSON", e);
    }
  }

  /** Create a new builder for BusinessRulesetDeactivateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BusinessRulesetDeactivateResponse. */
  public static class Builder {

    private BusinessRuleset businessRuleset;

    private Response httpResponse;

    private Builder() {}

    public Builder businessRuleset(BusinessRuleset businessRuleset) {
      this.businessRuleset = businessRuleset;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public BusinessRulesetDeactivateResponse build() {
      return new BusinessRulesetDeactivateResponse(this);
    }
  }

  /** Get the businessRuleset from the response. */
  public BusinessRuleset getBusinessRuleset() {
    return businessRuleset;
  }

  @Override
  public String toString() {
    return "BusinessRulesetDeactivateResponse{" + "businessRuleset=" + businessRuleset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BusinessRulesetDeactivateResponse that = (BusinessRulesetDeactivateResponse) o;
    return java.util.Objects.equals(businessRuleset, that.businessRuleset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(businessRuleset);
  }
}
