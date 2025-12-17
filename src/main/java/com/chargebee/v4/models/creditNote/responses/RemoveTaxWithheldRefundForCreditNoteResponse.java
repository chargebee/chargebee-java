package com.chargebee.v4.models.creditNote.responses;

import com.chargebee.v4.models.creditNote.CreditNote;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RemoveTaxWithheldRefundForCreditNote operation. Contains the
 * response data from the API.
 */
public final class RemoveTaxWithheldRefundForCreditNoteResponse extends BaseResponse {
  private final CreditNote creditNote;

  private RemoveTaxWithheldRefundForCreditNoteResponse(Builder builder) {
    super(builder.httpResponse);

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into RemoveTaxWithheldRefundForCreditNoteResponse object. */
  public static RemoveTaxWithheldRefundForCreditNoteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into RemoveTaxWithheldRefundForCreditNoteResponse object with HTTP
   * response.
   */
  public static RemoveTaxWithheldRefundForCreditNoteResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse RemoveTaxWithheldRefundForCreditNoteResponse from JSON", e);
    }
  }

  /** Create a new builder for RemoveTaxWithheldRefundForCreditNoteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for RemoveTaxWithheldRefundForCreditNoteResponse. */
  public static class Builder {

    private CreditNote creditNote;

    private Response httpResponse;

    private Builder() {}

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public RemoveTaxWithheldRefundForCreditNoteResponse build() {
      return new RemoveTaxWithheldRefundForCreditNoteResponse(this);
    }
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }
}
