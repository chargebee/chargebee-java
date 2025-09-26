package com.chargebee.core.responses.addon;

import com.chargebee.core.models.addon.Addon;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for AddonRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class AddonRetrieveResponse {

  private final Addon addon;

  private AddonRetrieveResponse(Addon addon) {

    this.addon = addon;
  }

  /** Parse JSON response into AddonRetrieveResponse object. */
  public static AddonRetrieveResponse fromJson(String json) {
    try {

      Addon addon = Addon.fromJson(JsonUtil.getObject(json, "addon"));

      return new AddonRetrieveResponse(addon);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddonRetrieveResponse from JSON", e);
    }
  }

  /** Get the addon from the response. */
  public Addon getAddon() {
    return addon;
  }
}
