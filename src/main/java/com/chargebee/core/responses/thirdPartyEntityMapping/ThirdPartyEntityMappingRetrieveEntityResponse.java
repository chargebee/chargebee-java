package com.chargebee.core.responses.thirdPartyEntityMapping;

import com.chargebee.core.models.thirdPartyEntityMapping.ThirdPartyEntityMapping;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ThirdPartyEntityMappingRetrieveEntity operation. Contains the
 * response data from a single resource get operation.
 */
public final class ThirdPartyEntityMappingRetrieveEntityResponse {

  private final ThirdPartyEntityMapping thirdPartyEntityMapping;

  private ThirdPartyEntityMappingRetrieveEntityResponse(
      ThirdPartyEntityMapping thirdPartyEntityMapping) {

    this.thirdPartyEntityMapping = thirdPartyEntityMapping;
  }

  /** Parse JSON response into ThirdPartyEntityMappingRetrieveEntityResponse object. */
  public static ThirdPartyEntityMappingRetrieveEntityResponse fromJson(String json) {
    try {

      ThirdPartyEntityMapping thirdPartyEntityMapping =
          ThirdPartyEntityMapping.fromJson(JsonUtil.getObject(json, "third_party_entity_mapping"));

      return new ThirdPartyEntityMappingRetrieveEntityResponse(thirdPartyEntityMapping);
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
