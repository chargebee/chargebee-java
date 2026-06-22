package com.chargebee.internal;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link Params#toJson()}.
 *
 * Regression coverage for a bug where {@link java.sql.Timestamp} values were
 * serialized in human-readable form (e.g. "2026-06-23 09:54:44.513") because
 * the original implementation handed the raw value map straight to
 * {@code new JSONObject(rawMap)}, which falls back to {@code Timestamp.toString()}.
 * The Chargebee API expects Unix seconds.
 */
public class ParamsToJsonTest {

    private enum Status { ACTIVE, IN_TRIAL, NON_RENEWING }

    /** Build a Timestamp at a known UTC instant so tests are timezone-stable. */
    private static Timestamp utcTimestamp(int year, int month, int day, int hour, int min, int sec) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.clear();
        cal.set(year, month - 1, day, hour, min, sec);
        return new Timestamp(cal.getTimeInMillis());
    }

    @Test
    void timestampIsSerializedAsUnixSecondsNumber() {
        Params p = new Params();
        Timestamp ts = utcTimestamp(2026, 6, 23, 9, 54, 44);
        long expectedUnixSeconds = ts.getTime() / 1000;

        p.add("subscription_id", "overage-check");
        p.add("amount", "500");
        p.add("expires_at", ts);
        p.add("unit_id", "ai_credits");

        JSONObject json = new JSONObject(p.toJson());

        assertEquals("overage-check", json.getString("subscription_id"));
        assertEquals("500", json.getString("amount"));
        assertEquals("ai_credits", json.getString("unit_id"));

        // The key regression assertion: must be a number, not a stringified date.
        assertEquals(expectedUnixSeconds, json.getLong("expires_at"),
                "Timestamp must be serialized as Unix-seconds number, not a human-readable string");

        // And it must NOT look like "yyyy-MM-dd HH:mm:ss(.SSS)?".
        String raw = p.toJson();
        assertFalse(raw.contains("\"expires_at\":\"" + ts.toString() + "\""),
                "Timestamp should not be serialized via Timestamp.toString()");
        assertFalse(raw.matches(".*\"expires_at\"\\s*:\\s*\"\\d{4}-\\d{2}-\\d{2} .*"),
                "Timestamp should not be serialized in 'yyyy-MM-dd HH:mm:ss' form");
    }

    @Test
    void dateIsSerializedAsYyyyMmDdString() {
        Params p = new Params();
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.clear();
        cal.set(2025, Calendar.DECEMBER, 31, 10, 0, 0);
        Date d = cal.getTime();
        p.add("trial_end_date", d);

        String expected = new SimpleDateFormat("yyyy-MM-dd").format(d);
        JSONObject json = new JSONObject(p.toJson());
        assertEquals(expected, json.getString("trial_end_date"));
    }

    @Test
    void enumIsSerializedAsLowercaseString() {
        Params p = new Params();
        p.add("status", Status.IN_TRIAL);

        JSONObject json = new JSONObject(p.toJson());
        assertEquals("in_trial", json.getString("status"));
    }

    @Test
    void numericTypesArePreservedAsJsonNumbers() {
        Params p = new Params();
        p.add("int_val", 42);
        p.add("long_val", 1234567890123L);
        p.add("double_val", 3.14);
        p.add("decimal_val", new BigDecimal("19.99"));
        p.add("bool_val", true);

        JSONObject json = new JSONObject(p.toJson());
        assertEquals(42, json.getInt("int_val"));
        assertEquals(1234567890123L, json.getLong("long_val"));
        assertEquals(3.14, json.getDouble("double_val"), 0.0001);
        assertEquals(new BigDecimal("19.99"), json.getBigDecimal("decimal_val"));
        assertTrue(json.getBoolean("bool_val"));
    }

    @Test
    void stringIsSerializedAsJsonString() {
        Params p = new Params();
        p.add("name", "Alice \"Q\" \n line2");

        JSONObject json = new JSONObject(p.toJson());
        assertEquals("Alice \"Q\" \n line2", json.getString("name"));
    }

    @Test
    void nestedJsonObjectIsPreservedAsStructuredJson() {
        Params p = new Params();
        JSONObject billing = new JSONObject();
        billing.put("city", "NYC");
        billing.put("zip", "10001");
        p.add("billing_address", billing);

        JSONObject json = new JSONObject(p.toJson());
        JSONObject got = json.getJSONObject("billing_address");
        assertEquals("NYC", got.getString("city"));
        assertEquals("10001", got.getString("zip"));
    }

    @Test
    void nestedJsonArrayIsPreservedAsStructuredJson() {
        Params p = new Params();
        JSONArray arr = new JSONArray();
        arr.put("a");
        arr.put("b");
        p.add("tags", arr);

        JSONObject json = new JSONObject(p.toJson());
        JSONArray got = json.getJSONArray("tags");
        assertEquals(2, got.length());
        assertEquals("a", got.getString(0));
        assertEquals("b", got.getString(1));
    }

    @Test
    void mapValueIsPreservedAsStructuredJsonAndTimestampsInsideAreConverted() {
        Params p = new Params();
        Timestamp ts = utcTimestamp(2026, 1, 1, 0, 0, 0);
        long expected = ts.getTime() / 1000;

        Map<String, Object> meta = new LinkedHashMap<String, Object>();
        meta.put("source", "ui");
        meta.put("seen_at", ts);
        p.add("metadata", meta);

        JSONObject json = new JSONObject(p.toJson());
        JSONObject got = json.getJSONObject("metadata");
        assertEquals("ui", got.getString("source"));
        assertEquals(expected, got.getLong("seen_at"));
    }

    @Test
    void listOfTimestampsConvertsEachElement() {
        Params p = new Params();
        Timestamp t1 = utcTimestamp(2026, 1, 1, 0, 0, 0);
        Timestamp t2 = utcTimestamp(2026, 2, 1, 0, 0, 0);
        List<Object> values = new ArrayList<Object>();
        values.add(t1);
        values.add(t2);
        p.add("checkpoints", values);

        JSONObject json = new JSONObject(p.toJson());
        JSONArray arr = json.getJSONArray("checkpoints");
        assertEquals(2, arr.length());
        assertEquals(t1.getTime() / 1000, arr.getLong(0));
        assertEquals(t2.getTime() / 1000, arr.getLong(1));
    }

    @Test
    void objectArrayIsConvertedRecursively() {
        Params p = new Params();
        Timestamp t1 = utcTimestamp(2026, 3, 1, 0, 0, 0);
        Object[] arr = new Object[] { t1, "hello", 7 };
        p.add("mixed", arr);

        JSONObject json = new JSONObject(p.toJson());
        JSONArray got = json.getJSONArray("mixed");
        assertEquals(3, got.length());
        assertEquals(t1.getTime() / 1000, got.getLong(0));
        assertEquals("hello", got.getString(1));
        assertEquals(7, got.getInt(2));
    }

    @Test
    void deeplyNestedMapAndListAreFullyTraversed() {
        Params p = new Params();
        Timestamp ts = utcTimestamp(2026, 4, 15, 12, 0, 0);
        long expected = ts.getTime() / 1000;

        Map<String, Object> inner = new HashMap<String, Object>();
        inner.put("at", ts);
        inner.put("status", Status.ACTIVE);

        List<Object> list = new ArrayList<Object>();
        list.add(inner);
        list.add(Arrays.asList(ts, "x"));

        Map<String, Object> outer = new HashMap<String, Object>();
        outer.put("events", list);
        p.add("payload", outer);

        JSONObject json = new JSONObject(p.toJson());
        JSONArray events = json.getJSONObject("payload").getJSONArray("events");

        JSONObject first = events.getJSONObject(0);
        assertEquals(expected, first.getLong("at"));
        assertEquals("active", first.getString("status"));

        JSONArray second = events.getJSONArray(1);
        assertEquals(expected, second.getLong(0));
        assertEquals("x", second.getString(1));
    }

    @Test
    void addOptWithNullValueIsExcludedFromJson() {
        Params p = new Params();
        p.add("id", "sub_1");
        p.addOpt("trial_end", null);

        JSONObject json = new JSONObject(p.toJson());
        assertEquals("sub_1", json.getString("id"));
        assertFalse(json.has("trial_end"),
                "Optional null params should be omitted from the JSON body (matches original behavior)");
    }

    @Test
    void addOptWithTimestampIsSerializedAsUnixSecondsNumber() {
        Params p = new Params();
        Timestamp ts = utcTimestamp(2027, 7, 4, 18, 30, 0);
        p.addOpt("trial_end", ts);

        JSONObject json = new JSONObject(p.toJson());
        assertEquals(ts.getTime() / 1000, json.getLong("trial_end"));
    }

    @Test
    void emptyParamsProducesEmptyJsonObject() {
        Params p = new Params();
        assertEquals("{}", p.toJson());
    }

    @Test
    void toValStrPathStillReturnsUnixSecondsStringForTimestamp() {
        // Sanity check that the form-encoded path (which uses toValStr) is unaffected.
        Timestamp ts = utcTimestamp(2026, 6, 23, 9, 54, 44);
        Object converted = Params.toValStr(ts);
        assertEquals(String.valueOf(ts.getTime() / 1000), converted);
    }
}
