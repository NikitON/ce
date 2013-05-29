package com.belkatechnologies.configeditor.listeners.topbuttons;

import com.belkatechnologies.configeditor.gui.GUI;

import java.awt.event.ActionListener;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public abstract class IOXMLListener implements ActionListener {
    protected static final String STAGING_URL = "http://188.93.18.98/BOR-BIDS/updateConfig";
    protected static final String STAGING_XML_URL = "http://109.234.154.82:81/messages/bor/config.xml";
    protected static final String PRODUCTION_URL = "http://bor.belkatechnologies.com/BOR-BIDS/updateConfig";
    protected static final String PRODUCTION_XML_URL = "http://188.93.22.210:81/BOR/config.xml";

    protected String getUpdateURL(boolean staging) {
        return staging ? STAGING_URL : PRODUCTION_URL;
    }

    protected String getXMLURL(boolean staging) {
        return staging ? STAGING_XML_URL : PRODUCTION_XML_URL;
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
