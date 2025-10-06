package com.chargebee.v4.core.responses.addon;

import com.chargebee.v4.core.models.addon.Addon;

import com.chargebee.v4.internal.JsonUtil;

/** Immutable response object for AddonUpdate operation. Contains the response data from the API. */
public final class AddonUpdateResponse {

  private final Addon addon;

  private AddonUpdateResponse(Builder builder) {

    this.addon = builder.addon;
  }

  /** Parse JSON response into AddonUpdateResponse object. */
  public static AddonUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __addonJson = JsonUtil.getObject(json, "addon");
      if (__addonJson != null) {
        builder.addon(Addon.fromJson(__addonJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddonUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for AddonUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AddonUpdateResponse. */
  public static class Builder {

    private Addon addon;

    private Builder() {}

    public Builder addon(Addon addon) {
      this.addon = addon;
      return this;
    }

    public AddonUpdateResponse build() {
      return new AddonUpdateResponse(this);
    }
  }

  /** Get the addon from the response. */
  public Addon getAddon() {
    return addon;
  }
}
