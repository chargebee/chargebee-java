package com.chargebee.v4.core.responses.customer;

import com.chargebee.v4.core.models.hierarchy.Hierarchy;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for CustomerHierarchy operation. Contains the response data from a
 * single resource get operation.
 */
public final class CustomerHierarchyResponse {

  private final List<Hierarchy> hierarchies;

  private final Response httpResponse;

  private CustomerHierarchyResponse(List<Hierarchy> hierarchies, Response httpResponse) {

    this.hierarchies = hierarchies;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into CustomerHierarchyResponse object. */
  public static CustomerHierarchyResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CustomerHierarchyResponse object with HTTP response. */
  public static CustomerHierarchyResponse fromJson(String json, Response httpResponse) {
    try {

      List<Hierarchy> hierarchies =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "hierarchies")).stream()
              .map(Hierarchy::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new CustomerHierarchyResponse(hierarchies, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CustomerHierarchyResponse from JSON", e);
    }
  }

  /** Get the hierarchies from the response. */
  public List<Hierarchy> getHierarchies() {
    return hierarchies;
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
