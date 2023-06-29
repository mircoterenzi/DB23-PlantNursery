package it.unibo.nursery.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;

/** This class contains some useful methods used within the code. */
public final class Utils {

    private Utils() { }

    /**
     * Used to convert from {@code java.util.Date} to {@code java.sql.Date}.
     * @param sqlDate
     * @return a {@code java.util.Date}
     */
    public static java.util.Date sqlDateToDate(final java.sql.Date sqlDate) {
        return sqlDate == null ? null : new java.util.Date(sqlDate.getTime());
    }

    /**
     * Used to convert from {@code java.sql.Date} to {@code java.util.Date}.
     * @param date
     * @return a {@code java.sql.Date}
     */
    public static java.sql.Date dateToSqlDate(final java.util.Date date) {
        return date == null ? null : new java.sql.Date(date.getTime());
    }

    /**
     * Builds a date from a string.
     * @param dateString Date written in format dd/mm/yyyy
     * @return an {@link Optional} with a date if the conversion was successful, empty otherwise
     */
    public static Optional<java.util.Date> buildDate(final String dateString) {
        try {
            final String dateFormatString = "dd/mm/yyyy";
            final java.util.Date date = new SimpleDateFormat(dateFormatString, Locale.ITALIAN).parse(dateString);
            return Optional.of(date);
        } catch (final ParseException e) {
            return Optional.empty();
        }
    }

    /**
     * Builds a date from a string.
     * @param day
     * @param month
     * @param year
     * @return an {@link Optional} with a date if the conversion was successful, empty otherwise
     */
    public static Optional<java.util.Date> buildDate(final int day, final int month, final int year) {
        final String dateString = day + "/" + month + "/" + year;
        return buildDate(dateString);
    }
}
