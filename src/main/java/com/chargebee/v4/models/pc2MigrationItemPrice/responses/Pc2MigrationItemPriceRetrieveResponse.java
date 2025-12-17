package com.chargebee.v4.models.pc2MigrationItemPrice.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationItemPriceRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class Pc2MigrationItemPriceRetrieveResponse extends BaseResponse {
  private final Object pc2MigrationItemPrice;

  private Pc2MigrationItemPriceRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.pc2MigrationItemPrice = builder.pc2MigrationItemPrice;
  }

  /** Parse JSON response into Pc2MigrationItemPriceRetrieveResponse object. */
  public static Pc2MigrationItemPriceRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemPriceRetrieveResponse object with HTTP response. */
  public static Pc2MigrationItemPriceRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.pc2MigrationItemPrice(JsonUtil.getObject(json, "pc2_migration_item_price"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemPriceRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemPriceRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemPriceRetrieveResponse. */
  public static class Builder {

    private Object pc2MigrationItemPrice;

    private Response httpResponse;

    private Builder() {}

    public Builder pc2MigrationItemPrice(Object pc2MigrationItemPrice) {
      this.pc2MigrationItemPrice = pc2MigrationItemPrice;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public Pc2MigrationItemPriceRetrieveResponse build() {
      return new Pc2MigrationItemPriceRetrieveResponse(this);
    }
  }

  /** Get the pc2MigrationItemPrice from the response. */
  public Object getPc2MigrationItemPrice() {
    return pc2MigrationItemPrice;
  }
}
