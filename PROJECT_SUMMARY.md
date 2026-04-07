# Common Utils Library - Project Summary

## Overview

This is a reusable Spring Boot utilities library that can be published to Maven Central (Sonatype) and used as a dependency in other Java/Spring Boot projects.

## What's Included

### 1. Utility Classes

Located in `src/main/java/com/yourcompany/utils/`:

- **StringUtils.java** - String manipulation (isEmpty, capitalize, camelCase, truncate, mask, etc.)
- **DateUtils.java** - Date/time operations (formatting, parsing, calculations, conversions)
- **JsonUtils.java** - JSON serialization/deserialization using Jackson
- **CollectionUtils.java** - Collection operations (isEmpty, partition, intersection, difference, etc.)

### 2. Spring Boot Auto-Configuration

Located in `src/main/java/com/yourcompany/config/`:

- **UtilsAutoConfiguration.java** - Enables automatic setup when library is added as dependency
- **UtilsProperties.java** - Configuration properties for customization

### 3. Maven Configuration

- **pom.xml** - Complete Maven configuration with:
  - Spring Boot 3.2.0 dependencies
  - Sonatype publishing configuration
  - GPG signing for artifacts
  - Source and Javadoc JAR generation
  - Nexus staging plugin

### 4. Documentation

- **README.md** - Main documentation with usage examples
- **DEPLOYMENT_GUIDE.md** - Complete guide for publishing to Maven Central
- **QUICK_START.md** - Quick start guide for building and using the library
- **settings.xml.template** - Maven settings template for Sonatype credentials

### 5. Configuration Files

- **.gitignore** - Git ignore file for Java/Maven projects
- **src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports** - Spring Boot 3.x auto-configuration

## How to Use This Project

### Step 1: Customize the Project

1. Update `pom.xml`:
   - Change `<groupId>` from `com.yourcompany` to your namespace
   - Update `<url>`, `<scm>`, and `<developers>` sections
   - Modify `<version>` as needed

2. Rename packages:
   - Change `com.yourcompany` to your package name
   - Update all Java files to reflect the new package

### Step 2: Build the Library

```bash
# Clean and build
mvn clean install

# This creates:
# - target/common-utils-1.0.0.jar
# - target/common-utils-1.0.0-sources.jar
# - target/common-utils-1.0.0-javadoc.jar
```

### Step 3: Use Locally

After `mvn install`, the library is in your local Maven repository. Add to any project:

```xml
<dependency>
    <groupId>com.yourcompany</groupId>
    <artifactId>common-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Step 4: Publish to Maven Central (Optional)

Follow the **DEPLOYMENT_GUIDE.md** for detailed steps:

1. Create Sonatype account
2. Request namespace approval
3. Generate GPG keys
4. Configure Maven settings
5. Deploy to Sonatype
6. Release to Maven Central

## Project Structure

```
D:/DependencyCreator/
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
├── .gitignore
├── README.md
├── DEPLOYMENT_GUIDE.md
├── QUICK_START.md
├── PROJECT_SUMMARY.md
└── settings.xml.template
```

## Key Features

### 1. Spring Boot Integration
- Auto-configuration support
- Works seamlessly with Spring Boot 3.x
- Configurable via application.properties/yml

### 2. Ready for Maven Central
- Complete POM configuration
- GPG signing support
- Source and Javadoc generation
- Sonatype OSSRH integration

### 3. Production Ready
- Proper error handling
- Null-safe operations
- Well-documented code
- Following Java best practices

## Usage Examples

### StringUtils
```java
import com.yourcompany.utils.StringUtils;

String result = StringUtils.capitalize("hello");  // "Hello"
boolean empty = StringUtils.isEmpty(str);
String masked = StringUtils.maskString("1234567890", 4, '*');  // "******7890"
```

### DateUtils
```java
import com.yourcompany.utils.DateUtils;

LocalDate today = DateUtils.getCurrentDate();
LocalDate future = DateUtils.addDays(today, 7);
long days = DateUtils.daysBetween(start, end);
```

### JsonUtils
```java
import com.yourcompany.utils.JsonUtils;

String json = JsonUtils.toJson(myObject);
MyClass obj = JsonUtils.fromJson(jsonString, MyClass.class);
```

### CollectionUtils
```java
import com.yourcompany.utils.CollectionUtils;

boolean empty = CollectionUtils.isEmpty(list);
List<List<String>> partitions = CollectionUtils.partition(list, 10);
List<String> common = CollectionUtils.intersection(list1, list2);
```

## Next Steps

1. **Build the project**: Run `mvn clean install`
2. **Test locally**: Create a test Spring Boot app and add the dependency
3. **Customize**: Add your own utility methods
4. **Publish**: Follow deployment guide to publish to Maven Central

## Requirements

- Java 17+
- Maven 3.6+
- Spring Boot 3.2.0+ (for consuming applications)

## Support

For detailed instructions:
- Building and testing: See **QUICK_START.md**
- Publishing to Maven Central: See **DEPLOYMENT_GUIDE.md**
- Usage examples: See **README.md**

## License

Apache License 2.0
