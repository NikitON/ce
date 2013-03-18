package com.belkatechnologies.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public class ParsingUtil {
    public static int toInt(Object obj, int defVal) {
        try {
            String s = (String) obj;
            return toInt(s, defVal);
        } catch (ClassCastException e) {
        }
        return defVal;
    }

    public static int toInt(String s, int defVal) {
        try {
            if (s != null && s.length() > 0) {
                return Integer.parseInt(s);
            }
        } catch (Exception e) {
        }
        return defVal;
    }

    public static Date toDate(String s, String pattern, String defVal) {
        try {
            if (s != null && s.length() > 0) {
                return new SimpleDateFormat(pattern).parse(s);
            }
        } catch (Exception e) {
        }
        try {
            return new SimpleDateFormat(pattern).parse(defVal);
        } catch (Exception e) {
        }
        return new Date();
    }

    public static double toDouble(String s, double defVal) {
        try {
            if (s != null && s.length() > 0) {
                return Double.parseDouble(s);
            }
        } catch (Exception e) {
        }
        return defVal;
    }

    public static long toLong(Object obj, long defVal) {
        try {
            String s = (String) obj;
            return toLong(s, defVal);
        } catch (ClassCastException e) {
        }
        return defVal;
    }

    public static long toLong(String s, long defVal) {
        try {
            if (s != null && s.length() > 0) {
                return Long.parseLong(s);
            }
        } catch (Exception e) {
        }
        return defVal;
    }

    public static boolean toBool(String s, Boolean defVal) {
        try {
            if (s != null && s.length() > 0) {
                return Boolean.parseBoolean(s);
            }
        } catch (Exception e) {
        }
        return defVal;
    }
}
