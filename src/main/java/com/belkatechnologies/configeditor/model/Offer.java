package com.belkatechnologies.configeditor.model;

import org.simpleframework.xml.*;

import java.util.List;

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
    private List<String> images;
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
    private List<OfferStep> steps;
    @Element
    private Targeting targeting;
    @Element
    private Checker checker;
    @ElementList(entry = "admin", required = false)
    private List<String> admins;
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
}
