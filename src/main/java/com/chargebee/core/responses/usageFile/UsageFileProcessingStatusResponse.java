package com.chargebee.core.responses.usageFile;

import com.chargebee.core.models.usageFile.UsageFile;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for UsageFileProcessingStatus operation. Contains the response data
 * from a single resource get operation.
 */
public final class UsageFileProcessingStatusResponse {

  private final UsageFile usageFile;

  private UsageFileProcessingStatusResponse(UsageFile usageFile) {

    this.usageFile = usageFile;
  }

  /** Parse JSON response into UsageFileProcessingStatusResponse object. */
  public static UsageFileProcessingStatusResponse fromJson(String json) {
    try {

      UsageFile usageFile = UsageFile.fromJson(JsonUtil.getObject(json, "usage_file"));

      return new UsageFileProcessingStatusResponse(usageFile);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsageFileProcessingStatusResponse from JSON", e);
    }
  }

  /** Get the usageFile from the response. */
  public UsageFile getUsageFile() {
    return usageFile;
  }
}
