package com.belkatechnologies.configeditor.listeners.topbuttons;

import com.belkatechnologies.configeditor.gui.GUI;
import com.belkatechnologies.configeditor.logging.Logger;
import com.belkatechnologies.configeditor.managers.TreeManager;

import java.awt.event.ActionEvent;

/**
 * Author: Nikita Khvorov
 * Date: 18.03.13
 */
public class UploadXMLListener extends IOXMLListener {
    private String updateURL;

    public UploadXMLListener(boolean staging) {
        this.updateURL = getUpdateURL(staging);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GUI.getInstance().runLoading("Uploading to " + updateURL);
                uploadTree();
            }
        }).start();
    }

    private void uploadTree() {
        try {
            TreeManager.getInstance().uploadTree(updateURL);
        } catch (Exception e) {
            Logger.error("UPLOAD EXCEPTION", e);
        } finally {
            GUI.getInstance().stopLoading();
        }
    }
}
