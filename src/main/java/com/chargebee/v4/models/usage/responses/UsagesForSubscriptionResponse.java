package com.chargebee.v4.models.usage.responses;

import com.chargebee.v4.models.usage.Usage;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UsagesForSubscription operation. Contains the response data from a
 * single resource get operation.
 */
public final class UsagesForSubscriptionResponse extends BaseResponse {
  private final Usage usage;

  private UsagesForSubscriptionResponse(Usage usage, Response httpResponse) {
    super(httpResponse);

    this.usage = usage;
  }

  /** Parse JSON response into UsagesForSubscriptionResponse object. */
  public static UsagesForSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UsagesForSubscriptionResponse object with HTTP response. */
  public static UsagesForSubscriptionResponse fromJson(String json, Response httpResponse) {
    try {

      Usage usage = Usage.fromJson(JsonUtil.getObject(json, "usage"));

      return new UsagesForSubscriptionResponse(usage, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsagesForSubscriptionResponse from JSON", e);
    }
  }

  /** Get the usage from the response. */
  public Usage getUsage() {
    return usage;
  }
}
