package com.belkatechnologies.configeditor.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Targeting implements Cloneable {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getIdEnds() {
        return idEnds;
    }

    public void setIdEnds(String idEnds) {
        this.idEnds = idEnds;
    }

    public boolean isNull() {
        return sex == null && age == null && countries == null && cities == null && groups == null && idEnds == null;
    }

    @Override
    public String toString() {
        if (!isNull()) {
            StringBuilder sb = new StringBuilder("[");
            if (sex != null) sb.append("sex=\"").append(sex).append("\", ");
            if (age != null) sb.append("age=\"").append(age).append("\", ");
            if (countries != null) sb.append("countries=\"").append(countries).append("\", ");
            if (cities != null) sb.append("cities=\"").append(cities).append("\", ");
            if (groups != null) sb.append("groups=\"").append(groups).append("\", ");
            if (idEnds != null) sb.append("idEnds=\"").append(idEnds).append("\", ");
            int end = sb.lastIndexOf(", ");
            return sb.substring(0, end) + "]";
        }
        return "ALL";
    }

    @Override
    protected Targeting clone() throws CloneNotSupportedException {
        Targeting targeting = (Targeting) super.clone();
        targeting.sex = sex;
        targeting.age = age;
        targeting.countries = countries;
        targeting.cities = cities;
        targeting.groups = groups;
        targeting.idEnds = idEnds;
        return targeting;
    }
}
