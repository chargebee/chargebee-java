package com.chargebee.v4.models.thirdPartyEntityMapping.responses;

import com.chargebee.v4.models.thirdPartyEntityMapping.ThirdPartyEntityMapping;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartyEntityMappingUpdateEntity operation. Contains the
 * response data from the API.
 */
public final class ThirdPartyEntityMappingUpdateEntityResponse extends BaseResponse {
  private final ThirdPartyEntityMapping thirdPartyEntityMapping;

  private ThirdPartyEntityMappingUpdateEntityResponse(Builder builder) {
    super(builder.httpResponse);

    this.thirdPartyEntityMapping = builder.thirdPartyEntityMapping;
  }

  /** Parse JSON response into ThirdPartyEntityMappingUpdateEntityResponse object. */
  public static ThirdPartyEntityMappingUpdateEntityResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into ThirdPartyEntityMappingUpdateEntityResponse object with HTTP response.
   */
  public static ThirdPartyEntityMappingUpdateEntityResponse fromJson(
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

    public ThirdPartyEntityMappingUpdateEntityResponse build() {
      return new ThirdPartyEntityMappingUpdateEntityResponse(this);
    }
  }

  /** Get the thirdPartyEntityMapping from the response. */
  public ThirdPartyEntityMapping getThirdPartyEntityMapping() {
    return thirdPartyEntityMapping;
  }

  @Override
  public String toString() {
    return "ThirdPartyEntityMappingUpdateEntityResponse{"
        + "thirdPartyEntityMapping="
        + thirdPartyEntityMapping
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ThirdPartyEntityMappingUpdateEntityResponse that =
        (ThirdPartyEntityMappingUpdateEntityResponse) o;
    return java.util.Objects.equals(thirdPartyEntityMapping, that.thirdPartyEntityMapping);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(thirdPartyEntityMapping);
  }
}
