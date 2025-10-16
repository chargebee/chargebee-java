package com.chargebee.v4.core.responses.pc2Migration;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class Pc2MigrationRetrieveResponse extends BaseResponse {
  private final Object pc2Migration;

  private Pc2MigrationRetrieveResponse(Object pc2Migration, Response httpResponse) {
    super(httpResponse);

    this.pc2Migration = pc2Migration;
  }

  /** Parse JSON response into Pc2MigrationRetrieveResponse object. */
  public static Pc2MigrationRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationRetrieveResponse object with HTTP response. */
  public static Pc2MigrationRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Object pc2Migration = JsonUtil.getObject(json, "pc2_migration");

      return new Pc2MigrationRetrieveResponse(pc2Migration, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationRetrieveResponse from JSON", e);
    }
  }

  /** Get the pc2Migration from the response. */
  public Object getPc2Migration() {
    return pc2Migration;
  }
}
