package com.belkatechnologies.configeditor.listeners.topbuttons;

import com.belkatechnologies.configeditor.logging.Logger;
import com.belkatechnologies.configeditor.managers.TreeManager;

import java.io.InputStream;

/**
 * Author: Nikita Khvorov
 * Date: 29.03.13
 */
public class OpenOldXMLListener extends OpenXMLListener {
    public OpenOldXMLListener() {
        super();
    }

    @Override
    protected void deserializeXML(InputStream inputStream) {
        try {
            TreeManager.getInstance().deserializeOldXML(inputStream);
        } catch (Exception e) {
            Logger.error("OPEN XML", e);
        }
    }
}
