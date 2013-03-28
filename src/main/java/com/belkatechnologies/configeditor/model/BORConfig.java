package com.belkatechnologies.configeditor.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Author: Nikita Khvorov
 * Date: 18.03.13
 */

@Root(name = "bor")
public class BORConfig {
    @ElementList(entry = "group")
    private ArrayList<EmailGroup> mailing;
    @ElementList
    private ArrayList<Application> apps;

    public ArrayList<Application> getApps() {
        return apps;
    }

    public Application getAppByID(String id) {
        for (Application app : apps) {
            if (app.getId().equals(id)) {
                return app;
            }
        }
        return null;
    }
}
