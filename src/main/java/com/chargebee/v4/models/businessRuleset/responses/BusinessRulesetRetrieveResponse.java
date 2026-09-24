package com.chargebee.v4.models.businessRuleset.responses;

import com.chargebee.v4.models.businessRuleset.BusinessRuleset;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BusinessRulesetRetrieve operation. Contains the response data from
 * a single resource get operation.
 */
public final class BusinessRulesetRetrieveResponse extends BaseResponse {
  private final BusinessRuleset businessRuleset;

  private BusinessRulesetRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.businessRuleset = builder.businessRuleset;
  }

  /** Parse JSON response into BusinessRulesetRetrieveResponse object. */
  public static BusinessRulesetRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BusinessRulesetRetrieveResponse object with HTTP response. */
  public static BusinessRulesetRetrieveResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse BusinessRulesetRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for BusinessRulesetRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BusinessRulesetRetrieveResponse. */
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

    public BusinessRulesetRetrieveResponse build() {
      return new BusinessRulesetRetrieveResponse(this);
    }
  }

  /** Get the businessRuleset from the response. */
  public BusinessRuleset getBusinessRuleset() {
    return businessRuleset;
  }

  @Override
  public String toString() {
    return "BusinessRulesetRetrieveResponse{" + "businessRuleset=" + businessRuleset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BusinessRulesetRetrieveResponse that = (BusinessRulesetRetrieveResponse) o;
    return java.util.Objects.equals(businessRuleset, that.businessRuleset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(businessRuleset);
  }
}
