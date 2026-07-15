package com.chargebee.v4.telemetry;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ChargebeeTelemetryHeaderParser")
class ChargebeeTelemetryHeaderParserTest {

  @Test
  @DisplayName("Should parse example 1 from response-header-format.md")
  void shouldParseExampleOne() {
    String header =
        "cb;start_time=@1781280400;res_wait_time_ms=90;tp_time_ms=1350;time_ms=3800, tp-avalara;time_ms=730, tp-stripe;pm=card;time_ms=620, ft-consolidated_invoicing, ft-account_hierarchy";

    Map<String, Object> attributes = ChargebeeTelemetryHeaderParser.parseToSpanAttributes(header);

    assertEquals(1781280400L, attributes.get("chargebee.telemetry.cb.start_time"));
    assertEquals(3800L, attributes.get("chargebee.telemetry.cb.time_ms"));
    assertEquals(90L, attributes.get("chargebee.telemetry.cb.res_wait_time_ms"));
    assertEquals(1350L, attributes.get("chargebee.telemetry.cb.tp_time_ms"));
    assertEquals(730L, attributes.get("chargebee.telemetry.tp.avalara.time_ms"));
    assertEquals(620L, attributes.get("chargebee.telemetry.tp.stripe.time_ms"));
    assertEquals("card", attributes.get("chargebee.telemetry.tp.stripe.pm"));
    assertEquals(
        List.of("consolidated_invoicing", "account_hierarchy"),
        attributes.get("chargebee.telemetry.features"));
  }

  @Test
  @DisplayName("Should parse example 2 from response-header-format.md")
  void shouldParseExampleTwo() {
    String header =
        "cb;start_time=@1781280900;res_wait_time_ms=120;tp_time_ms=1950;time_ms=5200, tp-stripe;pm=card;time_ms=1200, tp-avalara;time_ms=750, ft-account_hierarchy, ft-calendar_billing, ft-consolidated_invoicing";

    Map<String, Object> attributes = ChargebeeTelemetryHeaderParser.parseToSpanAttributes(header);

    assertEquals(1781280900L, attributes.get("chargebee.telemetry.cb.start_time"));
    assertEquals(5200L, attributes.get("chargebee.telemetry.cb.time_ms"));
    assertEquals(120L, attributes.get("chargebee.telemetry.cb.res_wait_time_ms"));
    assertEquals(1950L, attributes.get("chargebee.telemetry.cb.tp_time_ms"));
    assertEquals(1200L, attributes.get("chargebee.telemetry.tp.stripe.time_ms"));
    assertEquals(750L, attributes.get("chargebee.telemetry.tp.avalara.time_ms"));
    assertEquals(
        List.of("account_hierarchy", "calendar_billing", "consolidated_invoicing"),
        attributes.get("chargebee.telemetry.features"));
  }

  @Test
  @DisplayName("Should map all RFC 9651 scalar types")
  void shouldMapAllScalarTypes() {
    String header =
        "cb;start_time=@1781280400;time_ms=3800;ratio=99.9;desc=\"hello world\";enabled=?1;disabled=?0;payload=:aGVsbG8=:;pm=card";

    Map<String, Object> attributes = ChargebeeTelemetryHeaderParser.parseToSpanAttributes(header);

    assertEquals(1781280400L, attributes.get("chargebee.telemetry.cb.start_time"));
    assertEquals(3800L, attributes.get("chargebee.telemetry.cb.time_ms"));
    assertEquals(99.9d, attributes.get("chargebee.telemetry.cb.ratio"));
    assertEquals("hello world", attributes.get("chargebee.telemetry.cb.desc"));
    assertEquals(Boolean.TRUE, attributes.get("chargebee.telemetry.cb.enabled"));
    assertEquals(Boolean.FALSE, attributes.get("chargebee.telemetry.cb.disabled"));
    assertEquals("aGVsbG8=", attributes.get("chargebee.telemetry.cb.payload"));
    assertEquals("card", attributes.get("chargebee.telemetry.cb.pm"));
  }

  @Test
  @DisplayName("Should parse sf-string escapes")
  void shouldParseSfStringEscapes() {
    assertEquals("hello \"world\"", ChargebeeTelemetryHeaderParser.parseScalarValue("\"hello \\\"world\\\"\""));
  }

  @Test
  @DisplayName("Should return empty map for blank or malformed headers")
  void shouldReturnEmptyForInvalidInput() {
    assertTrue(ChargebeeTelemetryHeaderParser.parseToSpanAttributes(null).isEmpty());
    assertTrue(ChargebeeTelemetryHeaderParser.parseToSpanAttributes("").isEmpty());
    assertTrue(ChargebeeTelemetryHeaderParser.parseToSpanAttributes(";;;").isEmpty());
  }
}
