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

  private ProcessingStatusForUsageFileResponse(Builder builder) {
    super(builder.httpResponse);

    this.usageFile = builder.usageFile;
  }

  /** Parse JSON response into ProcessingStatusForUsageFileResponse object. */
  public static ProcessingStatusForUsageFileResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ProcessingStatusForUsageFileResponse object with HTTP response. */
  public static ProcessingStatusForUsageFileResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __usageFileJson = JsonUtil.getObject(json, "usage_file");
      if (__usageFileJson != null) {
        builder.usageFile(UsageFile.fromJson(__usageFileJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ProcessingStatusForUsageFileResponse from JSON", e);
    }
  }

  /** Create a new builder for ProcessingStatusForUsageFileResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ProcessingStatusForUsageFileResponse. */
  public static class Builder {

    private UsageFile usageFile;

    private Response httpResponse;

    private Builder() {}

    public Builder usageFile(UsageFile usageFile) {
      this.usageFile = usageFile;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public ProcessingStatusForUsageFileResponse build() {
      return new ProcessingStatusForUsageFileResponse(this);
    }
  }

  /** Get the usageFile from the response. */
  public UsageFile getUsageFile() {
    return usageFile;
  }
}
