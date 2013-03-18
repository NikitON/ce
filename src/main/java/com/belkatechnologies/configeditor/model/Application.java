package com.belkatechnologies.configeditor.model;

import org.simpleframework.xml.*;

import java.util.List;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */

@Root
public class Application {
    @Attribute
    private String id;
    @Attribute(required = false)
    private String explicitRewards;
    @Element
    private String defaultRewardValue;
    @Element
    private String defaultRewardType;
    @ElementList(entry = "word")
    private List<RewardWord> words;
    @ElementList
    private List<Offer> offers;

    public List<Offer> getOffers() {
        return offers;
    }
}
