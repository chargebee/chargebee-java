package com.chargebee.v4.models.creditNote.responses;

import com.chargebee.v4.models.creditNote.CreditNote;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreditNoteRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CreditNoteRetrieveResponse extends BaseResponse {
  private final CreditNote creditNote;

  private CreditNoteRetrieveResponse(CreditNote creditNote, Response httpResponse) {
    super(httpResponse);

    this.creditNote = creditNote;
  }

  /** Parse JSON response into CreditNoteRetrieveResponse object. */
  public static CreditNoteRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CreditNoteRetrieveResponse object with HTTP response. */
  public static CreditNoteRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      CreditNote creditNote = CreditNote.fromJson(JsonUtil.getObject(json, "credit_note"));

      return new CreditNoteRetrieveResponse(creditNote, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteRetrieveResponse from JSON", e);
    }
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }
}
