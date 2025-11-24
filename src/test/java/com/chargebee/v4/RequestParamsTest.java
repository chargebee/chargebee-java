package com.chargebee.v4;

import com.chargebee.v4.models.estimate.params.EstimateCreateSubscriptionForItemsParams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for strongly-typed request parameter POJOs.
 * Demonstrates MapStruct compatibility and proper Java Bean behavior.
 */
class RequestParamsTest {

    @Test
    @DisplayName("EstimateCreateSubscriptionForItemsParams should build with strongly-typed fields")
    void testEstimateCreateSubscriptionForItemsParamsBuilding() {
        // Build request using the strongly-typed builder
        EstimateCreateSubscriptionForItemsParams params = EstimateCreateSubscriptionForItemsParams.builder()
                .billingCycles(3)
                .mandatoryItemsToRemove(Arrays.asList("item1", "item2"))
                .termsToCharge(12)
                .billingAlignmentMode(EstimateCreateSubscriptionForItemsParams.BillingAlignmentMode.IMMEDIATE)
                .couponIds(Arrays.asList("coupon1", "coupon2"))
                .invoiceImmediately(true)
                .invoiceDate(Timestamp.from(Instant.now()))
                .clientProfileId("profile123")
                .build();

        // Test Java Bean getters (MapStruct compatible)
        assertEquals(3, params.getBillingCycles());
        assertEquals(Arrays.asList("item1", "item2"), params.getMandatoryItemsToRemove());
        assertEquals(12, params.getTermsToCharge());
        assertEquals(EstimateCreateSubscriptionForItemsParams.BillingAlignmentMode.IMMEDIATE, params.getBillingAlignmentMode());
        assertEquals(Arrays.asList("coupon1", "coupon2"), params.getCouponIds());
        assertTrue(params.getInvoiceImmediately());
        assertNotNull(params.getInvoiceDate());
        assertEquals("profile123", params.getClientProfileId());
    }

    @Test
    @DisplayName("EstimateCreateSubscriptionForItemsParams should serialize to Map for HTTP transport")
    void testEstimateCreateSubscriptionForItemsParamsToFormData() {
        // Build request
        EstimateCreateSubscriptionForItemsParams params = EstimateCreateSubscriptionForItemsParams.builder()
                .billingCycles(6)
                .mandatoryItemsToRemove(Arrays.asList("remove1"))
                .termsToCharge(24)
                .invoiceImmediately(false)
                .build();

        // Convert to form data (maintains backward compatibility)
        Map<String, Object> formData = params.toFormData();

        // Verify the Map contains the expected data
        assertEquals(6, formData.get("billing_cycles"));
        assertEquals(Arrays.asList("remove1"), formData.get("mandatory_items_to_remove"));
        assertEquals(24, formData.get("terms_to_charge"));
        assertFalse((Boolean) formData.get("invoice_immediately"));
    }

    @Test
    @DisplayName("Nested SubscriptionParams should work correctly")
    void testNestedSubscriptionParams() {
        // Build nested subscription params
        EstimateCreateSubscriptionForItemsParams.SubscriptionParams subscription =
            EstimateCreateSubscriptionForItemsParams.SubscriptionParams.builder()
                .id("sub123")
                .trialEnd(Timestamp.from(Instant.now().plusSeconds(86400)))
                .build();

        // Build main params with nested subscription
        EstimateCreateSubscriptionForItemsParams params = EstimateCreateSubscriptionForItemsParams.builder()
                .subscription(subscription)
                .billingCycles(12)
                .build();

        // Test nested getters
        assertEquals("sub123", params.getSubscription().getId());
        assertNotNull(params.getSubscription().getTrialEnd());
        assertEquals(12, params.getBillingCycles());
    }

