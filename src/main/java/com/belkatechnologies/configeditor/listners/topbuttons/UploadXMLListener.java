package com.belkatechnologies.configeditor.listners.topbuttons;

import com.belkatechnologies.configeditor.gui.GUI;
import com.belkatechnologies.configeditor.managers.TreeManager;
import com.belkatechnologies.configeditor.model.Credentials;

import java.awt.event.ActionEvent;

/**
 * Author: Nikita Khvorov
 * Date: 18.03.13
 */
public class UploadXMLListener extends IOXMLListener {
    private String server;
    private String path;
    private Credentials credentials;

    public UploadXMLListener(boolean staging) {
        this.server = getServer(staging);
        this.path = getPath(staging);
        this.credentials = getCredentials(staging);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GUI.getInstance().runLoading("Uploading to " + server);
                uploadTree();
            }
        }).start();
    }

    private void uploadTree() {
        try {
            TreeManager.getInstance().uploadTree(server, path, credentials);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            GUI.getInstance().stopLoading();
        }
    }
}
