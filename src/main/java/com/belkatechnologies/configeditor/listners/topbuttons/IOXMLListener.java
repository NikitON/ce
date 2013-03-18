package com.belkatechnologies.configeditor.listners.topbuttons;

import java.awt.event.ActionListener;
import java.io.File;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public abstract class IOXMLListener implements ActionListener {
    protected static final File CURRENT_DIRECTORY = new File("d:\\Work\\belkaDoc\\temp");
    protected static final String STAGING_XML_URL = "http://109.234.154.82:81/messages/bor/config_test.xml";
    protected static final String PRODUCTION_XML_URL = "http://188.93.22.210:81/BOR/config.xml";

    protected String getXMLURL(boolean staging) {
        return staging ? STAGING_XML_URL : PRODUCTION_XML_URL;
    }
}
