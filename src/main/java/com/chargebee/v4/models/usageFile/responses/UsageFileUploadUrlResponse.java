package com.chargebee.v4.models.usageFile.responses;

import com.chargebee.v4.models.usageFile.UsageFile;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UsageFileUploadUrl operation. Contains the response data from the
 * API.
 */
public final class UsageFileUploadUrlResponse extends BaseResponse {
  private final UsageFile usageFile;

  private UsageFileUploadUrlResponse(Builder builder) {
    super(builder.httpResponse);

    this.usageFile = builder.usageFile;
  }

  /** Parse JSON response into UsageFileUploadUrlResponse object. */
  public static UsageFileUploadUrlResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UsageFileUploadUrlResponse object with HTTP response. */
  public static UsageFileUploadUrlResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __usageFileJson = JsonUtil.getObject(json, "usage_file");
      if (__usageFileJson != null) {
        builder.usageFile(UsageFile.fromJson(__usageFileJson));
      }

      builder.httpResponse(httpResponse);
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

    public UsageFileUploadUrlResponse build() {
      return new UsageFileUploadUrlResponse(this);
    }
  }

  /** Get the usageFile from the response. */
  public UsageFile getUsageFile() {
    return usageFile;
  }

  @Override
  public String toString() {
    return "UsageFileUploadUrlResponse{" + "usageFile=" + usageFile + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UsageFileUploadUrlResponse that = (UsageFileUploadUrlResponse) o;
    return java.util.Objects.equals(usageFile, that.usageFile);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(usageFile);
  }
}
