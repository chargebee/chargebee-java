package com.chargebee.v4.models.thirdPartyEntityMapping.responses;

import com.chargebee.v4.models.thirdPartyEntityMapping.ThirdPartyEntityMapping;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartyEntityMappingListAll operation. Contains the response
 * data from a single resource get operation.
 */
public final class ThirdPartyEntityMappingListAllResponse extends BaseResponse {
  private final ThirdPartyEntityMapping thirdPartyEntityMapping;

  private ThirdPartyEntityMappingListAllResponse(Builder builder) {
    super(builder.httpResponse);

    this.thirdPartyEntityMapping = builder.thirdPartyEntityMapping;
  }

  /** Parse JSON response into ThirdPartyEntityMappingListAllResponse object. */
  public static ThirdPartyEntityMappingListAllResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ThirdPartyEntityMappingListAllResponse object with HTTP response. */
  public static ThirdPartyEntityMappingListAllResponse fromJson(
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
          "Failed to parse ThirdPartyEntityMappingListAllResponse from JSON", e);
    }
  }

  /** Create a new builder for ThirdPartyEntityMappingListAllResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ThirdPartyEntityMappingListAllResponse. */
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

    public ThirdPartyEntityMappingListAllResponse build() {
      return new ThirdPartyEntityMappingListAllResponse(this);
    }
  }

  /** Get the thirdPartyEntityMapping from the response. */
  public ThirdPartyEntityMapping getThirdPartyEntityMapping() {
    return thirdPartyEntityMapping;
  }

  @Override
  public String toString() {
    return "ThirdPartyEntityMappingListAllResponse{"
        + "thirdPartyEntityMapping="
        + thirdPartyEntityMapping
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ThirdPartyEntityMappingListAllResponse that = (ThirdPartyEntityMappingListAllResponse) o;
    return java.util.Objects.equals(thirdPartyEntityMapping, that.thirdPartyEntityMapping);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(thirdPartyEntityMapping);
  }
}
