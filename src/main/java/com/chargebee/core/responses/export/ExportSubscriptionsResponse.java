package com.chargebee.core.responses.export;

import com.chargebee.core.models.export.Export;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ExportSubscriptions operation. Contains the response data from the
 * API.
 */
public final class ExportSubscriptionsResponse {

  private final Export export;

  private ExportSubscriptionsResponse(Builder builder) {

    this.export = builder.export;
  }

  /** Parse JSON response into ExportSubscriptionsResponse object. */
  public static ExportSubscriptionsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __exportJson = JsonUtil.getObject(json, "export");
      if (__exportJson != null) {
        builder.export(Export.fromJson(__exportJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ExportSubscriptionsResponse from JSON", e);
    }
  }

  /** Create a new builder for ExportSubscriptionsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ExportSubscriptionsResponse. */
  public static class Builder {

    private Export export;

    private Builder() {}

    public Builder export(Export export) {
      this.export = export;
      return this;
    }

    public ExportSubscriptionsResponse build() {
      return new ExportSubscriptionsResponse(this);
    }
  }

  /** Get the export from the response. */
  public Export getExport() {
    return export;
  }
}
