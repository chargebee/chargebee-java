package com.chargebee.core.responses.addon;

import com.chargebee.core.models.addon.Addon;

import com.chargebee.internal.JsonUtil;

/** Immutable response object for AddonDelete operation. Contains the response data from the API. */
public final class AddonDeleteResponse {

  private final Addon addon;

  private AddonDeleteResponse(Builder builder) {

    this.addon = builder.addon;
  }

  /** Parse JSON response into AddonDeleteResponse object. */
  public static AddonDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __addonJson = JsonUtil.getObject(json, "addon");
      if (__addonJson != null) {
        builder.addon(Addon.fromJson(__addonJson));
      }

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

    private Builder() {}

    public Builder addon(Addon addon) {
      this.addon = addon;
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
