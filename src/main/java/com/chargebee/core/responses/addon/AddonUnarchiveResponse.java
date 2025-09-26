package com.chargebee.core.responses.addon;

import com.chargebee.core.models.addon.Addon;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for AddonUnarchive operation. Contains the response data from the API.
 */
public final class AddonUnarchiveResponse {

  private final Addon addon;

  private AddonUnarchiveResponse(Builder builder) {

    this.addon = builder.addon;
  }

  /** Parse JSON response into AddonUnarchiveResponse object. */
  public static AddonUnarchiveResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __addonJson = JsonUtil.getObject(json, "addon");
      if (__addonJson != null) {
        builder.addon(Addon.fromJson(__addonJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddonUnarchiveResponse from JSON", e);
    }
  }

  /** Create a new builder for AddonUnarchiveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AddonUnarchiveResponse. */
  public static class Builder {

    private Addon addon;

    private Builder() {}

    public Builder addon(Addon addon) {
      this.addon = addon;
      return this;
    }

    public AddonUnarchiveResponse build() {
      return new AddonUnarchiveResponse(this);
    }
  }

  /** Get the addon from the response. */
  public Addon getAddon() {
    return addon;
  }
}
