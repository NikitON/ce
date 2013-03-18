package com.belkatechnologies.configeditor.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */

@Root
public class Offer {
    @Attribute
    private String id;
    @Element(required = false)
    private String incrementLevel;
    @Element(required = false)
    private String incrementLevelDateOffset;
    @Element(required = false)
    private String minLevel;
    @Element
    private String targetURL;
    @Element(required = false)
    private String targetURLFormat;
    @Element(required = false)
    private String referralURL;
    @ElementList(entry = "image")
    private ArrayList<String> images;
    @Element
    private String title;
    @Element
    private String price;
    @Element(required = false)
    private String shortDescriptions;
    @Element(required = false)
    private String description;
    @Element(required = false)
    private String rewardText;
    @ElementList
    private ArrayList<OfferStep> steps;
    @Element(required = false)
    private Targeting targeting;
    @Element
    private Checker checker;
    @ElementList(entry = "admin", required = false)
    private ArrayList<String> admins;
    @Element(required = false)
    private String showLimit;
    @Element(required = false)
    private String clickLimit;
    @Element
    private String startDate;
    @Element
    private String endDate;
    @Element
    private String length;
    @Element
    private String sleepTime;
    @Element(required = false)
    private String extraParams;
    @Element(required = false)
    private String gameSlogan;

    public String getId() {
        return id;
    }

    public String getIncrementLevel() {
        return incrementLevel;
    }

    public String getIncrementLevelDateOffset() {
        return incrementLevelDateOffset;
    }

    public String getMinLevel() {
        return minLevel;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public String getTargetURLFormat() {
        return targetURLFormat;
    }

    public String getReferralURL() {
        return referralURL;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getShortDescriptions() {
        return shortDescriptions;
    }

    public String getDescription() {
        return description;
    }

    public String getRewardText() {
        return rewardText;
    }

    public ArrayList<OfferStep> getSteps() {
        return steps;
    }

    public Targeting getTargeting() {
        return targeting;
    }

    public Checker getChecker() {
        return checker;
    }

    public ArrayList<String> getAdmins() {
        return admins;
    }

    public String getShowLimit() {
        return showLimit;
    }

    public String getClickLimit() {
        return clickLimit;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getLength() {
        return length;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public String getExtraParams() {
        return extraParams;
    }

    public String getGameSlogan() {
        return gameSlogan;
    }
}
