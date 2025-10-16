package com.chargebee.v4.core.responses.usageFile;

import com.chargebee.v4.core.models.usageFile.UsageFile;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UsageFileProcessingStatus operation. Contains the response data
 * from a single resource get operation.
 */
public final class UsageFileProcessingStatusResponse extends BaseResponse {
  private final UsageFile usageFile;

  private UsageFileProcessingStatusResponse(UsageFile usageFile, Response httpResponse) {
    super(httpResponse);

    this.usageFile = usageFile;
  }

  /** Parse JSON response into UsageFileProcessingStatusResponse object. */
  public static UsageFileProcessingStatusResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UsageFileProcessingStatusResponse object with HTTP response. */
  public static UsageFileProcessingStatusResponse fromJson(String json, Response httpResponse) {
    try {

      UsageFile usageFile = UsageFile.fromJson(JsonUtil.getObject(json, "usage_file"));

      return new UsageFileProcessingStatusResponse(usageFile, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsageFileProcessingStatusResponse from JSON", e);
    }
  }

  /** Get the usageFile from the response. */
  public UsageFile getUsageFile() {
    return usageFile;
  }
}
