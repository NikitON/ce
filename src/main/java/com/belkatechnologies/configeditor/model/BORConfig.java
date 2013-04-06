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
    @ElementList
    private ArrayList<EmailGroup> mailing;
    @ElementList
    private ArrayList<Application> apps;

    public BORConfig() {
    }

    public BORConfig(ArrayList<EmailGroup> mailing, ArrayList<Application> apps) {
        this.mailing = mailing;
        this.apps = apps;
    }

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

    @Override
    public String toString() {
        return "CONFIG";
    }

    public void moveAppDown(Application app) {
        int index = apps.indexOf(app);
        if (index != apps.size() - 1) {
            apps.remove(index);
            apps.add(index + 1, app);
        }
    }

    public void moveAppUp(Application app) {
        int index = apps.indexOf(app);
        if (index != 0) {
            Application overLying = apps.get(index - 1);
            apps.remove(index - 1);
            apps.add(index, overLying);
        }
    }
}
