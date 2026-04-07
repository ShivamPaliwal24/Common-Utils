# Common Utils Library

A reusable Spring Boot utilities library that provides common helper methods for String, Date, JSON, and Collection operations. This library can be published to Maven Central via Sonatype and used as a dependency in any Java/Spring Boot project.

## Features

- **StringUtils**: Common string manipulation operations (isEmpty, isBlank, capitalize, camelCase, truncate, mask, etc.)
- **DateUtils**: Date and time operations (formatting, parsing, calculations, conversions)
- **JsonUtils**: JSON serialization and deserialization using Jackson
- **CollectionUtils**: Collection operations (isEmpty, partition, intersection, difference, etc.)
- **Spring Boot Auto-Configuration**: Automatic setup when added as a dependency

## Requirements

- Java 17 or higher
- Spring Boot 3.2.0 or higher
- Maven 3.6+ (for building)

## Installation

### Add as Maven Dependency

Once published to Maven Central, add this dependency to your `pom.xml`:

```xml

<dependency>
    <groupId>com.commonUtilscom.commonUtils</groupId>
    <artifactId>common-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Add as Gradle Dependency

For Gradle projects, add to your `build.gradle`:

```gradle
implementation 'com.commonUtils:common-utils:1.0.0'
```

## Usage Examples

### StringUtils

```java
import com.commonUtils.utils.StringUtils;

// Check if string is empty
boolean isEmpty = StringUtils.isEmpty(str);

        // Capitalize first letter
        String capitalized = StringUtils.capitalize("hello"); // Returns "Hello"

        // Convert to camelCase
        String camelCase = StringUtils.toCamelCase("hello-world", "-"); // Returns "helloWorld"

        // Truncate with ellipsis
        String truncated = StringUtils.truncateWithEllipsis("Long text here", 10); // Returns "Long te..."

        // Mask sensitive data
        String masked = StringUtils.maskString("1234567890", 4, '*'); // Returns "******7890"
```

### DateUtils

```java
import com.commonUtils.utils.DateUtils;

import java.time.LocalDate;

// Get current date
LocalDate today = DateUtils.getCurrentDate();

        // Format date
        String formatted = DateUtils.formatDate(today, "yyyy-MM-dd");

        // Parse date
        LocalDate parsed = DateUtils.parseDate("2024-01-15", "yyyy-MM-dd");

        // Add days
        LocalDate future = DateUtils.addDays(today, 7);

        // Calculate days between dates
        long days = DateUtils.daysBetween(startDate, endDate);

        // Check if date is in the past
        boolean isPast = DateUtils.isPast(someDate);
```

### JsonUtils

```java
import com.commonUtils.utils.JsonUtils;

// Convert object to JSON
String json = JsonUtils.toJson(myObject);

        // Convert to pretty JSON
        String prettyJson = JsonUtils.toPrettyJson(myObject);

        // Parse JSON to object
        MyClass obj = JsonUtils.fromJson(jsonString, MyClass.class);

        // Clone an object
        MyClass cloned = JsonUtils.clone(original, MyClass.class);
```

### CollectionUtils

```java
import com.commonUtils.utils.CollectionUtils;

// Check if collection is empty
boolean isEmpty = CollectionUtils.isEmpty(myList);

        // Get first element safely
        String first = CollectionUtils.getFirst(myList);

        // Partition a list
        List<List<String>> partitions = CollectionUtils.partition(largeList, 10);

        // Find intersection
        List<String> common = CollectionUtils.intersection(list1, list2);

        // Convert list to map
        Map<Long, User> userMap = CollectionUtils.toMap(userList, User::getId);
```

## Configuration

You can configure the library in your `application.properties` or `application.yml`:

```properties
# Enable/disable the library
common.utils.enabled=true

# Enable pretty printing for JSON
common.utils.json.pretty-print=false
```

4. **Configure Maven settings**: Add credentials to `~/.m2/settings.xml`

### Step 1: Generate GPG Keys

```bash
# Generate a GPG key pair
gpg --gen-key

# List your keys
gpg --list-keys

# Publish your public key to a key server
gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID
```

### Step 2: Configure Maven Settings

Create or edit `~/.m2/settings.xml`:

```xml
<settings>
    <servers>
        <server>
            <id>ossrh</id>
            <username>YOUR_SONATYPE_USERNAME</username>
            <password>YOUR_SONATYPE_PASSWORD</password>
        </server>
    </servers>
    <profiles>
        <profile>
            <id>ossrh</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <gpg.executable>gpg</gpg.executable>
                <gpg.passphrase>YOUR_GPG_PASSPHRASE</gpg.passphrase>
            </properties>
        </profile>
    </profiles>
</settings>
```

### Step 3: Update Project Information

Before publishing, update these fields in `pom.xml`:

- `<groupId>`: Change to your approved namespace (e.g., `com.commonUtils`)
- `<url>`: Change to your project's GitHub URL
- `<scm>`: Update with your repository information
- `<developers>`: Add your information

### Step 4: Deploy to Sonatype

```bash
# For snapshot versions
mvn clean deploy

# For release versions
mvn clean deploy -P release

# Or use the Maven release plugin
mvn release:prepare
mvn release:perform
```

### Step 5: Release on Sonatype

1. Log in to https://s01.oss.sonatype.org/
2. Go to "Staging Repositories"
3. Find your staged repository
4. Click "Close" and wait for validation
5. Click "Release" to publish to Maven Central

## Project Structure

```
common-utils/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/yourcompany/
│   │   │       ├── config/
│   │   │       │   ├── UtilsAutoConfiguration.java
│   │   │       │   └── UtilsProperties.java
│   │   │       └── utils/
│   │   │           ├── StringUtils.java
│   │   │           ├── DateUtils.java
│   │   │           ├── JsonUtils.java
│   │   │           └── CollectionUtils.java
│   │   └── resources/
│   │       └── META-INF/
│   │           └── spring/
│   │               └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
├── pom.xml
└── README.md
```

## License

This project is licensed under the Apache License 2.0 - see the LICENSE file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

For issues or questions, please open an issue on GitHub.

## Changelog

### Version 1.0.0
- Initial release
- StringUtils with common string operations
- DateUtils with date/time operations
- JsonUtils with JSON serialization
- CollectionUtils with collection operations
- Spring Boot auto-configuration support

## Building the Project

To build the library locally:

```bash
mvn clean install
```

## Publishing to Maven Central (Sonatype)

### Prerequisites

Before publishing, you need to:

1. **Create a Sonatype account**: Sign up at https://issues.sonatype.org/
2. **Create a JIRA ticket**: Request a new project namespace (e.g., com.commonUtils)
3. **Generate GPG keys**: For signing artifacts
