package com.chargebee.core.responses.usage;

import com.chargebee.core.models.usage.Usage;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for UsageRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class UsageRetrieveResponse {

  private final Usage usage;

  private UsageRetrieveResponse(Usage usage) {

    this.usage = usage;
  }

  /** Parse JSON response into UsageRetrieveResponse object. */
  public static UsageRetrieveResponse fromJson(String json) {
    try {

      Usage usage = Usage.fromJson(JsonUtil.getObject(json, "usage"));

      return new UsageRetrieveResponse(usage);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsageRetrieveResponse from JSON", e);
    }
  }

  /** Get the usage from the response. */
  public Usage getUsage() {
    return usage;
  }
}
