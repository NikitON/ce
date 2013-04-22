package com.belkatechnologies.configeditor.model;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.checkers.offer.*;
import com.belkatechnologies.utils.DateUtil;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */

@Root
public class Offer implements Cloneable {
    @Attribute
    private String id;
    @Element(required = false)
    private String incrementLevel;
    @Element(required = false)
    private String incrementLevelDateOffset;
    @Element(required = false)
    private String minLevel;
    @Element(required = false)
    private String newOnly;
    @Element
    private String targetURL;
    @Element(required = false)
    private String targetURLFormat;
    @Element(required = false)
    private String referralURL;
    @ElementList(entry = "image")
    private ArrayList<String> images;
    @Element(required = false)
    private String title;
    @Element(required = false)
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

    private static final List<Class<? extends InputChecker>> CHECKERS = initCheckers();

    private static List<Class<? extends InputChecker>> initCheckers() {
        List<Class<? extends InputChecker>> checkers = new ArrayList<>();
        checkers.add(IDChecker.class);
        checkers.add(IncLevelChecker.class);
        checkers.add(IncLevelDateOffset.class);
        checkers.add(MinLevelChecker.class);
        checkers.add(NewOnlyChecker.class);
        checkers.add(TargetURLChecker.class);
        checkers.add(ImagesChecker.class);
        checkers.add(PriceChecker.class);
        checkers.add(RewardInfoChecker.class);
        checkers.add(TargetingChecker.class);
        checkers.add(CheckerChecker.class);
        checkers.add(ShowLimitChecker.class);
        checkers.add(ClickLimitChecker.class);
        checkers.add(StartDateChecker.class);
        checkers.add(EndDateChecker.class);
        checkers.add(LengthChecker.class);
        checkers.add(SleepTimeChecker.class);
        return checkers;
    }

    public static List<Class<? extends InputChecker>> getCheckers() {
        return CHECKERS;
    }

    public Offer() {
    }

    public Offer(String id, String incrementLevel, String incrementLevelDateOffset, String minLevel, String newOnly, String targetURL, String targetURLFormat, String referralURL, ArrayList<String> images, String title, String price, String shortDescriptions, String description, String rewardText, ArrayList<OfferStep> steps, Targeting targeting, Checker checker, ArrayList<String> admins, String showLimit, String clickLimit, String startDate, String endDate, String length, String sleepTime, String extraParams, String gameSlogan) {
        this.id = id;
        this.incrementLevel = incrementLevel.equals("") ? null : incrementLevel;
        this.incrementLevelDateOffset = incrementLevelDateOffset.equals("") ? null : incrementLevelDateOffset;
        this.minLevel = minLevel.equals("") ? null : minLevel;
        this.newOnly = newOnly.equals("") ? null : newOnly;
        this.targetURL = targetURL;
        this.targetURLFormat = targetURLFormat.equals("") ? null : targetURLFormat;
        this.referralURL = referralURL.equals("") ? null : referralURL;
        this.images = images;
        this.title = title.equals("") ? null : title;
        this.price = price.equals("") ? null : price;
        this.shortDescriptions = shortDescriptions.equals("") ? null : shortDescriptions;
        this.description = description.equals("") ? null : description;
        this.rewardText = rewardText.equals("") ? null : rewardText;
        this.steps = steps;
        this.targeting = targeting == null || targeting.isNull() ? new Targeting() : targeting;
        this.checker = checker;
        this.admins = admins == null || admins.isEmpty() ? null : admins;
        this.showLimit = showLimit.equals("") ? null : showLimit;
        this.clickLimit = clickLimit.equals("") ? null : clickLimit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.length = length;
        this.sleepTime = sleepTime;
        this.extraParams = extraParams.equals("") ? null : extraParams;
        this.gameSlogan = gameSlogan.equals("") ? null : gameSlogan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncrementLevel() {
        return incrementLevel;
    }

    public void setIncrementLevel(String incrementLevel) {
        this.incrementLevel = incrementLevel;
    }

    public String getIncrementLevelDateOffset() {
        return incrementLevelDateOffset;
    }

    public void setIncrementLevelDateOffset(String incrementLevelDateOffset) {
        this.incrementLevelDateOffset = incrementLevelDateOffset;
    }

    public String getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(String minLevel) {
        this.minLevel = minLevel;
    }

    public String getNewOnly() {
        return newOnly;
    }

    public void setNewOnly(String newOnly) {
        this.newOnly = newOnly;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    public String getTargetURLFormat() {
        return targetURLFormat;
    }

    public void setTargetURLFormat(String targetURLFormat) {
        this.targetURLFormat = targetURLFormat;
    }

    public String getReferralURL() {
        return referralURL;
    }

    public void setReferralURL(String referralURL) {
        this.referralURL = referralURL;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShortDescriptions() {
        return shortDescriptions;
    }

    public void setShortDescriptions(String shortDescriptions) {
        this.shortDescriptions = shortDescriptions;
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

    public ArrayList<OfferStep> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<OfferStep> steps) {
        this.steps = steps;
    }

    public Targeting getTargeting() {
        return targeting;
    }

    public void setTargeting(Targeting targeting) {
        this.targeting = targeting;
    }

    public Checker getChecker() {
        return checker;
    }

    public void setChecker(Checker checker) {
        this.checker = checker;
    }

    public ArrayList<String> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<String> admins) {
        this.admins = admins;
    }

    public String getShowLimit() {
        return showLimit;
    }

    public void setShowLimit(String showLimit) {
        this.showLimit = showLimit;
    }

    public String getClickLimit() {
        return clickLimit;
    }

    public void setClickLimit(String clickLimit) {
        this.clickLimit = clickLimit;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getExtraParams() {
        return extraParams;
    }

    public void setExtraParams(String extraParams) {
        this.extraParams = extraParams;
    }

    public String getGameSlogan() {
        return gameSlogan;
    }

    public void setGameSlogan(String gameSlogan) {
        this.gameSlogan = gameSlogan;
    }

    public boolean isActive() {
        try {
            Date now = new Date();
            return DateUtil.getDate(startDate).before(now) && now.before(DateUtil.getDate(endDate));
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public Offer clone() throws CloneNotSupportedException {
        Offer offer = (Offer) super.clone();
        offer.id = id;
        offer.incrementLevel = incrementLevel;
        offer.incrementLevelDateOffset = incrementLevelDateOffset;
        offer.minLevel = minLevel;
        offer.newOnly = newOnly;
        offer.targetURL = targetURL;
        offer.targetURLFormat = targetURLFormat;
        offer.referralURL = referralURL;
        offer.images = new ArrayList<>();
        for (String image : images) {
            offer.images.add(image);
        }
        offer.title = title;
        offer.price = price;
        offer.shortDescriptions = shortDescriptions;
        offer.description = description;
        offer.rewardText = rewardText;
        offer.steps = new ArrayList<>();
        for (OfferStep step : steps) {
            offer.steps.add(step.clone());
        }
        offer.targeting = targeting.clone();
        offer.checker = checker.clone();
        if (admins != null) {
            offer.admins = new ArrayList<>();
            for (String admin : admins) {
                offer.admins.add(admin);
            }
        }
        offer.showLimit = showLimit;
        offer.clickLimit = clickLimit;
        offer.startDate = startDate;
        offer.endDate = endDate;
        offer.length = length;
        offer.sleepTime = sleepTime;
        offer.extraParams = extraParams;
        offer.gameSlogan = gameSlogan;
        return offer;
    }
}
