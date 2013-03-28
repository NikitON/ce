package com.belkatechnologies.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Nikita Khvorov
 * Date: 28.03.13
 */
public class DateUtil {
    public static Date getDate(String source) throws ParseException {
        return getDate(source, "dd.MM.yyyy HH:mm");
    }

    public static Date getDate(String source, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(source);
    }
}
