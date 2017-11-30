package com.mzherdev.restchooser.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static <T extends Comparable<? super T>> boolean isBetween(T value, T start, T end) {
        return value.compareTo(start) >= 0 && value.compareTo(end) <= 0;
    }

    public static LocalDate parseLocalDate(String str) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }

    public static LocalTime parseLocalTime(String str) {
        return StringUtils.isEmpty(str) ? null : LocalTime.parse(str);
    }

    public static boolean canChangeVote(LocalDateTime oldDateTime, LocalDateTime newDateTime) {
        return (oldDateTime.toLocalDate().compareTo(newDateTime.toLocalDate()) <= 0
                && (oldDateTime.toLocalTime().compareTo(LocalTime.of(11, 0, 0)) < 0));
    }
}