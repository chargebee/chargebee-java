package com.chargebee.core.responses.thirdPartyEntityMapping;

import com.chargebee.core.models.thirdPartyEntityMapping.ThirdPartyEntityMapping;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ThirdPartyEntityMappingListAll operation. Contains the response
 * data from a single resource get operation.
 */
public final class ThirdPartyEntityMappingListAllResponse {

  private final ThirdPartyEntityMapping thirdPartyEntityMapping;

  private ThirdPartyEntityMappingListAllResponse(ThirdPartyEntityMapping thirdPartyEntityMapping) {

    this.thirdPartyEntityMapping = thirdPartyEntityMapping;
  }

  /** Parse JSON response into ThirdPartyEntityMappingListAllResponse object. */
  public static ThirdPartyEntityMappingListAllResponse fromJson(String json) {
    try {

      ThirdPartyEntityMapping thirdPartyEntityMapping =
          ThirdPartyEntityMapping.fromJson(JsonUtil.getObject(json, "third_party_entity_mapping"));

      return new ThirdPartyEntityMappingListAllResponse(thirdPartyEntityMapping);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartyEntityMappingListAllResponse from JSON", e);
    }
  }

  /** Get the thirdPartyEntityMapping from the response. */
  public ThirdPartyEntityMapping getThirdPartyEntityMapping() {
    return thirdPartyEntityMapping;
  }
}
