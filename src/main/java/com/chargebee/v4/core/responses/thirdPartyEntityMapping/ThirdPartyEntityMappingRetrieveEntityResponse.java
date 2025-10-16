package com.chargebee.v4.core.responses.thirdPartyEntityMapping;

import com.chargebee.v4.core.models.thirdPartyEntityMapping.ThirdPartyEntityMapping;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartyEntityMappingRetrieveEntity operation. Contains the
 * response data from a single resource get operation.
 */
public final class ThirdPartyEntityMappingRetrieveEntityResponse extends BaseResponse {
  private final ThirdPartyEntityMapping thirdPartyEntityMapping;

  private ThirdPartyEntityMappingRetrieveEntityResponse(
      ThirdPartyEntityMapping thirdPartyEntityMapping, Response httpResponse) {
    super(httpResponse);

    this.thirdPartyEntityMapping = thirdPartyEntityMapping;
  }

  /** Parse JSON response into ThirdPartyEntityMappingRetrieveEntityResponse object. */
  public static ThirdPartyEntityMappingRetrieveEntityResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into ThirdPartyEntityMappingRetrieveEntityResponse object with HTTP
   * response.
   */
  public static ThirdPartyEntityMappingRetrieveEntityResponse fromJson(
      String json, Response httpResponse) {
    try {

      ThirdPartyEntityMapping thirdPartyEntityMapping =
          ThirdPartyEntityMapping.fromJson(JsonUtil.getObject(json, "third_party_entity_mapping"));

      return new ThirdPartyEntityMappingRetrieveEntityResponse(
          thirdPartyEntityMapping, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartyEntityMappingRetrieveEntityResponse from JSON", e);
    }
  }

  /** Get the thirdPartyEntityMapping from the response. */
  public ThirdPartyEntityMapping getThirdPartyEntityMapping() {
    return thirdPartyEntityMapping;
  }
}
