package com.chargebee.v4.core.responses.rule;

import com.chargebee.v4.core.models.rule.Rule;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RuleRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class RuleRetrieveResponse {

  private final Rule rule;

  private final Response httpResponse;

  private RuleRetrieveResponse(Rule rule, Response httpResponse) {

    this.rule = rule;

    this.httpResponse = httpResponse;
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

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
