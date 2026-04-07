package com.commonUtils.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the utilities library.
 * These properties can be configured in application.properties or application.yml.
 *
 * @author Your Name
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "common.utils")
public class UtilsProperties {

    /**
     * Enable or disable the utilities library.
     */
    private boolean enabled = true;

    /**
     * Custom configuration for JSON serialization.
     */
    private JsonConfig json = new JsonConfig();

    @Data
    public static class JsonConfig {
        /**
         * Enable pretty printing for JSON output.
         */
        private boolean prettyPrint = false;
    }
}
