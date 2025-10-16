package com.chargebee.v4.core.responses.rule;

import com.chargebee.v4.core.models.rule.Rule;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RuleRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class RuleRetrieveResponse extends BaseResponse {
  private final Rule rule;

  private RuleRetrieveResponse(Rule rule, Response httpResponse) {
    super(httpResponse);

    this.rule = rule;
  }

  /** Parse JSON response into RuleRetrieveResponse object. */
  public static RuleRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RuleRetrieveResponse object with HTTP response. */
  public static RuleRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Rule rule = Rule.fromJson(JsonUtil.getObject(json, "rule"));

      return new RuleRetrieveResponse(rule, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RuleRetrieveResponse from JSON", e);
    }
  }

  /** Get the rule from the response. */
  public Rule getRule() {
    return rule;
  }
}
