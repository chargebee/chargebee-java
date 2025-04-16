# Chargebee Java Client Library

This is the official Java library for integrating with Chargebee.

- 📘 For a complete reference of available APIs, check out our [API Documentation](https://apidocs.chargebee.com/docs/api/?lang=java).  
- 🧪 To explore and test API capabilities interactively, head over to our [API Explorer](https://api-explorer.chargebee.com).


## Library versions
***

The versioning scheme of this library is inspired by [SemVer](https://semver.org/) and the format is `v{MAJOR}.{MINOR}.{PATCH}`. For example, `v3.0.0` and `v2.5.1` are valid library versions.

The following table provides some details for each major version:

| Library major version | Status   | Compatible API versions                                                                                               | **Branch**        |
|----------------------------|----------|-----------------------------------------------------------------------------------------------------------------------|---------------|
| v3                         | Active   | [v2](https://apidocs.chargebee.com/docs/api/?lang=java) and [v1](https://apidocs.chargebee.com/docs/api/v1?lang=java) | `master`      |
| v2                         | Inactive | [v2](https://apidocs.chargebee.com/docs/api/?lang=java) and [v1](https://apidocs.chargebee.com/docs/api/v1?lang=java) | `chargebee-v2`|
| v1                         | Inactive | [v1](https://apidocs.chargebee.com/docs/api/v1?lang=java)                                                             | `chargebee-v1`|

A couple of terms used in the above table are explained below:
- **Status**: The current development status for the library version. An **Active** major version is currently being maintained and continues to get backward-compatible changes. **Inactive** versions no longer receive any updates.
- **Branch**: The branch in this repository containing the source code for the latest release of the library version. Every version of the library has been [tagged](https://github.com/chargebee/chargebee-java/tags). You can check out the source code for any version using its tag.

🔴 **Attention**: The support for v2 will eventually be discontinued on **December 31st 2023** and will no longer receive any further updates. We strongly recommend [upgrading to v3](https://github.com/chargebee/chargebee-java/wiki/Migration-guide-for-v3) as soon as possible.

**Note:** See the [changelog](CHANGELOG.md) for a history of changes.

## Install the library
***

### Requirement
***
* Java 1.8 or later.

### Install using Maven
***

You can install any release of an **active** library version by adding the below dependency to `pom.xml`:

```xml
<dependency>
  <groupId>com.chargebee</groupId>
  <artifactId>chargebee-java</artifactId>
  <version>{MAJOR}.{MINOR}.{PATCH}</version>
</dependency>
```

For example, the following are valid:

- Install the latest version:
```xml
<dependency>
  <groupId>com.chargebee</groupId>
  <artifactId>chargebee-java</artifactId>
  <version>3.0.0</version>
</dependency>
```

- Install an earlier version, say v2.5.1:
```xml
<dependency>
  <groupId>com.chargebee</groupId>
  <artifactId>chargebee-java</artifactId>
  <version>2.5.1</version>
</dependency>
```

### Install JAR files
***

#### Obtain pre-built JAR files
***

You can find the JAR files for the library in the [`dist`](dist) directory. To obtain the JAR files for a specific library version, check the `dist` directory within the source tree of the appropriate [tag](https://github.com/chargebee/chargebee-java/tags).

#### Build JAR files
***

Alternatively, you can build the JAR files by following the steps below:

1. Checkout the appropriate version of the library source code.
```shell
git checkout {tagname}
```
For example, the following is valid:
```shell
git checkout v3.0.0
```

2. Clean and build using Maven.
```shell
mvn clean package
```


## Use the library
***

### Create a subscription

```java
import java.io.IOException;
import com.chargebee.*;
import com.chargebee.models.*;
import com.chargebee.models.enums.*;

public class Sample{

  public static void main(String args[]) throws IOException{
    Environment.configure("{site}","{site_api_key}");
    Result result = Subscription.create()
                      .id("HwxfyiHNUFzaiWO")
                      .planId("starter")
                      .customerEmail("john@user.com")
                      .customerFirstName("John")
                      .customerLastName("Wayne").request();
    Subscription subscription = result.subscription();
    Customer customer = result.customer();
    Card card = result.card();
    System.out.println(result);
  }
}
```

### Create an idempotent request
[Idempotency keys](https://apidocs.chargebee.com/docs/api/idempotency?prod_cat_ver=2) are passed along with request headers to allow a safe retry of POST requests. 

```java
import com.chargebee.models.Card;
import com.chargebee.models.Contact;
import com.chargebee.models.Customer;
import com.chargebee.models.Subscription;
import org.json.JSONObject;

public class Sample {
    public static void main(String[] args) throws Exception {
        Environment.configure("{site}", "{site_api_key}");
        Result result = Customer.create()
                .firstName("John")
                .lastName("Dove")
                .email("john@test.com")
                .setIdempotencyKey("<<UUID>>") // Replace <<UUID>> with a unique string
                .request();
        Customer customer = result.customer();
        System.out.println(result.customer());
        Map<String, List<String>> responseHeaders = result.getResponseHeaders(); // Retrieves response headers
        System.out.println(responseHeaders);
        String idempotencyReplayedHeader = result.isIdempotencyReplayed();// Retrieves idempotency replayed header value 
        System.out.println(idempotencyReplayedHeader);
    }
}
```
`isIdempotencyReplayed()` method can be accessed to differentiate between original and replayed requests.

## Contribution
***
You may contribute patches to any of the **Active** versions of this library. To do so, raise a PR against the [respective branch](#library-versions). 

If you find something amiss, you are welcome to create an [issue](https://github.com/chargebee/chargebee-java/issues).

## API documentation
***

The API documentation for the Java library can be found in our [API reference](https://apidocs.chargebee.com/docs/api?lang=java).


## License
***

See the [LICENSE](LICENSE).
