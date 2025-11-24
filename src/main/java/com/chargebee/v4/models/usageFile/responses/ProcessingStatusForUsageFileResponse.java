package com.chargebee.v4.models.usageFile.responses;

import com.chargebee.v4.models.usageFile.UsageFile;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ProcessingStatusForUsageFile operation. Contains the response data
 * from a single resource get operation.
 */
public final class ProcessingStatusForUsageFileResponse extends BaseResponse {
  private final UsageFile usageFile;

  private ProcessingStatusForUsageFileResponse(UsageFile usageFile, Response httpResponse) {
    super(httpResponse);

    this.usageFile = usageFile;
  }

  /** Parse JSON response into ProcessingStatusForUsageFileResponse object. */
  public static ProcessingStatusForUsageFileResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ProcessingStatusForUsageFileResponse object with HTTP response. */
  public static ProcessingStatusForUsageFileResponse fromJson(String json, Response httpResponse) {
    try {

      UsageFile usageFile = UsageFile.fromJson(JsonUtil.getObject(json, "usage_file"));

      return new ProcessingStatusForUsageFileResponse(usageFile, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ProcessingStatusForUsageFileResponse from JSON", e);
    }
  }

  /** Get the usageFile from the response. */
  public UsageFile getUsageFile() {
    return usageFile;
  }
}
