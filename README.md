# Java Client Library for Chargebee API
***
This is the source code for the Java client library for [Chargebee APIs](https://apidocs.chargebee.com/docs/api?lang=java).

## Library versions

The versioning scheme of this library is inspired by [SemVer](https://semver.org/). For example, `v3.0.0.beta.1` and `v2.5.1` are valid library versions.

The following table provides some details for each major version:

| Library major version | Status        | Compatible API versions                                                                                               | **Branch**            |
|-----------------------|---------------|-----------------------------------------------------------------------------------------------------------------------|-----------------------|
| v3.x                  | Active (Beta) | [v2](https://apidocs.chargebee.com/docs/api/v2?lang=java) and [v1](https://apidocs.chargebee.com/docs/api/v1?lang=java) | `high-value-currency` |
| v2.x                  | Active        | [v2](https://apidocs.chargebee.com/docs/api/v2?lang=java) and [v1](https://apidocs.chargebee.com/docs/api/v1?lang=java)   | `master`              |
| v1.x                  | Inactive      | [v1](https://apidocs.chargebee.com/docs/api/v1?lang=java)                                                               | `chargebee-v1`        |

A couple of terms used in the above table are explained below:
- **Status**: The current development status for the library version. 
  - An **Active** major version is currently being maintained and continues to get backward-compatible changes. 
  - **Inactive** versions no longer receive any updates. 
  - **Beta** versions are pre-release versions and are subject to certain [terms and conditions](https://www.chargebee.com/company/terms/#your_rights).  
- **Branch**: The branch in this repository containing the source code for the latest release of the library version. Every version of the library has been [tagged](https://github.com/chargebee/chargebee-java/tags). You can check out the source code for any version using its tag.

**Note:** 
- See the [changelog](CHANGELOG.md) for a history of changes. 
- Follow the [Migration Guide](https://github.com/chargebee/chargebee-java/wiki/Migration-guide-for-v3) to upgrade from v2 to v3 (beta).

## Install the library
***

### Requirement
***
 * Java 1.8 or later.

### Install using Maven
***

You can install any release of beta library version by adding the below dependency to `pom.xml`:

```xml
<dependency>
  <groupId>com.chargebee</groupId>
  <artifactId>chargebee-java</artifactId>
  <version>[beta-version-number]</version>
</dependency>
```
For example, the following are valid:

- Install the latest beta version:
```xml
<dependency>
    <groupId>com.chargebee</groupId>
    <artifactId>chargebee-java</artifactId>
    <version>3.0.0-beta.1</version>
</dependency>
```

- Install a stable version, say v2.5.1:
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
For example, the following are valid:

```shell
git checkout v3.0.0-beta.1
```
or
```shell
git checkout v2.5.1
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
