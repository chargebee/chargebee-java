package com.chargebee.v4.models.addon.responses;

import com.chargebee.v4.models.addon.Addon;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for DeleteAddon operation. Contains the response data from the API. */
public final class DeleteAddonResponse extends BaseResponse {
  private final Addon addon;

  private DeleteAddonResponse(Builder builder) {
    super(builder.httpResponse);

    this.addon = builder.addon;
  }

  /** Parse JSON response into DeleteAddonResponse object. */
  public static DeleteAddonResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeleteAddonResponse object with HTTP response. */
  public static DeleteAddonResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __addonJson = JsonUtil.getObject(json, "addon");
      if (__addonJson != null) {
        builder.addon(Addon.fromJson(__addonJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeleteAddonResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteAddonResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteAddonResponse. */
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

    public DeleteAddonResponse build() {
      return new DeleteAddonResponse(this);
    }
  }

  /** Get the addon from the response. */
  public Addon getAddon() {
    return addon;
  }
}
