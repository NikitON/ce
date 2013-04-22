package com.belkatechnologies.configeditor.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
@Root(name = "word")
public class RewardWord implements Cloneable {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getForm1() {
        return form1;
    }

    public void setForm1(String form1) {
        this.form1 = form1;
    }

    public String getForm2() {
        return form2;
    }

    public void setForm2(String form2) {
        this.form2 = form2;
    }

    public String getForm3() {
        return form3;
    }

    public void setForm3(String form3) {
        this.form3 = form3;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    protected RewardWord clone() throws CloneNotSupportedException {
        RewardWord rewardWord = (RewardWord) super.clone();
        rewardWord.id = id;
        rewardWord.form1 = form1;
        rewardWord.form2 = form2;
        rewardWord.form3 = form3;
        return rewardWord;
    }
}
