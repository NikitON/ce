package com.belkatechnologies.configeditor.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
@Root(name = "word")
public class RewardWord {
    @Attribute
    private String id;
    @Element
    private String form1;
    @Element
    private String form2;
    @Element
    private String form3;
}
