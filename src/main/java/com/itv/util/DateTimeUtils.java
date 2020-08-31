package com.itv.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDateTime parse(String ldtStr) {
        return LocalDateTime.parse(ldtStr, DATE_TIME_FORMATTER);
    }
}
