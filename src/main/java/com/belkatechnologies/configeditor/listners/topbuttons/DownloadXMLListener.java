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
public class DownloadXMLListener extends IOXMLListener {
    private String xmlURL;

    public DownloadXMLListener(boolean staging) {
        this.xmlURL = getXMLURL(staging) + "?v=" + Math.random();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            URL url = new URL(xmlURL);
            URLConnection connection = url.openConnection();
            TreeManager.getInstance().deserializeXML(connection.getInputStream());
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
