package com.isa.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class DateValidator {
    private static final String formatStr = "yyyy-MM-dd";

    public static String getFormatStr() {
        return formatStr;
    }

    public static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(formatStr);
    }

    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, getFormatter());
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private DateValidator() {
    }
}
