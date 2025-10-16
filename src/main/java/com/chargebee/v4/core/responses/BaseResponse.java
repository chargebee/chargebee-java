package com.chargebee.v4.core.responses;

import com.chargebee.v4.transport.Response;
import java.util.List;
import java.util.Map;
import java.util.Collections;

/**
 * Base class for all response objects.
 * Provides common functionality for accessing HTTP response metadata.
 */
public abstract class BaseResponse {
  private final Response httpResponse;

  protected BaseResponse(Response httpResponse) {
    this.httpResponse = httpResponse;
  }

  /**
   * Get the raw response payload as JSON string.
   */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /**
   * Get the HTTP status code.
   */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /**
   * Get response headers.
   */
  public Map<String, List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : Collections.emptyMap();
  }

  /**
   * Get a specific header value.
   */
  public List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }

  /**
   * Check if this response is an idempotency replay.
   * Returns true if the chargebee-idempotency-replayed header is set to "true".
   *
   * @return true if this is a replayed idempotent request, false otherwise
   */
  public boolean isIdempotencyReplayed() {
    List<String> headerValues = header("chargebee-idempotency-replayed");
    return headerValues != null
        && !headerValues.isEmpty()
        && "true".equalsIgnoreCase(headerValues.get(0));
  }

  /**
   * Get the underlying HTTP response object.
   *
   * @return the HTTP response, or null if not available
   */
  protected Response getHttpResponse() {
    return httpResponse;
  }
}
