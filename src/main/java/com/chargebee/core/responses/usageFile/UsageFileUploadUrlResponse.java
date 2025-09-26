package com.chargebee.core.responses.usageFile;

import com.chargebee.core.models.usageFile.UsageFile;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for UsageFileUploadUrl operation. Contains the response data from the
 * API.
 */
public final class UsageFileUploadUrlResponse {

  private final UsageFile usageFile;

  private UsageFileUploadUrlResponse(Builder builder) {

    this.usageFile = builder.usageFile;
  }

  /** Parse JSON response into UsageFileUploadUrlResponse object. */
  public static UsageFileUploadUrlResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __usageFileJson = JsonUtil.getObject(json, "usage_file");
      if (__usageFileJson != null) {
        builder.usageFile(UsageFile.fromJson(__usageFileJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsageFileUploadUrlResponse from JSON", e);
    }
  }

  /** Create a new builder for UsageFileUploadUrlResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UsageFileUploadUrlResponse. */
  public static class Builder {

    private UsageFile usageFile;

    private Builder() {}

    public Builder usageFile(UsageFile usageFile) {
      this.usageFile = usageFile;
      return this;
    }

    public UsageFileUploadUrlResponse build() {
      return new UsageFileUploadUrlResponse(this);
    }
  }

  /** Get the usageFile from the response. */
  public UsageFile getUsageFile() {
    return usageFile;
  }
}
