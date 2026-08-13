/*
 * Copyright 2026 Chargebee Inc.
 */

package com.chargebee.v4.telemetry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/** Parses the {@code X-Chargebee-Telemetry} response header into OpenTelemetry span attributes. */
public final class ChargebeeTelemetryHeaderParser {

  static final String SF_DATE_PREFIX = "@";
  static final String SF_BOOLEAN_TRUE = "?1";
  static final String SF_BOOLEAN_FALSE = "?0";

  private static final Pattern INTEGER_PATTERN = Pattern.compile("-?\\d+");
  private static final Pattern DECIMAL_PATTERN = Pattern.compile("-?\\d+\\.\\d+");

  private ChargebeeTelemetryHeaderParser() {
    // utility class
  }

  /**
   * Parses a raw {@code X-Chargebee-Telemetry} header value into typed span attributes.
   *
   * @param headerValue raw header string
   * @return parsed attributes, or empty map when input is null/blank or structurally invalid
   */
  public static Map<String, Object> parseToSpanAttributes(String headerValue) {
    if (headerValue == null || headerValue.trim().isEmpty()) {
      return Collections.emptyMap();
    }

    try {
      Map<String, Object> attributes = new HashMap<>();
      List<String> features = new ArrayList<>();

      for (String item : splitListItems(headerValue)) {
        parseListItem(item, attributes, features);
      }

      if (!features.isEmpty()) {
        attributes.put(TelemetryAttributeKeys.CHARGEBEE_TELEMETRY_FEATURES, features);
      }

      return attributes.isEmpty() ? Collections.emptyMap() : attributes;
    } catch (RuntimeException ex) {
      return Collections.emptyMap();
    }
  }

  private static void parseListItem(
      String item, Map<String, Object> attributes, List<String> features) {
    String trimmed = item.trim();
    if (trimmed.isEmpty()) {
      return;
    }

    int separator = indexOfParameterSeparator(trimmed);
    String token = separator < 0 ? trimmed : trimmed.substring(0, separator).trim();
    if (token.isEmpty()) {
      throw new IllegalArgumentException("missing sf-item token");
    }

    if (token.startsWith(TelemetryAttributeKeys.CHARGEBEE_TELEMETRY_FT_PREFIX)) {
      features.add(token.substring(TelemetryAttributeKeys.CHARGEBEE_TELEMETRY_FT_PREFIX.length()));
      return;
    }

    String attributePrefix = segmentAttributePrefix(token);
    if (separator >= 0) {
      parseParameters(trimmed.substring(separator + 1), attributePrefix, attributes);
    }
  }

  private static String segmentAttributePrefix(String token) {
    if (TelemetryAttributeKeys.CHARGEBEE_TELEMETRY_CB_SEGMENT.equals(token)) {
      return TelemetryAttributeKeys.CHARGEBEE_TELEMETRY_CB_PREFIX;
    }
    if (token.startsWith(TelemetryAttributeKeys.CHARGEBEE_TELEMETRY_TP_PREFIX)) {
      return TelemetryAttributeKeys.CHARGEBEE_TELEMETRY_TP_ATTRIBUTE_PREFIX
          + token.substring(TelemetryAttributeKeys.CHARGEBEE_TELEMETRY_TP_PREFIX.length())
          + ".";
    }
    return TelemetryAttributeKeys.CHARGEBEE_TELEMETRY_PREFIX + token + ".";
  }

  private static void parseParameters(
      String parametersSection, String attributePrefix, Map<String, Object> attributes) {
    for (String parameter : splitParameters(parametersSection)) {
      parseParameter(parameter, attributePrefix, attributes);
    }
  }

  private static void parseParameter(
      String parameter, String attributePrefix, Map<String, Object> attributes) {
    String trimmed = parameter.trim();
    if (trimmed.isEmpty()) {
      return;
    }

    int equalsIndex = indexOfEquals(trimmed);
    if (equalsIndex <= 0) {
      throw new IllegalArgumentException("invalid parameter: " + trimmed);
    }

    String key = trimmed.substring(0, equalsIndex).trim();
    String rawValue = trimmed.substring(equalsIndex + 1).trim();
    if (key.isEmpty()) {
      throw new IllegalArgumentException("missing parameter key");
    }

    attributes.put(attributePrefix + key, parseScalarValue(rawValue));
  }

