package com.belkatechnologies.configeditor.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Targeting {
    @Element
    private String sex;
    @Element
    private String age;
    @Element
    private String countries;
    @Element(required = false)
    private String cities;
    @Element(required = false)
    private String groups;
}
