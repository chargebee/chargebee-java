package com.chargebee.v4.core.responses.thirdPartySyncDetail;

import com.chargebee.v4.core.models.thirdPartySyncDetail.ThirdPartySyncDetail;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartySyncDetailRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class ThirdPartySyncDetailRetrieveResponse {

  private final ThirdPartySyncDetail thirdPartySyncDetail;

  private final Response httpResponse;

  private ThirdPartySyncDetailRetrieveResponse(
      ThirdPartySyncDetail thirdPartySyncDetail, Response httpResponse) {

    this.thirdPartySyncDetail = thirdPartySyncDetail;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into ThirdPartySyncDetailRetrieveResponse object. */
  public static ThirdPartySyncDetailRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ThirdPartySyncDetailRetrieveResponse object with HTTP response. */
  public static ThirdPartySyncDetailRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      ThirdPartySyncDetail thirdPartySyncDetail =
          ThirdPartySyncDetail.fromJson(JsonUtil.getObject(json, "third_party_sync_detail"));

      return new ThirdPartySyncDetailRetrieveResponse(thirdPartySyncDetail, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartySyncDetailRetrieveResponse from JSON", e);
    }
  }

  /** Get the thirdPartySyncDetail from the response. */
  public ThirdPartySyncDetail getThirdPartySyncDetail() {
    return thirdPartySyncDetail;
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
