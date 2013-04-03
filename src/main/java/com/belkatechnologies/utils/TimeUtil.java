package com.belkatechnologies.utils;

/**
 * Author: Nikita Khvorov
 * Date: 03.04.13
 */
public class TimeUtil {
    public static final long SECOND_TO_MS = 1000;
    public static final long MINUTE_TO_MS = SECOND_TO_MS * 60;
    public static final long HOUR_TO_MS = MINUTE_TO_MS * 60;
    public static final long DAY_TO_MS = HOUR_TO_MS * 24;
    public static final long WEEK_TO_MS = DAY_TO_MS * 7;
    public static final long MONTH_TO_MS = DAY_TO_MS * 31;
}