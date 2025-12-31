package com.chargebee.v4.models.subscriptionSetting.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionSettingRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class SubscriptionSettingRetrieveResponse extends BaseResponse {
  private final Object subscriptionSetting;

  private SubscriptionSettingRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.subscriptionSetting = builder.subscriptionSetting;
  }

  /** Parse JSON response into SubscriptionSettingRetrieveResponse object. */
  public static SubscriptionSettingRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into SubscriptionSettingRetrieveResponse object with HTTP response. */
  public static SubscriptionSettingRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.subscriptionSetting(JsonUtil.getObject(json, "subscription_setting"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionSettingRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionSettingRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionSettingRetrieveResponse. */
  public static class Builder {

    private Object subscriptionSetting;

    private Response httpResponse;

    private Builder() {}

    public Builder subscriptionSetting(Object subscriptionSetting) {
      this.subscriptionSetting = subscriptionSetting;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public SubscriptionSettingRetrieveResponse build() {
      return new SubscriptionSettingRetrieveResponse(this);
    }
  }

  /** Get the subscriptionSetting from the response. */
  public Object getSubscriptionSetting() {
    return subscriptionSetting;
  }

  @Override
  public String toString() {
    return "SubscriptionSettingRetrieveResponse{"
        + "subscriptionSetting="
        + subscriptionSetting
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SubscriptionSettingRetrieveResponse that = (SubscriptionSettingRetrieveResponse) o;
    return java.util.Objects.equals(subscriptionSetting, that.subscriptionSetting);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(subscriptionSetting);
  }
}
