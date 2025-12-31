package com.chargebee.v4.models.rule.responses;

import com.chargebee.v4.models.rule.Rule;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RuleRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class RuleRetrieveResponse extends BaseResponse {
  private final Rule rule;

  private RuleRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.rule = builder.rule;
  }

  /** Parse JSON response into RuleRetrieveResponse object. */
  public static RuleRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RuleRetrieveResponse object with HTTP response. */
  public static RuleRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __ruleJson = JsonUtil.getObject(json, "rule");
      if (__ruleJson != null) {
        builder.rule(Rule.fromJson(__ruleJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RuleRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for RuleRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for RuleRetrieveResponse. */
  public static class Builder {

    private Rule rule;

    private Response httpResponse;

    private Builder() {}

    public Builder rule(Rule rule) {
      this.rule = rule;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public RuleRetrieveResponse build() {
      return new RuleRetrieveResponse(this);
    }
  }

  /** Get the rule from the response. */
  public Rule getRule() {
    return rule;
  }

  @Override
  public String toString() {
    return "RuleRetrieveResponse{" + "rule=" + rule + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RuleRetrieveResponse that = (RuleRetrieveResponse) o;
    return java.util.Objects.equals(rule, that.rule);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(rule);
  }
}
