# Migration Guide

This guide helps you upgrade between different versions of the Chargebee Java SDK.

## Migrating to v4 SDK (Beta)

The v4 SDK provides a modern, immutable client with enhanced type safety and improved developer experience.

### Key Differences

| Feature | v3 SDK | v4 SDK |
|---------|--------|--------|
| **Artifact** | `com.chargebee:chargebee-java` | `com.chargebee:chargebee-java` |
| **Version** | `3.x.x` | `4.x.x` |
| **Client** | `Environment.configure()` | `ChargebeeClient.builder()` |
| **Mutability** | Mutable builders | Immutable builders |
| **Configuration** | Global environment | Per-client configuration |
| **Async Support** | Limited | Full async/sync APIs |
| **Type Safety** | Basic | Enhanced with generics |

### Installation Changes

**v3 SDK:**
```xml
<dependency>
  <groupId>com.chargebee</groupId>
  <artifactId>chargebee-java</artifactId>
  <version>3.0.0</version>
</dependency>
```

**v4 SDK:**
```xml
<dependency>
  <groupId>com.chargebee</groupId>
  <artifactId>chargebee-java</artifactId>
  <version>4.0.0</version>
</dependency>
```

### Client Configuration

**v3 SDK:**
```java
import com.chargebee.Environment;

Environment.configure("your-site", "your-api-key");
```

**v4 SDK:**
```java
import com.chargebee.v4.client.ChargebeeClient;

ChargebeeClient client = ChargebeeClient.builder()
    .apiKey("your-api-key")
    .siteName("your-site")
    .build();
```

### Customer Operations

**v3 SDK:**
```java
import com.chargebee.models.Customer;
import com.chargebee.Result;

Result result = Customer.create()
    .firstName("John")
    .lastName("Doe")
    .email("john@example.com")
    .request();
Customer customer = result.customer();
```

**v4 SDK:**
```java
import com.chargebee.core.services.CustomerService;
import com.chargebee.core.models.customer.params.CustomerCreateParams;
import com.chargebee.core.responses.customer.CustomerCreateResponse;

CustomerService customers = client.customer();
CustomerCreateResponse response = customers.create(
    CustomerCreateParams.builder()
        .firstName("John")
        .lastName("Doe")
        .email("john@example.com")
        .build()
);
```

### Retry Configuration

**v3 SDK:**
```java
import com.chargebee.Environment;
import com.chargebee.v4.internal.RetryConfig;

Environment.configure("site", "key");
Environment.defaultConfig().updateRetryConfig(
    new RetryConfig(true, 3, 500, Set.of(500, 502))
);
```

**v4 SDK:**
```java
import com.chargebee.v4.internal.RetryConfig;

ChargebeeClient client = ChargebeeClient.builder()
    .apiKey("key")
    .siteName("site")
    .retry(
        RetryConfig.builder()
            .enabled(true)
            .maxRetries(3)
            .baseDelayMs(500)
            .build()
    )
    .build();
```

### Async Operations

**v3 SDK:**
```java
// Limited async support
CompletableFuture<Result> future = Customer.createAsync()
    .firstName("John")
    .request();
```

**v4 SDK:**
```java
import java.util.concurrent.CompletableFuture;

// Full async API
CompletableFuture<CustomerCreateResponse> future = customers.createAsync(
    CustomerCreateParams.builder()
        .firstName("John")
        .build()
);
```

## Migration Strategy

### Recommended Approach

1. **Start with a Test Environment**: Always test migrations in a non-production environment
2. **Gradual Migration**: Migrate one service at a time rather than the entire application
3. **Parallel Running**: Run both versions side-by-side during transition if needed
4. **Monitor**: Watch for any behavioral differences after migration

### Common Issues

1. **Import Changes**: Update import statements for the new SDK
2. **Configuration**: Move from global to per-client configuration
3. **Error Handling**: Update exception handling for new error types
4. **Async Patterns**: Adopt new async patterns if using async operations

### Testing Migration

Create a simple test to verify your migration:

```java
// Test both SDKs produce same results
@Test
public void testMigration() {
    // Legacy SDK call
    Environment.configure("test-site", "test-key");
    Result legacyResult = Customer.retrieve("customer-id").request();
    
    // Next Gen SDK call
    ChargebeeClient client = ChargebeeClient.builder()
        .siteName("test-site")
        .apiKey("test-key")
        .build();
    CustomerRetrieveResponse nextGenResult = client.customer()
        .retrieve("customer-id");
    
    // Compare results
    assertEquals(legacyResult.customer().id(), 
                 nextGenResult.customer().id());
}
```

## Support

If you encounter issues during migration:

1. Check the [API Documentation](https://apidocs.chargebee.com/docs/api/?lang=java)
2. Review the [changelog](../CHANGELOG.md) for breaking changes
3. Create an [issue](https://github.com/chargebee/chargebee-java/issues) if you find bugs
4. Join our [Discord server](https://discord.gg/S3SXDzXHAg) for community support
