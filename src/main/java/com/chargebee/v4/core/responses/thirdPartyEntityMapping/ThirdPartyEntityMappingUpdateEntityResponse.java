package com.chargebee.v4.core.responses.thirdPartyEntityMapping;

import com.chargebee.v4.core.models.thirdPartyEntityMapping.ThirdPartyEntityMapping;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartyEntityMappingUpdateEntity operation. Contains the
 * response data from the API.
 */
public final class ThirdPartyEntityMappingUpdateEntityResponse {

  private final ThirdPartyEntityMapping thirdPartyEntityMapping;

  private final Response httpResponse;

  private ThirdPartyEntityMappingUpdateEntityResponse(Builder builder) {

    this.thirdPartyEntityMapping = builder.thirdPartyEntityMapping;

    this.httpResponse = builder.httpResponse;
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

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
