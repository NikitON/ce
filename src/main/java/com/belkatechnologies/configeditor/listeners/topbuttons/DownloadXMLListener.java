package com.belkatechnologies.configeditor.listeners.topbuttons;

import com.belkatechnologies.configeditor.gui.GUI;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Author: Nikita Khvorov
 * Date: 18.03.13
 */
public class DownloadXMLListener extends OpenXMLListener {
    private String xmlURL;

    public DownloadXMLListener(boolean staging) {
        this.xmlURL = getXMLURL(staging) + "?v=" + Math.random();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GUI.getInstance().runLoading("Downloading from server");
                    URL url = new URL(xmlURL);
                    URLConnection connection = url.openConnection();
                    deserializeXML(connection.getInputStream());
                    GUI.getInstance().enableButtons();
                } catch (IOException e1) {
                    GUI.getInstance().showErrorMessageDialog("Connection failed", "Try again.");
                } finally {
                    GUI.getInstance().stopLoading();
                }
            }
        }).start();
    }
}
