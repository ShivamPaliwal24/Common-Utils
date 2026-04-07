package com.commonUtils.utils;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Utility class for common String operations.
 * This class provides helper methods for string manipulation, validation, and formatting.
 *
 * @author Your Name
 * @version 1.0.0
 */
public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Checks if a string is null or empty.
     *
     * @param str the string to check
     * @return true if the string is null or empty, false otherwise
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Checks if a string is null, empty, or contains only whitespace.
     *
     * @param str the string to check
     * @return true if the string is blank, false otherwise
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Checks if a string is not null and not empty.
     *
     * @param str the string to check
     * @return true if the string is not empty, false otherwise
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * Checks if a string is not blank.
     *
     * @param str the string to check
     * @return true if the string is not blank, false otherwise
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * Returns the default value if the string is null or empty.
     *
     * @param str the string to check
     * @param defaultValue the default value to return
     * @return the original string if not empty, otherwise the default value
     */
    public static String defaultIfEmpty(String str, String defaultValue) {
        return isEmpty(str) ? defaultValue : str;
    }

    /**
     * Capitalizes the first letter of a string.
     *
     * @param str the string to capitalize
     * @return the capitalized string, or null if input is null
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * Converts a string to camelCase.
     *
     * @param str the string to convert
     * @param delimiter the delimiter to split on
     * @return the camelCase string
     */
    public static String toCamelCase(String str, String delimiter) {
        if (isEmpty(str)) {
            return str;
        }
        String[] parts = str.split(delimiter);
        StringBuilder camelCase = new StringBuilder(parts[0].toLowerCase());
        for (int i = 1; i < parts.length; i++) {
            camelCase.append(capitalize(parts[i].toLowerCase()));
        }
        return camelCase.toString();
    }

    /**
     * Truncates a string to the specified length.
     *
     * @param str the string to truncate
     * @param maxLength the maximum length
     * @return the truncated string
     */
    public static String truncate(String str, int maxLength) {
        if (str == null || str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength);
    }

    /**
     * Truncates a string and adds ellipsis.
     *
     * @param str the string to truncate
     * @param maxLength the maximum length (including ellipsis)
     * @return the truncated string with ellipsis
     */
    public static String truncateWithEllipsis(String str, int maxLength) {
        if (str == null || str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }

    /**
     * Joins a collection of strings with a delimiter.
     *
     * @param collection the collection to join
     * @param delimiter the delimiter
     * @return the joined string
     */
    public static String join(Collection<String> collection, String delimiter) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        return String.join(delimiter, collection);
    }

    /**
     * Masks a string, showing only the last N characters.
     *
     * @param str the string to mask
     * @param visibleChars number of characters to show at the end
     * @param maskChar the character to use for masking
     * @return the masked string
     */
    public static String maskString(String str, int visibleChars, char maskChar) {
        if (isEmpty(str) || str.length() <= visibleChars) {
            return str;
        }
        int maskLength = str.length() - visibleChars;
        return String.valueOf(maskChar).repeat(maskLength) + str.substring(maskLength);
    }
}
