package com.chargebee.v4.models.itemFamily.responses;

import com.chargebee.v4.models.itemFamily.ItemFamily;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ItemFamilyRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class ItemFamilyRetrieveResponse extends BaseResponse {
  private final ItemFamily itemFamily;

  private ItemFamilyRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.itemFamily = builder.itemFamily;
  }

  /** Parse JSON response into ItemFamilyRetrieveResponse object. */
  public static ItemFamilyRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemFamilyRetrieveResponse object with HTTP response. */
  public static ItemFamilyRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemFamilyJson = JsonUtil.getObject(json, "item_family");
      if (__itemFamilyJson != null) {
        builder.itemFamily(ItemFamily.fromJson(__itemFamilyJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemFamilyRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemFamilyRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemFamilyRetrieveResponse. */
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

    public ItemFamilyRetrieveResponse build() {
      return new ItemFamilyRetrieveResponse(this);
    }
  }

  /** Get the itemFamily from the response. */
  public ItemFamily getItemFamily() {
    return itemFamily;
  }
}
