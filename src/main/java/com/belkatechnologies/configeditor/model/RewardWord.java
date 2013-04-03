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
    @Element(required = false)
    private String form1;
    @Element(required = false)
    private String form2;
    @Element(required = false)
    private String form3;

    public RewardWord() {
    }

    public RewardWord(String id, String form1, String form2, String form3) {
        this.id = id;
        this.form1 = form1;
        this.form2 = form2;
        this.form3 = form3;
    }
}
