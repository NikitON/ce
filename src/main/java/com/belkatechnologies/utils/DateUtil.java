package com.belkatechnologies.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Nikita Khvorov
 * Date: 28.03.13
 */
public class DateUtil {
    private static final SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static Date getDate(String source) throws ParseException {
        return defaultFormat.parse(source);
    }

    public static Date getDate(String source, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(source);
    }

    public static String getString(long time) {
        return getString(new Date(time));
    }

    public static String getString(Date date) {
        return defaultFormat.format(date);
    }
}
