package com.chargebee.core.responses.thirdPartyEntityMapping;

import com.chargebee.core.models.thirdPartyEntityMapping.ThirdPartyEntityMapping;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ThirdPartyEntityMappingUpdateEntity operation. Contains the
 * response data from the API.
 */
public final class ThirdPartyEntityMappingUpdateEntityResponse {

  private final ThirdPartyEntityMapping thirdPartyEntityMapping;

  private ThirdPartyEntityMappingUpdateEntityResponse(Builder builder) {

    this.thirdPartyEntityMapping = builder.thirdPartyEntityMapping;
  }

  /** Parse JSON response into ThirdPartyEntityMappingUpdateEntityResponse object. */
  public static ThirdPartyEntityMappingUpdateEntityResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __thirdPartyEntityMappingJson = JsonUtil.getObject(json, "third_party_entity_mapping");
      if (__thirdPartyEntityMappingJson != null) {
        builder.thirdPartyEntityMapping(
            ThirdPartyEntityMapping.fromJson(__thirdPartyEntityMappingJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartyEntityMappingUpdateEntityResponse from JSON", e);
    }
  }

  /** Create a new builder for ThirdPartyEntityMappingUpdateEntityResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ThirdPartyEntityMappingUpdateEntityResponse. */
  public static class Builder {

    private ThirdPartyEntityMapping thirdPartyEntityMapping;

    private Builder() {}

    public Builder thirdPartyEntityMapping(ThirdPartyEntityMapping thirdPartyEntityMapping) {
      this.thirdPartyEntityMapping = thirdPartyEntityMapping;
      return this;
    }

    public ThirdPartyEntityMappingUpdateEntityResponse build() {
      return new ThirdPartyEntityMappingUpdateEntityResponse(this);
    }
  }

  /** Get the thirdPartyEntityMapping from the response. */
  public ThirdPartyEntityMapping getThirdPartyEntityMapping() {
    return thirdPartyEntityMapping;
  }
}
