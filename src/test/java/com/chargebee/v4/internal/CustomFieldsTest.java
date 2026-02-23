package com.chargebee.v4.internal;

import com.chargebee.v4.models.customer.Customer;
import com.chargebee.v4.models.invoice.Invoice;
import com.chargebee.v4.models.subscription.Subscription;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Verifies that model fromJson correctly extracts root-level custom fields
 * (cf_*) while ignoring those buried inside nested objects and arrays.
 * Tests multiple models to ensure the shared extractCustomFields pattern works generically.
 */
@DisplayName("Custom Fields (cf_*) extraction across models")
class CustomFieldsTest {

    private static final String NESTED_JSON_TEMPLATE = "{"
            + "\"id\": \"%s\","
            + "\"billing_address\": {"
            + "  \"first_name\": \"John\","
            + "  \"cf_addr_note\": \"should not leak\""
            + "},"
            + "\"line_items\": [{"
            + "  \"id\": \"li_1\","
            + "  \"cf_line_note\": \"should not leak either\""
            + "}],"
            + "\"cf_root_field\": \"extracted\""
            + "}";

    // ========== Invoice ==========
    @Nested
    @DisplayName("Invoice")
    class InvoiceTests {

        @Test void rootCustomFields() {
            String json = "{\"id\": \"inv_1\", \"status\": \"paid\","
                    + "\"cf_department\": \"engineering\","
                    + "\"cf_cost_center\": \"CC-100\","
                    + "\"cf_notes\": \"rush order\"}";
            Invoice inv = Invoice.fromJson(json);
            assertEquals("inv_1", inv.getId());
            assertEquals("engineering", inv.getCustomField("cf_department"));
            assertEquals("CC-100", inv.getCustomField("cf_cost_center"));
            assertEquals("rush order", inv.getCustomField("cf_notes"));
            assertEquals(3, inv.getCustomFields().size());
        }

        @Test void nestedCfFieldsNotLeaked() {
            Invoice inv = Invoice.fromJson(String.format(NESTED_JSON_TEMPLATE, "inv_1"));
            assertEquals("extracted", inv.getCustomField("cf_root_field"));
            assertNull(inv.getCustomField("cf_addr_note"));
            assertNull(inv.getCustomField("cf_line_note"));
            assertEquals(1, inv.getCustomFields().size());
        }

        @Test void emptyWhenNoCfPresent() {
            Invoice inv = Invoice.fromJson("{\"id\": \"inv_1\", \"status\": \"paid\"}");
            assertNotNull(inv.getCustomFields());
            assertTrue(inv.getCustomFields().isEmpty());
        }

        @Test void rootCfWinsSameKeyAtNested() {
            String json = "{\"id\": \"inv_1\","
                    + "\"billing_address\": {\"cf_priority\": \"nested\"},"
                    + "\"cf_priority\": \"root\"}";
            Invoice inv = Invoice.fromJson(json);
            assertEquals("root", inv.getCustomField("cf_priority"));
            assertEquals(1, inv.getCustomFields().size());
        }
    }

    // ========== Customer ==========
    @Nested
    @DisplayName("Customer")
    class CustomerTests {

        @Test void rootCustomFields() {
            String json = "{\"id\": \"cust_1\", \"first_name\": \"Jane\","
                    + "\"cf_tier\": \"gold\", \"cf_region\": \"APAC\"}";
            Customer cust = Customer.fromJson(json);
            assertEquals("cust_1", cust.getId());
            assertEquals("gold", cust.getCustomField("cf_tier"));
            assertEquals("APAC", cust.getCustomField("cf_region"));
            assertEquals(2, cust.getCustomFields().size());
        }

        @Test void nestedCfFieldsNotLeaked() {
            String json = "{\"id\": \"cust_1\","
                    + "\"billing_address\": {\"cf_addr_tag\": \"nested\"},"
                    + "\"cf_visible\": \"yes\"}";
            Customer cust = Customer.fromJson(json);
            assertEquals("yes", cust.getCustomField("cf_visible"));
            assertNull(cust.getCustomField("cf_addr_tag"));
        }

        @Test void emptyWhenNoCfPresent() {
            Customer cust = Customer.fromJson("{\"id\": \"cust_1\"}");
            assertNotNull(cust.getCustomFields());
            assertTrue(cust.getCustomFields().isEmpty());
        }
    }

    // ========== Subscription ==========
    @Nested
    @DisplayName("Subscription")
    class SubscriptionTests {

        @Test void rootCustomFields() {
            String json = "{\"id\": \"sub_1\", \"status\": \"active\","
                    + "\"cf_channel\": \"web\", \"cf_campaign_id\": \"camp_42\"}";
            Subscription sub = Subscription.fromJson(json);
            assertEquals("sub_1", sub.getId());
            assertEquals("web", sub.getCustomField("cf_channel"));
            assertEquals("camp_42", sub.getCustomField("cf_campaign_id"));
            assertEquals(2, sub.getCustomFields().size());
        }

        @Test void nestedCfFieldsNotLeaked() {
            String json = "{\"id\": \"sub_1\","
                    + "\"subscription_items\": [{\"item_price_id\": \"p1\", \"cf_item_tag\": \"nested\"}],"
                    + "\"cf_plan_note\": \"visible\"}";
            Subscription sub = Subscription.fromJson(json);
            assertEquals("visible", sub.getCustomField("cf_plan_note"));
            assertNull(sub.getCustomField("cf_item_tag"));
            assertEquals(1, sub.getCustomFields().size());
        }

        @Test void emptyWhenNoCfPresent() {
            Subscription sub = Subscription.fromJson("{\"id\": \"sub_1\"}");
            assertNotNull(sub.getCustomFields());
            assertTrue(sub.getCustomFields().isEmpty());
        }
    }

    // ========== Consent Fields (Customer-specific cs_*) ==========
    @Nested
    @DisplayName("Consent Fields (cs_*) - Customer")
    class ConsentFieldsTests {

        @Test void rootConsentFields() {
            String json = "{\"id\": \"cust_1\","
                    + "\"cs_marketing\": true, \"cs_analytics\": false}";
            Customer cust = Customer.fromJson(json);
            assertEquals(true, cust.getConsentFieldAsBoolean("cs_marketing"));
            assertEquals(false, cust.getConsentFieldAsBoolean("cs_analytics"));
        }

        @Test void emptyWhenNoCsPresent() {
            Customer cust = Customer.fromJson("{\"id\": \"cust_1\"}");
            assertNotNull(cust.getConsentFields());
            assertTrue(cust.getConsentFields().isEmpty());
        }
    }
}
