package com.chargebee.v4.core.responses.quote;

import java.util.List;

import com.chargebee.v4.core.models.quoteLineGroup.QuoteLineGroup;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.QuoteService;
import com.chargebee.v4.core.models.quote.params.QuoteQuoteLineGroupsForQuoteParams;

/**
 * Immutable response object for QuoteQuoteLineGroupsForQuote operation. Contains paginated list
 * data.
 */
public final class QuoteQuoteLineGroupsForQuoteResponse {

  private final List<QuoteQuoteLineGroupsForQuoteItem> list;

  private final String nextOffset;

  private final String quoteId;

  private final QuoteService service;
  private final QuoteQuoteLineGroupsForQuoteParams originalParams;
  private final Response httpResponse;

  private QuoteQuoteLineGroupsForQuoteResponse(
      List<QuoteQuoteLineGroupsForQuoteItem> list,
      String nextOffset,
      String quoteId,
      QuoteService service,
      QuoteQuoteLineGroupsForQuoteParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.quoteId = quoteId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into QuoteQuoteLineGroupsForQuoteResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static QuoteQuoteLineGroupsForQuoteResponse fromJson(String json) {
    try {

      List<QuoteQuoteLineGroupsForQuoteItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(QuoteQuoteLineGroupsForQuoteItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new QuoteQuoteLineGroupsForQuoteResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse QuoteQuoteLineGroupsForQuoteResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into QuoteQuoteLineGroupsForQuoteResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static QuoteQuoteLineGroupsForQuoteResponse fromJson(
      String json,
      QuoteService service,
      QuoteQuoteLineGroupsForQuoteParams originalParams,
      String quoteId,
      Response httpResponse) {
    try {

      List<QuoteQuoteLineGroupsForQuoteItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(QuoteQuoteLineGroupsForQuoteItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new QuoteQuoteLineGroupsForQuoteResponse(
          list, nextOffset, quoteId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse QuoteQuoteLineGroupsForQuoteResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<QuoteQuoteLineGroupsForQuoteItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws Exception if unable to fetch next page
   */
  public QuoteQuoteLineGroupsForQuoteResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    QuoteQuoteLineGroupsForQuoteParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : QuoteQuoteLineGroupsForQuoteParams.builder())
            .offset(nextOffset)
            .build();

    return service.quoteLineGroupsForQuote(quoteId, nextParams);
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }

  public static class QuoteQuoteLineGroupsForQuoteItem {

    private QuoteLineGroup quoteLineGroup;

    public QuoteLineGroup getQuoteLineGroup() {
      return quoteLineGroup;
    }

    public static QuoteQuoteLineGroupsForQuoteItem fromJson(String json) {
      QuoteQuoteLineGroupsForQuoteItem item = new QuoteQuoteLineGroupsForQuoteItem();

      String __quoteLineGroupJson = JsonUtil.getObject(json, "quote_line_group");
      if (__quoteLineGroupJson != null) {
        item.quoteLineGroup = QuoteLineGroup.fromJson(__quoteLineGroupJson);
      }

      return item;
    }
  }
}
