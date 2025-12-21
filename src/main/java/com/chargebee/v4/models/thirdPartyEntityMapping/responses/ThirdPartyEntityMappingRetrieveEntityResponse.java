package com.chargebee.v4.models.thirdPartyEntityMapping.responses;

import com.chargebee.v4.models.thirdPartyEntityMapping.ThirdPartyEntityMapping;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartyEntityMappingRetrieveEntity operation. Contains the
 * response data from a single resource get operation.
 */
public final class ThirdPartyEntityMappingRetrieveEntityResponse extends BaseResponse {
  private final ThirdPartyEntityMapping thirdPartyEntityMapping;

  private ThirdPartyEntityMappingRetrieveEntityResponse(Builder builder) {
    super(builder.httpResponse);

    this.thirdPartyEntityMapping = builder.thirdPartyEntityMapping;
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
      Builder builder = builder();

      String __thirdPartyEntityMappingJson = JsonUtil.getObject(json, "third_party_entity_mapping");
      if (__thirdPartyEntityMappingJson != null) {
        builder.thirdPartyEntityMapping(
            ThirdPartyEntityMapping.fromJson(__thirdPartyEntityMappingJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartyEntityMappingRetrieveEntityResponse from JSON", e);
    }
  }

  /** Create a new builder for ThirdPartyEntityMappingRetrieveEntityResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ThirdPartyEntityMappingRetrieveEntityResponse. */
  public static class Builder {

    private ThirdPartyEntityMapping thirdPartyEntityMapping;

    private Response httpResponse;

    private Builder() {}

    public Builder thirdPartyEntityMapping(ThirdPartyEntityMapping thirdPartyEntityMapping) {
      this.thirdPartyEntityMapping = thirdPartyEntityMapping;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public ThirdPartyEntityMappingRetrieveEntityResponse build() {
      return new ThirdPartyEntityMappingRetrieveEntityResponse(this);
    }
  }

  /** Get the thirdPartyEntityMapping from the response. */
  public ThirdPartyEntityMapping getThirdPartyEntityMapping() {
    return thirdPartyEntityMapping;
  }
}
