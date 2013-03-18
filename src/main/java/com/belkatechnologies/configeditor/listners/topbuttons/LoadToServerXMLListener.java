package com.belkatechnologies.configeditor.listners.topbuttons;

import com.belkatechnologies.configeditor.managers.TreeManager;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Author: Nikita Khvorov
 * Date: 18.03.13
 */
public class LoadToServerXMLListener extends IOXMLListener {
    private String xmlURL;

    public LoadToServerXMLListener(boolean staging) {
        this.xmlURL = getXMLURL(staging);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            URL url = new URL(xmlURL);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            TreeManager.getInstance().serializeTree(connection.getOutputStream());
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
