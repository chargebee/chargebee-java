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

  private Pc2MigrationItemPriceRetrieveResponse(
      Object pc2MigrationItemPrice, Response httpResponse) {
    super(httpResponse);

    this.pc2MigrationItemPrice = pc2MigrationItemPrice;
  }

  /** Parse JSON response into Pc2MigrationItemPriceRetrieveResponse object. */
  public static Pc2MigrationItemPriceRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemPriceRetrieveResponse object with HTTP response. */
  public static Pc2MigrationItemPriceRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Object pc2MigrationItemPrice = JsonUtil.getObject(json, "pc2_migration_item_price");

      return new Pc2MigrationItemPriceRetrieveResponse(pc2MigrationItemPrice, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemPriceRetrieveResponse from JSON", e);
    }
  }

  /** Get the pc2MigrationItemPrice from the response. */
  public Object getPc2MigrationItemPrice() {
    return pc2MigrationItemPrice;
  }
}