  static Object parseScalarValue(String rawValue) {
    if (rawValue == null || rawValue.isEmpty()) {
      throw new IllegalArgumentException("missing scalar value");
    }

    if (rawValue.startsWith(SF_DATE_PREFIX)) {
      return Long.parseLong(rawValue.substring(SF_DATE_PREFIX.length()));
    }
    if (SF_BOOLEAN_TRUE.equals(rawValue)) {
      return Boolean.TRUE;
    }
    if (SF_BOOLEAN_FALSE.equals(rawValue)) {
      return Boolean.FALSE;
    }
    if (rawValue.startsWith(":") && rawValue.endsWith(":") && rawValue.length() >= 2) {
      return rawValue.substring(1, rawValue.length() - 1);
    }
    if (rawValue.startsWith("\"")) {
      return parseStringValue(rawValue);
    }
    if (INTEGER_PATTERN.matcher(rawValue).matches()) {
      return Long.parseLong(rawValue);
    }
    if (DECIMAL_PATTERN.matcher(rawValue).matches()) {
      return Double.parseDouble(rawValue);
    }
    return rawValue;
  }

  private static String parseStringValue(String rawValue) {
    if (rawValue.length() < 2 || rawValue.charAt(rawValue.length() - 1) != '"') {
      throw new IllegalArgumentException("invalid sf-string value");
    }

    StringBuilder decoded = new StringBuilder();
    for (int i = 1; i < rawValue.length() - 1; i++) {
      char current = rawValue.charAt(i);
      if (current == '\\') {
        if (i + 1 >= rawValue.length() - 1) {
          throw new IllegalArgumentException("invalid sf-string escape");
        }
        decoded.append(rawValue.charAt(++i));
      } else {
        decoded.append(current);
      }
    }
    return decoded.toString();
  }

  private static List<String> splitListItems(String input) {
    return splitOnDelimiter(input, ',');
  }

  private static List<String> splitParameters(String input) {
    return splitOnDelimiter(input, ';');
  }

  private static List<String> splitOnDelimiter(String input, char delimiter) {
    List<String> parts = new ArrayList<>();
    StringBuilder current = new StringBuilder();
    boolean inQuotes = false;

    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);
      if (currentChar == '"') {
        if (!isEscapedQuote(input, i)) {
          inQuotes = !inQuotes;
        }
        current.append(currentChar);
      } else if (currentChar == delimiter && !inQuotes) {
        addIfNotBlank(parts, current);
        current = new StringBuilder();
      } else {
        current.append(currentChar);
      }
    }

    addIfNotBlank(parts, current);
    return parts;
  }

  private static int indexOfParameterSeparator(String item) {
    boolean inQuotes = false;
    for (int i = 0; i < item.length(); i++) {
      char current = item.charAt(i);
      if (current == '"') {
        if (!isEscapedQuote(item, i)) {
          inQuotes = !inQuotes;
        }
      } else if (current == ';' && !inQuotes) {
        return i;
      }
    }
    return -1;
  }

  private static int indexOfEquals(String parameter) {
    boolean inQuotes = false;
    for (int i = 0; i < parameter.length(); i++) {
      char current = parameter.charAt(i);
      if (current == '"') {
        if (!isEscapedQuote(parameter, i)) {
          inQuotes = !inQuotes;
        }
      } else if (current == '=' && !inQuotes) {
        return i;
      }
    }
    return -1;
  }

  /** Returns true when {@code input[index]} is a quote escaped by a preceding backslash. */
  private static boolean isEscapedQuote(String input, int index) {
    int backslashes = 0;
    for (int i = index - 1; i >= 0 && input.charAt(i) == '\\'; i--) {
      backslashes++;
    }
    return backslashes % 2 == 1;
  }

  private static void addIfNotBlank(List<String> parts, StringBuilder current) {
    if (current.length() == 0) {
      return;
    }
    String value = current.toString().trim();
    if (!value.isEmpty()) {
      parts.add(value);
    }
  }
}
