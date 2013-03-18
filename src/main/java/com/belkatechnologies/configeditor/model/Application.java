package com.belkatechnologies.configeditor.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */

@Root(name = "app")
public class Application {
    @Attribute
    private String id;
    @Attribute(required = false)
    private String explicitRewards;
    @Attribute(required = false)
    private String link;
    @Element
    private String defaultRewardValue;
    @Element
    private String defaultRewardType;
    @ElementList(entry = "word")
    private ArrayList<RewardWord> words;
    @ElementList
    private ArrayList<Offer> offers;

    public List<Offer> getOffers() {
        return offers;
    }

    public String getDefaultRewardValue() {
        return defaultRewardValue;
    }
}
