package com.belkatechnologies.configeditor.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Targeting {
    @Element(required = false)
    private String sex;
    @Element(required = false)
    private String age;
    @Element(required = false)
    private String countries;
    @Element(required = false)
    private String cities;
    @Element(required = false)
    private String groups;
}
