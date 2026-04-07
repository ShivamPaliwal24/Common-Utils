package com.commonUtils.utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utility class for common Collection operations.
 * This class provides helper methods for collection manipulation and validation.
 *
 * @author Your Name
 * @version 1.0.0
 */
public class CollectionUtils {

    private CollectionUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Checks if a collection is null or empty.
     *
     * @param collection the collection to check
     * @return true if the collection is null or empty, false otherwise
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Checks if a collection is not null and not empty.
     *
     * @param collection the collection to check
     * @return true if the collection is not empty, false otherwise
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * Checks if a map is null or empty.
     *
     * @param map the map to check
     * @return true if the map is null or empty, false otherwise
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * Checks if a map is not null and not empty.
     *
     * @param map the map to check
     * @return true if the map is not empty, false otherwise
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * Gets the first element from a collection.
     *
     * @param collection the collection
     * @param <T> the type of elements
     * @return the first element, or null if the collection is empty
     */
    public static <T> T getFirst(Collection<T> collection) {
        if (isEmpty(collection)) {
            return null;
        }
        return collection.iterator().next();
    }

    /**
     * Gets the last element from a list.
     *
     * @param list the list
     * @param <T> the type of elements
     * @return the last element, or null if the list is empty
     */
    public static <T> T getLast(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    /**
     * Creates a safe copy of a collection to avoid null pointer exceptions.
     *
     * @param collection the collection to copy
     * @param <T> the type of elements
     * @return a new list containing the elements, or an empty list if input is null
     */
    public static <T> List<T> safeCopy(Collection<T> collection) {
        if (collection == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(collection);
    }

    /**
     * Partitions a list into smaller lists of the specified size.
     *
     * @param list the list to partition
     * @param size the size of each partition
     * @param <T> the type of elements
     * @return a list of partitioned lists
     */
    public static <T> List<List<T>> partition(List<T> list, int size) {
        if (isEmpty(list) || size <= 0) {
            return Collections.emptyList();
        }
        List<List<T>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            partitions.add(list.subList(i, Math.min(i + size, list.size())));
        }
        return partitions;
    }

    /**
     * Converts a list to a map using a key extractor function.
     *
     * @param list the list to convert
     * @param keyExtractor the function to extract keys
     * @param <K> the key type
     * @param <V> the value type
     * @return a map with extracted keys and original values
     */
    public static <K, V> Map<K, V> toMap(List<V> list, Function<V, K> keyExtractor) {
        if (isEmpty(list)) {
            return Collections.emptyMap();
        }
        return list.stream().collect(Collectors.toMap(keyExtractor, Function.identity()));
    }

    /**
     * Finds the intersection of two collections.
     *
     * @param collection1 the first collection
     * @param collection2 the second collection
     * @param <T> the type of elements
     * @return a new list containing common elements
     */
    public static <T> List<T> intersection(Collection<T> collection1, Collection<T> collection2) {
        if (isEmpty(collection1) || isEmpty(collection2)) {
            return new ArrayList<>();
        }
        return collection1.stream()
                .filter(collection2::contains)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Finds the difference between two collections (elements in collection1 but not in collection2).
     *
     * @param collection1 the first collection
     * @param collection2 the second collection
     * @param <T> the type of elements
     * @return a new list containing the difference
     */
    public static <T> List<T> difference(Collection<T> collection1, Collection<T> collection2) {
        if (isEmpty(collection1)) {
            return new ArrayList<>();
        }
        if (isEmpty(collection2)) {
            return new ArrayList<>(collection1);
        }
        return collection1.stream()
                .filter(item -> !collection2.contains(item))
                .collect(Collectors.toList());
    }
}
