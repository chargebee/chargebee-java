package com.chargebee.v4.models.itemFamily.responses;

import com.chargebee.v4.models.itemFamily.ItemFamily;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeleteItemFamily operation. Contains the response data from the
 * API.
 */
public final class DeleteItemFamilyResponse extends BaseResponse {
  private final ItemFamily itemFamily;

  private DeleteItemFamilyResponse(Builder builder) {
    super(builder.httpResponse);

    this.itemFamily = builder.itemFamily;
  }

  /** Parse JSON response into DeleteItemFamilyResponse object. */
  public static DeleteItemFamilyResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeleteItemFamilyResponse object with HTTP response. */
  public static DeleteItemFamilyResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemFamilyJson = JsonUtil.getObject(json, "item_family");
      if (__itemFamilyJson != null) {
        builder.itemFamily(ItemFamily.fromJson(__itemFamilyJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeleteItemFamilyResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteItemFamilyResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteItemFamilyResponse. */
  public static class Builder {

    private ItemFamily itemFamily;

    private Response httpResponse;

    private Builder() {}

    public Builder itemFamily(ItemFamily itemFamily) {
      this.itemFamily = itemFamily;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public DeleteItemFamilyResponse build() {
      return new DeleteItemFamilyResponse(this);
    }
  }

  /** Get the itemFamily from the response. */
  public ItemFamily getItemFamily() {
    return itemFamily;
  }
}
