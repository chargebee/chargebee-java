package com.chargebee.v4.core.responses.attachedItem;

import com.chargebee.v4.core.models.attachedItem.AttachedItem;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AttachedItemRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class AttachedItemRetrieveResponse {

  private final AttachedItem attachedItem;

  private final Response httpResponse;

  private AttachedItemRetrieveResponse(AttachedItem attachedItem, Response httpResponse) {

    this.attachedItem = attachedItem;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into AttachedItemRetrieveResponse object. */
  public static AttachedItemRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AttachedItemRetrieveResponse object with HTTP response. */
  public static AttachedItemRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      AttachedItem attachedItem = AttachedItem.fromJson(JsonUtil.getObject(json, "attached_item"));

      return new AttachedItemRetrieveResponse(attachedItem, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AttachedItemRetrieveResponse from JSON", e);
    }
  }

  /** Get the attachedItem from the response. */
  public AttachedItem getAttachedItem() {
    return attachedItem;
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
