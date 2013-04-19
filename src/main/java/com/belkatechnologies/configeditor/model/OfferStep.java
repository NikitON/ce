package com.belkatechnologies.configeditor.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
@Root(name = "step")
public class OfferStep {
    @Attribute
    private String level;
    @Element(required = false)
    private String description;
    @Element(required = false, name = "text")
    private String rewardText;
    @Element(required = false, name = "value")
    private String rewardValue;
    @Element(required = false, name = "type")
    private String rewardType;

    public OfferStep() {
    }

    public OfferStep(String level, String description, String rewardText, String rewardValue, String rewardType) {
        this.level = level;
        this.description = description.equals("") ? null : description;
        this.rewardText = rewardText.equals("") ? null : rewardText;
        this.rewardValue = rewardValue.equals("") ? null : rewardValue;
        this.rewardType = rewardType.equals("") ? null : rewardType;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRewardText() {
        return rewardText;
    }

    public void setRewardText(String rewardText) {
        this.rewardText = rewardText;
    }

    public String getRewardValue() {
        return rewardValue;
    }

    public void setRewardValue(String rewardValue) {
        this.rewardValue = rewardValue;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    @Override
    public String toString() {
        return level;
    }
}
