package com.belkatechnologies.configeditor.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Author: Nikita Khvorov
 * Date: 14.01.13
 */

@Root
public class Checker implements Cloneable {
    @Attribute
    private String strategy;
    @Element(required = false)
    private String checkUrl;
    @Element(required = false)
    private String statsUrl;
    @Element(required = false)
    private String params;
    @Attribute(required = false)
    private String wrappers;
    @Attribute(required = false)
    private String seed;
    @Attribute(required = false)
    private String interval;
    @Attribute(required = false)
    private String maxUsers;

    public Checker() {
    }

    public Checker(String strategy, String checkUrl, String statsUrl, String params, String wrappers, String seed, String interval, String maxUsers) {
        this.strategy = strategy;
        this.checkUrl = checkUrl.equals("") ? null : checkUrl;
        this.statsUrl = statsUrl.equals("") ? null : statsUrl;
        this.params = params.equals("") ? null : params;
        this.wrappers = wrappers.equals("") ? null : wrappers;
        this.seed = seed.equals("") ? null : seed;
        this.interval = interval.equals("") ? null : interval;
        this.maxUsers = maxUsers.equals("") ? null : maxUsers;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getCheckUrl() {
        return checkUrl;
    }

    public void setCheckUrl(String checkUrl) {
        this.checkUrl = checkUrl;
    }

    public String getStatsUrl() {
        return statsUrl;
    }

    public void setStatsUrl(String statsUrl) {
        this.statsUrl = statsUrl;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getWrappers() {
        return wrappers;
    }

    public void setWrappers(String wrappers) {
        this.wrappers = wrappers;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(String maxUsers) {
        this.maxUsers = maxUsers;
    }

    @Override
    public String toString() {
        return strategy;
    }

    @Override
    protected Checker clone() throws CloneNotSupportedException {
        Checker checker = (Checker) super.clone();
        checker.strategy = strategy;
        checker.checkUrl = checkUrl;
        checker.statsUrl = statsUrl;
        checker.params = params;
        checker.wrappers = wrappers;
        checker.seed = seed;
        checker.interval = interval;
        checker.maxUsers = maxUsers;
        return checker;
    }
}
