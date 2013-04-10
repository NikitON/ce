package com.belkatechnologies.utils;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public class StringUtil {
    public static boolean isOkString(String string) {
        return (string != null && string.length() > 0);
    }

    public static String getSingular(String word) {
        return word.substring(0, word.length() - 1);
    }
}
