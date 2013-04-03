package com.belkatechnologies.configeditor.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Targeting {
    @Element(required = false)
    private String sex;
    @Element(required = false)
    private String age;
    @Element(required = false)
    private String countries;
    @Element(required = false)
    private String cities;
    @Element(required = false)
    private String groups;
    @Element(required = false)
    private String idEnds;

    public Targeting() {
    }

    public Targeting(String sex, String age, String countries, String cities, String groups, String idEnds) {
        this.sex = sex.equals("all") || sex.equals("") ? null : sex;
        this.age = age.equals("all") || age.equals("") ? null : age;
        this.countries = countries.equals("all") || countries.equals("") ? null : countries;
        this.cities = cities.equals("") ? null : cities;
        this.groups = groups.equals("") ? null : groups;
        this.idEnds = idEnds.equals("") ? null : idEnds;
    }

    public boolean isNull() {
        return sex == null && age == null && countries == null && cities == null && groups == null && idEnds == null;
    }
}
