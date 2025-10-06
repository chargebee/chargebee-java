package com.chargebee.v4.core.responses.rule;

import com.chargebee.v4.core.models.rule.Rule;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for RuleRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class RuleRetrieveResponse {

  private final Rule rule;

  private RuleRetrieveResponse(Rule rule) {

    this.rule = rule;
  }

  /** Parse JSON response into RuleRetrieveResponse object. */
  public static RuleRetrieveResponse fromJson(String json) {
    try {

      Rule rule = Rule.fromJson(JsonUtil.getObject(json, "rule"));

      return new RuleRetrieveResponse(rule);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RuleRetrieveResponse from JSON", e);
    }
  }

  /** Get the rule from the response. */
  public Rule getRule() {
    return rule;
  }
}
