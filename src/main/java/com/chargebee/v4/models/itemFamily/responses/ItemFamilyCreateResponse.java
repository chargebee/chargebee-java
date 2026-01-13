package com.chargebee.v4.models.itemFamily.responses;

import com.chargebee.v4.models.itemFamily.ItemFamily;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ItemFamilyCreate operation. Contains the response data from the
 * API.
 */
public final class ItemFamilyCreateResponse extends BaseResponse {
  private final ItemFamily itemFamily;

  private ItemFamilyCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.itemFamily = builder.itemFamily;
  }

  /** Parse JSON response into ItemFamilyCreateResponse object. */
  public static ItemFamilyCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemFamilyCreateResponse object with HTTP response. */
  public static ItemFamilyCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemFamilyJson = JsonUtil.getObject(json, "item_family");
      if (__itemFamilyJson != null) {
        builder.itemFamily(ItemFamily.fromJson(__itemFamilyJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemFamilyCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemFamilyCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemFamilyCreateResponse. */
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

    public ItemFamilyCreateResponse build() {
      return new ItemFamilyCreateResponse(this);
    }
  }

  /** Get the itemFamily from the response. */
  public ItemFamily getItemFamily() {
    return itemFamily;
  }

  @Override
  public String toString() {
    return "ItemFamilyCreateResponse{" + "itemFamily=" + itemFamily + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ItemFamilyCreateResponse that = (ItemFamilyCreateResponse) o;
    return java.util.Objects.equals(itemFamily, that.itemFamily);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(itemFamily);
  }
}
