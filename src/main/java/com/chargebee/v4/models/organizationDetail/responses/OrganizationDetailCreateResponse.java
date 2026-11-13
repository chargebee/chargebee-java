package com.chargebee.v4.models.organizationDetail.responses;

import com.chargebee.v4.models.organizationDetail.OrganizationDetail;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OrganizationDetailCreate operation. Contains the response data from
 * the API.
 */
public final class OrganizationDetailCreateResponse extends BaseResponse {
  private final OrganizationDetail organizationDetail;

  private OrganizationDetailCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.organizationDetail = builder.organizationDetail;
  }

  /** Parse JSON response into OrganizationDetailCreateResponse object. */
  public static OrganizationDetailCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OrganizationDetailCreateResponse object with HTTP response. */
  public static OrganizationDetailCreateResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __organizationDetailObj = JsonUtil.getJsonObject(jsonObj, "organization_detail");
      if (__organizationDetailObj != null) {
        builder.organizationDetail(OrganizationDetail.fromJson(__organizationDetailObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrganizationDetailCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for OrganizationDetailCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OrganizationDetailCreateResponse. */
  public static class Builder {

    private OrganizationDetail organizationDetail;

    private Response httpResponse;

    private Builder() {}

    public Builder organizationDetail(OrganizationDetail organizationDetail) {
      this.organizationDetail = organizationDetail;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public OrganizationDetailCreateResponse build() {
      return new OrganizationDetailCreateResponse(this);
    }
  }

  /** Get the organizationDetail from the response. */
  public OrganizationDetail getOrganizationDetail() {
    return organizationDetail;
  }

  @Override
  public String toString() {
    return "OrganizationDetailCreateResponse{" + "organizationDetail=" + organizationDetail + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    OrganizationDetailCreateResponse that = (OrganizationDetailCreateResponse) o;
    return java.util.Objects.equals(organizationDetail, that.organizationDetail);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(organizationDetail);
  }
}
