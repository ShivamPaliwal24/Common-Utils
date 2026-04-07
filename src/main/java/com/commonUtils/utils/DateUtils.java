package com.commonUtils.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Utility class for common Date and Time operations.
 * This class provides helper methods for date manipulation, formatting, and calculations.
 *
 * @author Your Name
 * @version 1.0.0
 */
public class DateUtils {

    private DateUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static final DateTimeFormatter ISO_DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;
    public static final DateTimeFormatter ISO_DATETIME_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public static final DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter SIMPLE_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Gets the current date as LocalDate.
     *
     * @return the current date
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * Gets the current date and time as LocalDateTime.
     *
     * @return the current date and time
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * Formats a LocalDate to string using the specified pattern.
     *
     * @param date the date to format
     * @param pattern the pattern to use
     * @return the formatted date string
     */
    public static String formatDate(LocalDate date, String pattern) {
        if (date == null) {
            return null;
        }
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Formats a LocalDateTime to string using the specified pattern.
     *
     * @param dateTime the datetime to format
     * @param pattern the pattern to use
     * @return the formatted datetime string
     */
    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Parses a string to LocalDate using the specified pattern.
     *
     * @param dateStr the date string
     * @param pattern the pattern to use
     * @return the parsed LocalDate
     */
    public static LocalDate parseDate(String dateStr, String pattern) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Parses a string to LocalDateTime using the specified pattern.
     *
     * @param dateTimeStr the datetime string
     * @param pattern the pattern to use
     * @return the parsed LocalDateTime
     */
    public static LocalDateTime parseDateTime(String dateTimeStr, String pattern) {
        if (StringUtils.isEmpty(dateTimeStr)) {
            return null;
        }
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Adds days to a date.
     *
     * @param date the date
     * @param days the number of days to add
     * @return the new date
     */
    public static LocalDate addDays(LocalDate date, long days) {
        if (date == null) {
            return null;
        }
        return date.plusDays(days);
    }

    /**
     * Adds months to a date.
     *
     * @param date the date
     * @param months the number of months to add
     * @return the new date
     */
    public static LocalDate addMonths(LocalDate date, long months) {
        if (date == null) {
            return null;
        }
        return date.plusMonths(months);
    }

    /**
     * Calculates the difference in days between two dates.
     *
     * @param startDate the start date
     * @param endDate the end date
     * @return the number of days between the dates
     */
    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * Checks if a date is in the past.
     *
     * @param date the date to check
     * @return true if the date is in the past
     */
    public static boolean isPast(LocalDate date) {
        return date != null && date.isBefore(LocalDate.now());
    }

    /**
     * Checks if a date is in the future.
     *
     * @param date the date to check
     * @return true if the date is in the future
     */
    public static boolean isFuture(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }

    /**
     * Converts LocalDateTime to milliseconds since epoch.
     *
     * @param dateTime the datetime to convert
     * @return the milliseconds since epoch
     */
    public static long toEpochMilli(LocalDateTime dateTime) {
        if (dateTime == null) {
            return 0;
        }
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * Converts milliseconds since epoch to LocalDateTime.
     *
     * @param epochMilli the milliseconds since epoch
     * @return the LocalDateTime
     */
    public static LocalDateTime fromEpochMilli(long epochMilli) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
    }
}
