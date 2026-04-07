package io.github.shivampaliwal24.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * Auto-configuration class for the utilities library.
 * This enables automatic configuration when the library is added as a dependency.
 *
 * @author Shivam Paliwal
 * @version 1.0.0
 */
@AutoConfiguration
@ComponentScan(basePackages = "io.github.shivampaliwal24")
@EnableConfigurationProperties(UtilsProperties.class)
public class UtilsAutoConfiguration {

    public UtilsAutoConfiguration() {
        // This constructor is called when Spring Boot loads the auto-configuration
        System.out.println("Common Utils Library Auto-Configuration loaded successfully!");
    }
}