    @Test
    @DisplayName("BillingAddressParams should work as nested object")
    void testNestedBillingAddressParams() {
        // Build nested billing address
        EstimateCreateSubscriptionForItemsParams.BillingAddressParams billingAddress =
            EstimateCreateSubscriptionForItemsParams.BillingAddressParams.builder()
                .line1("123 Main St")
                .city("San Francisco")
                .stateCode("CA")
                .zip("94105")
                .country("US")
                .build();

        // Build main params
        EstimateCreateSubscriptionForItemsParams params = EstimateCreateSubscriptionForItemsParams.builder()
                .billingAddress(billingAddress)
                .build();

        // Test nested getters
        assertEquals("123 Main St", params.getBillingAddress().getLine1());
        assertEquals("San Francisco", params.getBillingAddress().getCity());
        assertEquals("CA", params.getBillingAddress().getStateCode());
        assertEquals("94105", params.getBillingAddress().getZip());
        assertEquals("US", params.getBillingAddress().getCountry());
    }

    @Test
    @DisplayName("Null values should be handled correctly")
    void testNullValues() {
        EstimateCreateSubscriptionForItemsParams params = EstimateCreateSubscriptionForItemsParams.builder()
                .billingCycles(1)
                .build();

        // Test that only set values appear in form data
        Map<String, Object> formData = params.toFormData();
        assertEquals(1, formData.get("billing_cycles"));
        assertFalse(formData.containsKey("terms_to_charge")); // null values not included
    }

    @Test
    @DisplayName("List of SubscriptionItemsParams should work correctly")
    void testListOfSubscriptionItemsParams() {
        // Build list of subscription items
        List<EstimateCreateSubscriptionForItemsParams.SubscriptionItemsParams> items = Arrays.asList(
            EstimateCreateSubscriptionForItemsParams.SubscriptionItemsParams.builder()
                .itemPriceId("price1")
                .quantity(5)
                .build(),
            EstimateCreateSubscriptionForItemsParams.SubscriptionItemsParams.builder()
                .itemPriceId("price2")
                .quantity(10)
                .build()
        );

        // Build main params
        EstimateCreateSubscriptionForItemsParams params = EstimateCreateSubscriptionForItemsParams.builder()
                .subscriptionItems(items)
                .build();

        // Test list getters
        assertEquals(2, params.getSubscriptionItems().size());
        assertEquals("price1", params.getSubscriptionItems().get(0).getItemPriceId());
        assertEquals(5, params.getSubscriptionItems().get(0).getQuantity());
        assertEquals("price2", params.getSubscriptionItems().get(1).getItemPriceId());
        assertEquals(10, params.getSubscriptionItems().get(1).getQuantity());
    }

    @Test
    @DisplayName("Enum values should work correctly")
    void testEnumValues() {
        EstimateCreateSubscriptionForItemsParams params = EstimateCreateSubscriptionForItemsParams.builder()
                .billingAlignmentMode(EstimateCreateSubscriptionForItemsParams.BillingAlignmentMode.DELAYED)
                .build();

        assertEquals(EstimateCreateSubscriptionForItemsParams.BillingAlignmentMode.DELAYED,
                    params.getBillingAlignmentMode());
        assertEquals("delayed", params.getBillingAlignmentMode().getValue());

        // Test enum fromString
        EstimateCreateSubscriptionForItemsParams.BillingAlignmentMode parsed =
            EstimateCreateSubscriptionForItemsParams.BillingAlignmentMode.fromString("immediate");
        assertEquals(EstimateCreateSubscriptionForItemsParams.BillingAlignmentMode.IMMEDIATE, parsed);
    }

    @Test
    @DisplayName("MapStruct compatibility - getters follow Java Bean convention")
    void testMapStructCompatibility() {
        EstimateCreateSubscriptionForItemsParams params = EstimateCreateSubscriptionForItemsParams.builder()
                .billingCycles(42)
                .clientProfileId("test-profile")
                .build();

        // These are the methods MapStruct would use for mapping
        assertEquals(42, params.getBillingCycles());
        assertEquals("test-profile", params.getClientProfileId());

        // Verify the class follows Java Bean conventions
        assertTrue(params.getClass().getDeclaredMethods().length > 0);

        // Check that getters exist and are public
        try {
            java.lang.reflect.Method getBillingCycles = params.getClass().getMethod("getBillingCycles");
            assertTrue(java.lang.reflect.Modifier.isPublic(getBillingCycles.getModifiers()));

            java.lang.reflect.Method getClientProfileId = params.getClass().getMethod("getClientProfileId");
            assertTrue(java.lang.reflect.Modifier.isPublic(getClientProfileId.getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("Required getter methods not found", e);
        }
    }
}
