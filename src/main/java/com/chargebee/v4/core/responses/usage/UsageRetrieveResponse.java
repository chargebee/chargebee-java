package com.chargebee.v4.core.responses.usage;

import com.chargebee.v4.core.models.usage.Usage;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UsageRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class UsageRetrieveResponse extends BaseResponse {
  private final Usage usage;

  private UsageRetrieveResponse(Usage usage, Response httpResponse) {
    super(httpResponse);

    this.usage = usage;
  }

  /** Parse JSON response into UsageRetrieveResponse object. */
  public static UsageRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UsageRetrieveResponse object with HTTP response. */
  public static UsageRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Usage usage = Usage.fromJson(JsonUtil.getObject(json, "usage"));

      return new UsageRetrieveResponse(usage, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsageRetrieveResponse from JSON", e);
    }
  }

  /** Get the usage from the response. */
  public Usage getUsage() {
    return usage;
  }
}
