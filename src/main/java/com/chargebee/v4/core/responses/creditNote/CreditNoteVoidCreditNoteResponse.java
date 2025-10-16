package com.chargebee.v4.core.responses.creditNote;

import com.chargebee.v4.core.models.creditNote.CreditNote;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreditNoteVoidCreditNote operation. Contains the response data from
 * the API.
 */
public final class CreditNoteVoidCreditNoteResponse extends BaseResponse {
  private final CreditNote creditNote;

  private CreditNoteVoidCreditNoteResponse(Builder builder) {
    super(builder.httpResponse);

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into CreditNoteVoidCreditNoteResponse object. */
  public static CreditNoteVoidCreditNoteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CreditNoteVoidCreditNoteResponse object with HTTP response. */
  public static CreditNoteVoidCreditNoteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteVoidCreditNoteResponse from JSON", e);
    }
  }

  /** Create a new builder for CreditNoteVoidCreditNoteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreditNoteVoidCreditNoteResponse. */
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

    public CreditNoteVoidCreditNoteResponse build() {
      return new CreditNoteVoidCreditNoteResponse(this);
    }
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }
}
