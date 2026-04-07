package io.github.shivampaliwal24.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Utility class for JSON operations.
 * This class provides helper methods for JSON serialization and deserialization.
 *
 * @author Shivam Paliwal
 * @version 1.0.0
 */
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    private JsonUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Converts an object to JSON string.
     *
     * @param object the object to convert
     * @return the JSON string
     * @throws RuntimeException if conversion fails
     */
    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    /**
     * Converts an object to pretty-printed JSON string.
     *
     * @param object the object to convert
     * @return the pretty-printed JSON string
     * @throws RuntimeException if conversion fails
     */
    public static String toPrettyJson(Object object) {
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    /**
     * Converts a JSON string to an object of the specified class.
     *
     * @param json the JSON string
     * @param clazz the target class
     * @param <T> the type of the object
     * @return the deserialized object
     * @throws RuntimeException if conversion fails
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON to object", e);
        }
    }

    /**
     * Converts a JSON string to an object using TypeReference.
     *
     * @param json the JSON string
     * @param typeReference the type reference
     * @param <T> the type of the object
     * @return the deserialized object
     * @throws RuntimeException if conversion fails
     */
    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON to object", e);
        }
    }

    /**
     * Clones an object by serializing and deserializing it.
     *
     * @param object the object to clone
     * @param clazz the class of the object
     * @param <T> the type of the object
     * @return the cloned object
     * @throws RuntimeException if cloning fails
     */
    public static <T> T clone(T object, Class<T> clazz) {
        String json = toJson(object);
        return fromJson(json, clazz);
    }

    /**
     * Gets the ObjectMapper instance used by this utility.
     *
     * @return the ObjectMapper instance
     */
    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}
