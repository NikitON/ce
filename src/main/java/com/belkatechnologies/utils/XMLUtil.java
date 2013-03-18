package com.belkatechnologies.utils;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import java.io.ByteArrayOutputStream;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public class XMLUtil {
    public static String serialize(Object object, Format format) throws Exception {
        Serializer serializer = new Persister(format);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        serializer.write(object, baos);
        return baos.toString();
    }

    public static String serialize(Object object) throws Exception {
        Format format = new Format(0);
        return serialize(object, format);
    }

}
