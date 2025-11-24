package com.chargebee.v4.models.pc2MigrationItemFamily.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationItemFamilyRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class Pc2MigrationItemFamilyRetrieveResponse extends BaseResponse {
  private final Object pc2MigrationItemFamily;

  private Pc2MigrationItemFamilyRetrieveResponse(
      Object pc2MigrationItemFamily, Response httpResponse) {
    super(httpResponse);

    this.pc2MigrationItemFamily = pc2MigrationItemFamily;
  }

  /** Parse JSON response into Pc2MigrationItemFamilyRetrieveResponse object. */
  public static Pc2MigrationItemFamilyRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemFamilyRetrieveResponse object with HTTP response. */
  public static Pc2MigrationItemFamilyRetrieveResponse fromJson(
      String json, Response httpResponse) {
    try {

      Object pc2MigrationItemFamily = JsonUtil.getObject(json, "pc2_migration_item_family");

      return new Pc2MigrationItemFamilyRetrieveResponse(pc2MigrationItemFamily, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemFamilyRetrieveResponse from JSON", e);
    }
  }

  /** Get the pc2MigrationItemFamily from the response. */
  public Object getPc2MigrationItemFamily() {
    return pc2MigrationItemFamily;
  }
}
