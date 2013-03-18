package com.belkatechnologies.configeditor.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Author: Nikita Khvorov
 * Date: 14.01.13
 */

@Root
public class Checker {
    @Attribute
    private String strategy;
    @Element(required = false)
    private String checkUrl;
    @Element(required = false)
    private String statsUrl;
    @Element(required = false)
    private String params;
    @Attribute(required = false)
    private String wrappers;
    @Attribute(required = false)
    private String seed;
    @Attribute(required = false)
    private String interval;
    @Attribute(required = false)
    private String maxUsers;
}
