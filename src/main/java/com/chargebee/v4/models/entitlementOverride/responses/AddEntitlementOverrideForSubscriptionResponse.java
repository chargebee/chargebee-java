package com.chargebee.v4.models.entitlementOverride.responses;

import java.util.List;

import com.chargebee.v4.models.entitlementOverride.EntitlementOverride;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AddEntitlementOverrideForSubscription operation. Contains the
 * response data from the API.
 */
public final class AddEntitlementOverrideForSubscriptionResponse extends BaseResponse {
  private final List<EntitlementOverride> list;

  private AddEntitlementOverrideForSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.list = builder.list;
  }

  /** Parse JSON response into AddEntitlementOverrideForSubscriptionResponse object. */
  public static AddEntitlementOverrideForSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into AddEntitlementOverrideForSubscriptionResponse object with HTTP
   * response.
   */
  public static AddEntitlementOverrideForSubscriptionResponse fromJson(
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
          "Failed to parse AddEntitlementOverrideForSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for AddEntitlementOverrideForSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AddEntitlementOverrideForSubscriptionResponse. */
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

    public AddEntitlementOverrideForSubscriptionResponse build() {
      return new AddEntitlementOverrideForSubscriptionResponse(this);
    }
  }

  /** Get the list from the response. */
  public List<EntitlementOverride> getList() {
    return list;
  }
}
