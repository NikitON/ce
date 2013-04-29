package com.belkatechnologies.configeditor.model;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.checkers.app.*;
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
public class Application implements Cloneable {
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
    @Element(required = false)
    private String oldUsersTable;
    @ElementList
    private ArrayList<Offer> offers;

    private static final List<Class<? extends InputChecker>> CHECKERS = initCheckers();

    private static List<Class<? extends InputChecker>> initCheckers() {
        List<Class<? extends InputChecker>> checkers = new ArrayList<Class<? extends InputChecker>>();
        checkers.add(IDChecker.class);
        checkers.add(ExplicitRewardsChecker.class);
        checkers.add(LinkChecker.class);
        checkers.add(DefaultRVChecker.class);
        checkers.add(DefaultRTChecker.class);
        checkers.add(WordsChecker.class);
        return checkers;
    }

    public static List<Class<? extends InputChecker>> getCheckers() {
        return CHECKERS;
    }

    public Application() {
    }

    public Application(String id, String explicitRewards, String link, String defaultRewardValue, String defaultRewardType, ArrayList<RewardWord> words, String oldUsersTable, ArrayList<Offer> offers) {
        this.id = id;
        this.explicitRewards = explicitRewards.equals("") ? null : explicitRewards;
        this.link = link.equals("") ? null : link;
        this.defaultRewardValue = defaultRewardValue;
        this.defaultRewardType = defaultRewardType;
        this.words = words;
        this.oldUsersTable = oldUsersTable.equals("") ? null : oldUsersTable;
        this.offers = offers == null ? new ArrayList<Offer>() : offers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExplicitRewards() {
        return explicitRewards;
    }

    public void setExplicitRewards(String explicitRewards) {
        this.explicitRewards = explicitRewards;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDefaultRewardValue() {
        return defaultRewardValue;
    }

    public void setDefaultRewardValue(String defaultRewardValue) {
        this.defaultRewardValue = defaultRewardValue;
    }

    public String getDefaultRewardType() {
        return defaultRewardType;
    }

    public void setDefaultRewardType(String defaultRewardType) {
        this.defaultRewardType = defaultRewardType;
    }

    public ArrayList<RewardWord> getWords() {
        return words;
    }

    public void setWords(ArrayList<RewardWord> words) {
        this.words = words;
    }

    public String getOldUsersTable() {
        return oldUsersTable;
    }

    public void setOldUsersTable(String oldUsersTable) {
        this.oldUsersTable = oldUsersTable;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    public Offer getOfferByID(String id) {
        for (Offer offer : offers) {
            if (offer.getId().equals(id)) {
                return offer;
            }
        }
        return null;
    }

    public boolean isActive() {
        for (Offer offer : offers) {
            if (offer.isActive()) {
                return true;
            }
        }
        return false;
    }

    public void moveOfferUp(Offer offer) {
        int index = offers.indexOf(offer);
        if (index != 0) {
            Offer overLying = offers.get(index - 1);
            offers.remove(index - 1);
            offers.add(index, overLying);
        }
    }

    public void moveOfferDown(Offer offer) {
        int index = offers.indexOf(offer);
        if (index != offers.size() - 1) {
            offers.remove(index);
            offers.add(index + 1, offer);
        }
    }

    @Override
    public String toString() {
        return id;
    }

    public void insertOffer(Offer offer) {
        offers.add(0, offer);
    }

    public boolean containsWord(String type) {
        for (RewardWord word : words) {
            if (word.toString().equals(type)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Application clone() throws CloneNotSupportedException {
        Application application = (Application) super.clone();
        application.id = id;
        application.explicitRewards = explicitRewards;
        application.link = link;
        application.defaultRewardValue = defaultRewardValue;
        application.defaultRewardType = defaultRewardType;
        application.words = new ArrayList<RewardWord>();
        for (RewardWord word : words) {
            application.words.add(word.clone());
        }
        application.oldUsersTable = oldUsersTable;
        if (offers != null) {
            application.offers = new ArrayList<Offer>();
            for (Offer offer : offers) {
                application.offers.add(offer.clone());
            }
        }
        return application;
    }
}
