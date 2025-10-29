package com.chargebee.v4.core.responses.entitlementOverride;

import java.util.List;

import com.chargebee.v4.core.models.entitlementOverride.EntitlementOverride;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EntitlementOverrideAddEntitlementOverrideForSubscription operation.
 * Contains the response data from the API.
 */
public final class EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse
    extends BaseResponse {
  private final List<EntitlementOverride> list;

  private EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.list = builder.list;
  }

  /**
   * Parse JSON response into EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse
   * object.
   */
  public static EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse fromJson(
      String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse
   * object with HTTP response.
   */
  public static EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.list(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(EntitlementOverride::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse from JSON",
          e);
    }
  }

  /** Create a new builder for EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse. */
  public static class Builder {

    private List<EntitlementOverride> list;

    private Response httpResponse;

    private Builder() {}

    public Builder list(List<EntitlementOverride> list) {
      this.list = list;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse build() {
      return new EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse(this);
    }
  }

  /** Get the list from the response. */
  public List<EntitlementOverride> getList() {
    return list;
  }
}
