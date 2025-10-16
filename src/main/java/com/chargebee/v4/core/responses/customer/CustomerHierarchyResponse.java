package com.chargebee.v4.core.responses.customer;

import com.chargebee.v4.core.models.hierarchy.Hierarchy;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for CustomerHierarchy operation. Contains the response data from a
 * single resource get operation.
 */
public final class CustomerHierarchyResponse extends BaseResponse {
  private final List<Hierarchy> hierarchies;

  private CustomerHierarchyResponse(List<Hierarchy> hierarchies, Response httpResponse) {
    super(httpResponse);

    this.hierarchies = hierarchies;
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
}
