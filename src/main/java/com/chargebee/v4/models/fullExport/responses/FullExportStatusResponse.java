package com.chargebee.v4.models.fullExport.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for FullExportStatus operation. Contains the response data from a
 * single resource get operation.
 */
public final class FullExportStatusResponse extends BaseResponse {
  private final Object fullExport;

  private FullExportStatusResponse(Builder builder) {
    super(builder.httpResponse);

    this.fullExport = builder.fullExport;
  }

  /** Parse JSON response into FullExportStatusResponse object. */
  public static FullExportStatusResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into FullExportStatusResponse object with HTTP response. */
  public static FullExportStatusResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.fullExport(JsonUtil.getObject(json, "full_export"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FullExportStatusResponse from JSON", e);
    }
  }

  /** Create a new builder for FullExportStatusResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for FullExportStatusResponse. */
  public static class Builder {

    private Object fullExport;

    private Response httpResponse;

    private Builder() {}

    public Builder fullExport(Object fullExport) {
      this.fullExport = fullExport;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public FullExportStatusResponse build() {
      return new FullExportStatusResponse(this);
    }
  }

  /** Get the fullExport from the response. */
  public Object getFullExport() {
    return fullExport;
  }
}
