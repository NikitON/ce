package com.belkatechnologies.configeditor.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Author: Nikita Khvorov
 * Date: 18.03.13
 */

@Root(name = "group")
public class EmailGroup {
    @Attribute
    private String id;
    @ElementList(entry = "email")
    private ArrayList<String> emails;
}
