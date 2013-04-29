package com.belkatechnologies.configeditor.logging;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Element;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class Logger {
    private static org.apache.log4j.Logger getLogger(Class clazz) {
        return org.apache.log4j.Logger.getLogger(clazz);
    }

    private static org.apache.log4j.Logger getLogger(String name) {
        return org.apache.log4j.Logger.getLogger(name);
    }

    public static void initializeProperties(Element xmlElement) {
        DOMConfigurator.configure(xmlElement);
    }

    public static void initializeProperties(InputStream inputStream) {
        PropertyConfigurator.configure(inputStream);
    }

    public static void info(String message) {
        info(message, Logger.class);
    }

    public static void info(String message, Class clazz) {
        info(message, clazz.getName());
    }

    public static void info(String message, String loggerName) {
        getLogger(loggerName).info(message);
    }

    public static void error(Exception exception) {
        error(exception, Logger.class);
    }

    public static void error(Exception exception, Class clazz) {
        error(exception, clazz.getName());
    }

    public static void error(Exception exception, String loggerName) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);

        exception.printStackTrace(printWriter);

        getLogger(loggerName).error(writer.toString());
    }

    public static void error(String message) {
        error(message, Logger.class);
    }

    public static void error(String message, Class clazz) {
        error(message, clazz.getName());
    }

    public static void error(String message, String loggerName) {
        getLogger(loggerName).error(message);
    }

    public static void error(String message, Exception exception) {
        error(message);
        error(exception);
    }

    public static void error(String message, Exception exception, Class clazz) {
        error(message, clazz);
        error(exception, clazz);
    }

    public static void error(String message, Exception exception, String loggerName) {
        error(message, loggerName);
        error(exception, loggerName);
    }
}