# Quick Start Guide

This guide will help you quickly get started with building and testing the Common Utils Library.

## Building the Library

### 1. Install Prerequisites

Ensure you have:
- **Java 17 or higher** installed
- **Maven 3.6+** installed

Verify installations:
```bash
java -version
mvn -version
```

### 2. Clone and Build

```bash
# Navigate to the project directory
cd D:/DependencyCreator

# Build the project
mvn clean install
```

This will:
- Compile the source code
- Generate the JAR file
- Generate sources JAR
- Generate Javadoc JAR
- Install the artifact to your local Maven repository (~/.m2/repository)

### 3. Verify the Build

Check that the following files are created in the `target/` directory:
- `common-utils-1.0.0.jar` - Main library JAR
- `common-utils-1.0.0-sources.jar` - Sources JAR
- `common-utils-1.0.0-javadoc.jar` - Javadoc JAR

## Using the Library in Another Project

### Option 1: Local Maven Repository (After `mvn install`)

Once you've run `mvn install`, the library is available in your local Maven repository. You can use it in any other project on your machine.

#### Create a new Spring Boot project:

1. **Create a new Spring Boot project** (or use existing one)

2. **Add dependency to pom.xml:**

```xml

<dependency>
    <groupId>com.commonUtilscom.commonUtils</groupId>
    <artifactId>common-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

3. **Use the utilities in your code:**

```java
package com.example.demo;

import com.commonUtils.utils.StringUtils;
import com.commonUtils.utils.DateUtils;
import com.commonUtils.utils.JsonUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Test StringUtils
        System.out.println("=== StringUtils Demo ===");
        String text = "hello world";
        System.out.println("Original: " + text);
        System.out.println("Capitalized: " + StringUtils.capitalize(text));
        System.out.println("CamelCase: " + StringUtils.toCamelCase("hello-world-example", "-"));
        System.out.println("Masked: " + StringUtils.maskString("1234567890", 4, '*'));

        // Test DateUtils
        System.out.println("\n=== DateUtils Demo ===");
        LocalDate today = DateUtils.getCurrentDate();
        System.out.println("Today: " + DateUtils.formatDate(today, "yyyy-MM-dd"));
        LocalDate future = DateUtils.addDays(today, 7);
        System.out.println("Week later: " + DateUtils.formatDate(future, "yyyy-MM-dd"));
        System.out.println("Days between: " + DateUtils.daysBetween(today, future));

        // Test JsonUtils
        System.out.println("\n=== JsonUtils Demo ===");
        Map<String, Object> data = new HashMap<>();
        data.put("name", "John Doe");
        data.put("age", 30);
        data.put("date", today);
        String json = JsonUtils.toPrettyJson(data);
        System.out.println("JSON Output:\n" + json);
    }
}
```

4. **Run your application:**
```bash
mvn spring-boot:run
```

You should see the utilities in action!

### Option 2: Direct JAR Reference (Without Maven Central)

If you want to share the library with others before publishing to Maven Central:

1. **Build the library:**
```bash
cd D:/DependencyCreator
mvn clean package
```

2. **Copy the JAR to your project:**
```bash
# Copy to another project's lib folder
mkdir -p /path/to/your/project/lib
cp target/common-utils-1.0.0.jar /path/to/your/project/lib/
```

3. **Add as system dependency in pom.xml:**

```xml

<dependency>
    <groupId>com.commonUtilscom.commonUtils</groupId>
    <artifactId>common-utils</artifactId>
    <version>1.0.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/lib/common-utils-1.0.0.jar</systemPath>
</dependency>
```

## Testing the Library

### Running Tests

```bash
# Run all tests
mvn test

# Run tests with coverage
mvn clean test jacoco:report
```

### Creating a Simple Test

You can create tests to verify the utilities work correctly. Example test:

```java
import com.commonUtils.utils.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testCapitalize() {
        assertEquals("Hello", StringUtils.capitalize("hello"));
        assertEquals("World", StringUtils.capitalize("world"));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty("test"));
    }
}
```

## Next Steps

1. **Customize the library** - Modify the groupId, artifactId, and package names to match your organization
2. **Add more utilities** - Extend the existing classes or add new utility classes
3. **Write tests** - Add comprehensive tests for all utilities
4. **Publish to Maven Central** - Follow the DEPLOYMENT_GUIDE.md to publish your library

## Common Commands

```bash
# Clean and build
mvn clean install

# Build without tests
mvn clean install -DskipTests

# Generate Javadoc
mvn javadoc:javadoc

# Package for distribution
mvn clean package

# Deploy to remote repository
mvn clean deploy
```

## Troubleshooting

### Issue: Build fails with Java version error
**Solution:** Ensure Java 17 or higher is installed and JAVA_HOME is set correctly

### Issue: Dependencies not downloading
**Solution:** Check your internet connection and Maven settings. Try `mvn clean install -U` to force update

### Issue: Auto-configuration not working in Spring Boot
**Solution:** Ensure the library is on the classpath and Spring Boot version is 3.0+

## Configuration in Your Application

After adding the library, you can configure it in your `application.properties`:

```properties
# Enable/disable the library
common.utils.enabled=true

# Configure JSON pretty printing
common.utils.json.pretty-print=true
```

Or in `application.yml`:

```yaml
common:
  utils:
    enabled: true
    json:
      pretty-print: true
```
