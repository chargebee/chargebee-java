package com.chargebee.v4.models.addon.responses;

import com.chargebee.v4.models.addon.Addon;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AddonRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class AddonRetrieveResponse extends BaseResponse {
  private final Addon addon;

  private AddonRetrieveResponse(Addon addon, Response httpResponse) {
    super(httpResponse);

    this.addon = addon;
  }

  /** Parse JSON response into AddonRetrieveResponse object. */
  public static AddonRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AddonRetrieveResponse object with HTTP response. */
  public static AddonRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Addon addon = Addon.fromJson(JsonUtil.getObject(json, "addon"));

      return new AddonRetrieveResponse(addon, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddonRetrieveResponse from JSON", e);
    }
  }

  /** Get the addon from the response. */
  public Addon getAddon() {
    return addon;
  }
}
