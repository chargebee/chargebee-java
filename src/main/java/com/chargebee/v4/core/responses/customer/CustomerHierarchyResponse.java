package com.chargebee.v4.core.responses.customer;

import com.chargebee.v4.core.models.hierarchy.Hierarchy;

import com.chargebee.v4.internal.JsonUtil;
import java.util.List;

/**
 * Immutable response object for CustomerHierarchy operation. Contains the response data from a
 * single resource get operation.
 */
public final class CustomerHierarchyResponse {

  private final List<Hierarchy> hierarchies;

  private CustomerHierarchyResponse(List<Hierarchy> hierarchies) {

    this.hierarchies = hierarchies;
  }

  /** Parse JSON response into CustomerHierarchyResponse object. */
  public static CustomerHierarchyResponse fromJson(String json) {
    try {

      List<Hierarchy> hierarchies =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "hierarchies")).stream()
              .map(Hierarchy::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new CustomerHierarchyResponse(hierarchies);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CustomerHierarchyResponse from JSON", e);
    }
  }

  /** Get the hierarchies from the response. */
  public List<Hierarchy> getHierarchies() {
    return hierarchies;
  }
}
