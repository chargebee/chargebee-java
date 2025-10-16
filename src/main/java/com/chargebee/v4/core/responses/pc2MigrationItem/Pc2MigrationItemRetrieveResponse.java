package com.chargebee.v4.core.responses.pc2MigrationItem;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationItemRetrieve operation. Contains the response data from
 * a single resource get operation.
 */
public final class Pc2MigrationItemRetrieveResponse extends BaseResponse {
  private final Object pc2MigrationItem;

  private Pc2MigrationItemRetrieveResponse(Object pc2MigrationItem, Response httpResponse) {
    super(httpResponse);

    this.pc2MigrationItem = pc2MigrationItem;
  }

  /** Parse JSON response into Pc2MigrationItemRetrieveResponse object. */
  public static Pc2MigrationItemRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemRetrieveResponse object with HTTP response. */
  public static Pc2MigrationItemRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Object pc2MigrationItem = JsonUtil.getObject(json, "pc2_migration_item");

      return new Pc2MigrationItemRetrieveResponse(pc2MigrationItem, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemRetrieveResponse from JSON", e);
    }
  }

  /** Get the pc2MigrationItem from the response. */
  public Object getPc2MigrationItem() {
    return pc2MigrationItem;
  }
}
