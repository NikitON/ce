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
    @Element(required = false)
    private String oldUsersTable;
    @ElementList
    private ArrayList<Offer> offers;

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
        this.offers = offers;
    }

    public List<Offer> getOffers() {
        return offers;
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

    public String getDefaultRewardValue() {
        return defaultRewardValue;
    }

    public String getId() {
        return id;
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
}
