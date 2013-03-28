package com.belkatechnologies.configeditor.listners.topbuttons;

import com.belkatechnologies.configeditor.gui.GUI;
import com.belkatechnologies.configeditor.model.Credentials;

import java.awt.event.ActionListener;
import java.io.File;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public abstract class IOXMLListener implements ActionListener {
    protected static final File CURRENT_DIRECTORY = new File("d:\\Work\\belkaDoc\\temp");

    protected static final String STAGING_SERVER = "109.234.154.82";
    protected static final String STAGING_PATH = "bor/config_test.xml";
    protected static final Credentials STAGING_CREDENTIALS = new Credentials("writer", "Jd324jdKadpfd");

    protected static final String PRODUCTION_SERVER = "188.93.22.210";
    protected static final String PRODUCTION_PATH = "config_test.xml";
    protected static final Credentials PRODUCTION_CREDENTIALS = new Credentials("writer", "GtPa45a91qWc");

    protected String getServer(boolean staging) {
        return staging ? STAGING_SERVER : PRODUCTION_SERVER;
    }

    protected String getPath(boolean staging) {
        return staging ? STAGING_PATH : PRODUCTION_PATH;
    }

    protected String getXMLURL(boolean staging) {
        return "http://" + (staging ? STAGING_SERVER + ":81/messages/" + STAGING_PATH :
                PRODUCTION_SERVER + ":81/BOR/" + PRODUCTION_PATH);
    }

    protected Credentials getCredentials(boolean staging) {
        return staging ? STAGING_CREDENTIALS : PRODUCTION_CREDENTIALS;
    }

    protected void notifyWithExceptionMessage(Exception e) {
        GUI.getInstance().notifyWithExceptionMessage("XML ERROR", e);
    }

    protected void notifyWithExceptionTrace(Exception e) {
        GUI.getInstance().notifyWithExceptionTrace("XML ERROR", e);
    }

    protected void showXMLErrorMessageDialog(String message) {
        GUI.getInstance().showErrorMessageDialog("XML ERROR", message);
    }
}
