package com.chargebee.v4.models;

import static org.junit.jupiter.api.Assertions.*;

import com.chargebee.v4.transport.Response;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BaseResponse")
class BaseResponseTest {

  @Test
  @DisplayName("Should ignore null header keys when looking up by name")
  void shouldIgnoreNullHeaderKeys() {
    HashMap<String, List<String>> headers = new HashMap<>();
    headers.put(null, List.of("ignored"));
    headers.put("X-Chargebee-Telemetry", List.of("cb;time_ms=1"));

    BaseResponse response = new BaseResponse(new Response(200, headers, "{}".getBytes())) {};

    assertEquals(List.of("cb;time_ms=1"), response.header("X-Chargebee-Telemetry"));
  }
}
