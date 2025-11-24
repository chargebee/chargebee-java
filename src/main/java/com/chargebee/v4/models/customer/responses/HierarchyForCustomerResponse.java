package com.chargebee.v4.models.customer.responses;

import com.chargebee.v4.models.hierarchy.Hierarchy;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for HierarchyForCustomer operation. Contains the response data from a
 * single resource get operation.
 */
public final class HierarchyForCustomerResponse extends BaseResponse {
  private final List<Hierarchy> hierarchies;

  private HierarchyForCustomerResponse(List<Hierarchy> hierarchies, Response httpResponse) {
    super(httpResponse);

    this.hierarchies = hierarchies;
  }

  /** Parse JSON response into HierarchyForCustomerResponse object. */
  public static HierarchyForCustomerResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into HierarchyForCustomerResponse object with HTTP response. */
  public static HierarchyForCustomerResponse fromJson(String json, Response httpResponse) {
    try {

      List<Hierarchy> hierarchies =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "hierarchies")).stream()
              .map(Hierarchy::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new HierarchyForCustomerResponse(hierarchies, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HierarchyForCustomerResponse from JSON", e);
    }
  }

  /** Get the hierarchies from the response. */
  public List<Hierarchy> getHierarchies() {
    return hierarchies;
  }
}
