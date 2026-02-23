package com.chargebee.v4.internal;

import com.chargebee.v4.models.customer.Customer;
import com.chargebee.v4.models.customer.Customer.BillingAddress;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Customer JSON parsing")
class CustomerParserTest {

    private static Customer customer;

    @BeforeAll
    static void loadFixture() throws IOException {
        String customerJson;
        try (InputStream is = CustomerParserTest.class.getResourceAsStream("/fixtures/customer.json")) {
            assertNotNull(is, "fixtures/customer.json not found on classpath");
            customerJson = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
        customer = Customer.fromJson(customerJson);
    }

    @Nested
    @DisplayName("String fields")
    class StringFields {

        @Test void id() {
            assertEquals("__test__KyVnHhSBWl7eY2bl", customer.getId());
        }

        @Test void firstName() {
            assertEquals("John", customer.getFirstName());
        }

        @Test void lastName() {
            assertEquals("Doe", customer.getLastName());
        }

        @Test void email() {
            assertEquals("john@test.com", customer.getEmail());
        }

        @Test void locale() {
            assertEquals("fr-CA", customer.getLocale());
        }

        @Test void preferredCurrencyCode() {
            assertEquals("USD", customer.getPreferredCurrencyCode());
        }
    }

    @Nested
    @DisplayName("Boolean fields")
    class BooleanFields {

        @Test void allowDirectDebit() {
            assertEquals(false, customer.getAllowDirectDebit());
        }

        @Test void deleted() {
            assertEquals(false, customer.getDeleted());
        }
    }

    @Nested
    @DisplayName("Integer fields")
    class IntegerFields {

        @Test void netTermDays() {
            assertEquals(0, customer.getNetTermDays());
        }
    }

    @Nested
    @DisplayName("Long fields")
    class LongFields {

        @Test void resourceVersion() {
            assertEquals(1517505731000L, customer.getResourceVersion());
        }

        @Test void promotionalCredits() {
            assertEquals(0L, customer.getPromotionalCredits());
        }

        @Test void unbilledCharges() {
            assertEquals(0L, customer.getUnbilledCharges());
        }

        @Test void refundableCredits() {
            assertEquals(0L, customer.getRefundableCredits());
        }

        @Test void excessPayments() {
            assertEquals(0L, customer.getExcessPayments());
        }
    }

    @Nested
    @DisplayName("Enum fields")
    class EnumFields {

        @Test void autoCollection() {
            assertEquals(Customer.AutoCollection.ON, customer.getAutoCollection());
        }

        @Test void piiCleared() {
            assertEquals(Customer.PiiCleared.ACTIVE, customer.getPiiCleared());
        }

        @Test void taxability() {
            assertEquals(Customer.Taxability.TAXABLE, customer.getTaxability());
        }
    }

    @Nested
    @DisplayName("Timestamp fields")
    class TimestampFields {

        @Test void createdAt() {
            assertEquals(new Timestamp(1517505731L * 1000), customer.getCreatedAt());
        }

        @Test void updatedAt() {
            assertEquals(new Timestamp(1517505731L * 1000), customer.getUpdatedAt());
        }
    }

    @Nested
    @DisplayName("Absent fields parse as null")
    class AbsentFields {

        @Test void phone() {
            assertNull(customer.getPhone());
        }

        @Test void company() {
            assertNull(customer.getCompany());
        }

        @Test void vatNumber() {
            assertNull(customer.getVatNumber());
        }

        @Test void createdFromIp() {
            assertNull(customer.getCreatedFromIp());
        }

        @Test void billingDate() {
            assertNull(customer.getBillingDate());
        }

        @Test void channel() {
            assertEquals(Customer.Channel._UNKNOWN, customer.getChannel());
        }

        @Test void fraudFlag() {
            assertEquals(Customer.FraudFlag._UNKNOWN, customer.getFraudFlag());
        }

        @Test void paymentMethod() {
            assertNull(customer.getPaymentMethod());
        }

        @Test void relationship() {
            assertNull(customer.getRelationship());
        }
    }

    @Nested
    @DisplayName("Billing address (nested object)")
    class BillingAddressTests {

        @Test void billingAddressNotNull() {
            assertNotNull(customer.getBillingAddress());
        }

        @Test void city() {
            assertEquals("Walnut", customer.getBillingAddress().getCity());
        }

        @Test void country() {
            assertEquals("US", customer.getBillingAddress().getCountry());
        }

        @Test void billingFirstName() {
            assertEquals("John", customer.getBillingAddress().getFirstName());
        }

        @Test void billingLastName() {
            assertEquals("Mike", customer.getBillingAddress().getLastName());
        }

        @Test void line1() {
            assertEquals("PO Box 9999", customer.getBillingAddress().getLine1());
        }

        @Test void state() {
            assertEquals("California", customer.getBillingAddress().getState());
        }

        @Test void stateCode() {
            assertEquals("CA", customer.getBillingAddress().getStateCode());
        }

        @Test void zip() {
            assertEquals("91789", customer.getBillingAddress().getZip());
        }

        @Test void validationStatus() {
            assertEquals(
                    BillingAddress.ValidationStatus.NOT_VALIDATED,
                    customer.getBillingAddress().getValidationStatus());
        }

        @Test void absentLine2() {
            assertNull(customer.getBillingAddress().getLine2());
        }

        @Test void absentLine3() {
            assertNull(customer.getBillingAddress().getLine3());
        }
    }

    @Nested
    @DisplayName("Empty collections for absent arrays")
    class EmptyCollections {

        @Test void referralUrls() {
            assertNotNull(customer.getReferralUrls());
            assertTrue(customer.getReferralUrls().isEmpty());
        }

        @Test void contacts() {
            assertNotNull(customer.getContacts());
            assertTrue(customer.getContacts().isEmpty());
        }

        @Test void balances() {
            assertNotNull(customer.getBalances());
            assertTrue(customer.getBalances().isEmpty());
        }

        @Test void entityIdentifiers() {
            assertNotNull(customer.getEntityIdentifiers());
            assertTrue(customer.getEntityIdentifiers().isEmpty());
        }

        @Test void taxProvidersFields() {
            assertNotNull(customer.getTaxProvidersFields());
            assertTrue(customer.getTaxProvidersFields().isEmpty());
        }
    }

    @Nested
    @DisplayName("Custom and consent fields")
    class DynamicFields {

        @Test void customFieldsEmpty() {
            assertNotNull(customer.getCustomFields());
            assertTrue(customer.getCustomFields().isEmpty());
        }

        @Test void consentFieldsEmpty() {
            assertNotNull(customer.getConsentFields());
            assertTrue(customer.getConsentFields().isEmpty());
        }
    }
}
