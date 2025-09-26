//package com.chargebee.core.services;
//
//
//import com.chargebee.client.ChargebeeClient;
//import com.chargebee.client.request.RequestOptions;
//import com.chargebee.core.http.ChargebeeApiResponse;
//import com.chargebee.core.models.customer.Customer;
//import com.chargebee.core.models.customer.CustomerList;
//import com.chargebee.core.models.customer.builder.CustomerCreateBuilder;
//import com.chargebee.core.models.customer.builder.CustomerCreateBuilderRaw;
//import com.chargebee.core.models.customer.builder.CustomerListBuilder;
//import com.chargebee.core.models.customer.builder.CustomerListBuilderRaw;
//import com.chargebee.core.models.customer.params.CustomerCreateParams;
//import com.chargebee.core.models.customer.params.CustomerListParams;
//import com.chargebee.internal.JsonUtil;
//import com.chargebee.transport.Response;
//
//import java.util.concurrent.CompletableFuture;
//
///**
// * Customer service for fluent API access.
// * Provides both quick-start fluent builders and params-based methods.
// */
//public final class CustomerService_old extends BaseService<CustomerService_old> {
//
//    private final ServiceConfig config;
//
//    public CustomerService_old(ChargebeeClient client) {
//        super(client);
//        this.config = ServiceConfig.defaultConfig();
//    }
//
//    private CustomerService_old(ChargebeeClient client, RequestOptions options) {
//        super(client, options);
//        this.config = ServiceConfig.defaultConfig();
//    }
//
//    private CustomerService_old(ChargebeeClient client, RequestOptions options, ServiceConfig config) {
//        super(client, options);
//        this.config = config;
//    }
//
//    @Override
//    CustomerService_old with(RequestOptions newOptions) {
//        return new CustomerService_old(client, newOptions, config);
//    }
//
//    private RawApi rawApiInstance;
//
//    /**
//     * Access methods that return raw responses (ChargebeeApiResponse).
//     */
//    public RawApi withRawResponse() {
//        if (this.rawApiInstance == null) {
//            this.rawApiInstance = new RawApi(this);
//        }
//        return this.rawApiInstance;
//    }
//
//    /**
//     * Start building a customer creation request.
//     * For reusable params, prefer {@link #create(CustomerCreateParams)}.
//     */
//    public CustomerCreateBuilder create() {
//        return new CustomerCreateBuilder(this);
//    }
//
//    /**
//     * Create a customer using immutable params (executes immediately) - returns raw Response.
//     */
//    Response createRaw(CustomerCreateParams params) throws Exception {
//        return post("/customers", params.toFormData());
//    }
//
//    /**
//     * Create a customer using raw JSON payload (executes immediately) - returns raw Response.
//     */
//    Response createRaw(String jsonPayload) throws Exception {
//        return postJson("/customers", jsonPayload);
//    }
//
//    public Customer create(CustomerCreateParams params) throws Exception {
//        Response response = createRaw(params);
//        return parseCustomerFromResponse(response);
//    }
//
//    /**
//     * Create a customer using immutable params asynchronously - returns raw Response.
//     */
//    CompletableFuture<Response> createRawAsync(CustomerCreateParams params) {
//        return postAsync("/customers", params.toFormData());
//    }
//
//    /**
//     * Create a customer using raw JSON payload asynchronously - returns raw Response.
//     */
//    CompletableFuture<Response> createRawAsync(String jsonPayload) {
//        return postJsonAsync("/customers", jsonPayload);
//    }
//
//    /**
//     * Start building a customer list request.
//     * For reusable params, prefer {@link #list(CustomerListParams)}.
//     */
//    public CustomerListBuilder list() {
//        return new CustomerListBuilder(this);
//    }
//
//    /**
//     * List customers using immutable params (executes immediately) - returns raw Response.
//     */
//    public Response listRaw(CustomerListParams params) throws Exception {
//        return get("/customers", params.toQueryParams());
//    }
//
//    /**
//     * List customers using immutable params asynchronously - returns raw Response.
//     */
//    CompletableFuture<Response> listRawAsync(CustomerListParams params) {
//        return getAsync("/customers", params.toQueryParams());
//    }
//
//    // === Fluent Response API ===
//
//    /**
//     * Create a customer and return a fluent Customer object.
//     */
//
//
//    /**
//     * Create a customer with JSON payload and return a fluent Customer object.
//     */
//    public Customer create(String jsonPayload) throws Exception {
//        Response response = createRaw(jsonPayload);
//        return parseCustomerFromResponse(response);
//    }
//
//    /**
//     * Create a customer asynchronously and return a fluent Customer object.
//     */
//    public CompletableFuture<Customer> createAsync(CustomerCreateParams params) {
//        return createRawAsync(params).thenApply(this::parseCustomerFromResponse);
//    }
//
//    /**
//     * Create a customer with JSON payload asynchronously and return a fluent Customer object.
//     */
//    public CompletableFuture<Customer> createAsync(String jsonPayload) {
//        return createRawAsync(jsonPayload).thenApply(this::parseCustomerFromResponse);
//    }
//
//
//
//    /**
//     * List all customers and return a fluent CustomerList with pagination support.
//     */
//    public CustomerList listAll() throws Exception {
//        CustomerListParams params = CustomerListParams.builder().build();
//        return list(params);
//    }
//
//    /**
//     * List customers with params and return a fluent CustomerList with pagination support.
//     */
//    public CustomerList list(CustomerListParams params) throws Exception {
//        Response response = listRaw(params);
//        return new CustomerList(this, params, response, false);
//    }
//
//
//
//    /**
//     * List all customers asynchronously and return a fluent CustomerList with pagination support.
//     */
//    public CompletableFuture<CustomerList> listAllAsync() {
//        CustomerListParams params = CustomerListParams.builder().build();
//        return listAsync(params);
//    }
//
//    /**
//     * List customers with params asynchronously and return a fluent CustomerList with pagination support.
//     */
//    public CompletableFuture<CustomerList> listAsync(CustomerListParams params) {
//        return listRawAsync(params).thenApply(response -> {
//            try {
//                return new CustomerList(this, params, response, false);
//            } catch (Exception e) {
//                throw new RuntimeException("Failed to parse customer list response", e);
//            }
//        });
//    }
//
//
//
//    // === Helper Methods ===
//
//    private Customer parseCustomerFromResponse(Response response) {
//        try {
//            String json = response.getBodyAsString();
//
//            // Try parsing as wrapped format first: {"customer": {...}}
//            String customerJson = JsonUtil.getObject(json, "customer");
//            if (customerJson != null) {
//                return Customer.fromJson(customerJson);
//            }
//
//            // Fallback to direct format for testing: {"id": "...", "first_name": "..."}
//            if (JsonUtil.hasValue(json, "id")) {
//                return Customer.fromJson(json);
//            }
//
//            throw new IllegalStateException("Response does not contain customer data");
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to parse customer from response", e);
//        }
//    }
//
//    /**
//     * API methods that return raw responses, including status code and headers.
//     */
//    public class RawApi {
//        private final CustomerService_old service;
//
//        RawApi(CustomerService_old service) {
//            this.service = service;
//        }
//
//        /**
//         * Start building a customer creation request that will return a raw response.
//         */
//        public CustomerCreateBuilderRaw create() {
//            return new CustomerCreateBuilderRaw(this);
//        }
//
//        /**
//         * Create a customer and return a raw response object.
//         */
//        public ChargebeeApiResponse<Customer> create(CustomerCreateParams params) throws Exception {
//            Response response = service.createRaw(params);
//            Customer customer = service.parseCustomerFromResponse(response);
//            return new ChargebeeApiResponse<>(response, customer);
//        }
//
//        /**
//         * Create a customer asynchronously and return a raw response object.
//         */
//        public CompletableFuture<ChargebeeApiResponse<Customer>> createAsync(CustomerCreateParams params) {
//            return service.createRawAsync(params).thenApply(response -> {
//                Customer customer = service.parseCustomerFromResponse(response);
//                return new ChargebeeApiResponse<>(response, customer);
//            });
//        }
//
//        /**
//         * Start building a customer list request that will return a raw response.
//         */
//        public CustomerListBuilderRaw list() {
//            return new CustomerListBuilderRaw(this);
//        }
//
//        /**
//         * List customers and return a raw response object.
//         */
//        public ChargebeeApiResponse<CustomerList> list(CustomerListParams params) throws Exception {
//            Response response = service.listRaw(params);
//            CustomerList customerList = new CustomerList(service, params, response, false);
//            return new ChargebeeApiResponse<>(response, customerList);
//        }
//
//        /**
//         * List customers asynchronously and return a raw response object.
//         */
//        public CompletableFuture<ChargebeeApiResponse<CustomerList>> listAsync(CustomerListParams params) {
//            return service.listRawAsync(params).thenApply(response -> {
//                try {
//                    CustomerList customerList = new CustomerList(service, params, response, false);
//                    return new ChargebeeApiResponse<>(response, customerList);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        }
//    }
//}