package com.chargebee.v4.models.addon.responses;

import com.chargebee.v4.models.addon.Addon;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for AddonDelete operation. Contains the response data from the API. */
public final class AddonDeleteResponse extends BaseResponse {
  private final Addon addon;

  private AddonDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.addon = builder.addon;
  }

  /** Parse JSON response into AddonDeleteResponse object. */
  public static AddonDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AddonDeleteResponse object with HTTP response. */
  public static AddonDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __addonJson = JsonUtil.getObject(json, "addon");
      if (__addonJson != null) {
        builder.addon(Addon.fromJson(__addonJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddonDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for AddonDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AddonDeleteResponse. */
  public static class Builder {

    private Addon addon;

    private Response httpResponse;

    private Builder() {}

    public Builder addon(Addon addon) {
      this.addon = addon;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public AddonDeleteResponse build() {
      return new AddonDeleteResponse(this);
    }
  }

  /** Get the addon from the response. */
  public Addon getAddon() {
    return addon;
  }
}
