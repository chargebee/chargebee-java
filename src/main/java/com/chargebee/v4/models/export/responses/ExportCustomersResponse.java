package com.chargebee.v4.models.export.responses;

import com.chargebee.v4.models.export.Export;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ExportCustomers operation. Contains the response data from the API.
 */
public final class ExportCustomersResponse extends BaseResponse {
  private final Export export;

  private ExportCustomersResponse(Builder builder) {
    super(builder.httpResponse);

    this.export = builder.export;
  }

  /** Parse JSON response into ExportCustomersResponse object. */
  public static ExportCustomersResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ExportCustomersResponse object with HTTP response. */
  public static ExportCustomersResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __exportJson = JsonUtil.getObject(json, "export");
      if (__exportJson != null) {
        builder.export(Export.fromJson(__exportJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ExportCustomersResponse from JSON", e);
    }
  }

  /** Create a new builder for ExportCustomersResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ExportCustomersResponse. */
  public static class Builder {

    private Export export;

    private Response httpResponse;

    private Builder() {}

    public Builder export(Export export) {
      this.export = export;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public ExportCustomersResponse build() {
      return new ExportCustomersResponse(this);
    }
  }

  /** Get the export from the response. */
  public Export getExport() {
    return export;
  }

  @Override
  public String toString() {
    return "ExportCustomersResponse{" + "export=" + export + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ExportCustomersResponse that = (ExportCustomersResponse) o;
    return java.util.Objects.equals(export, that.export);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(export);
  }
}
