package com.chargebee.v4.core.responses.thirdPartyEntityMapping;

import com.chargebee.v4.core.models.thirdPartyEntityMapping.ThirdPartyEntityMapping;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartyEntityMappingRetrieveEntity operation. Contains the
 * response data from a single resource get operation.
 */
public final class ThirdPartyEntityMappingRetrieveEntityResponse {

  private final ThirdPartyEntityMapping thirdPartyEntityMapping;

  private final Response httpResponse;

  private ThirdPartyEntityMappingRetrieveEntityResponse(
      ThirdPartyEntityMapping thirdPartyEntityMapping, Response httpResponse) {

    this.thirdPartyEntityMapping = thirdPartyEntityMapping;

    this.httpResponse = httpResponse;
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
