package com.belkatechnologies.configeditor.listeners.topbuttons;

import com.belkatechnologies.configeditor.gui.GUI;
import com.belkatechnologies.configeditor.managers.TreeManager;

import java.awt.event.ActionEvent;

/**
 * Author: Nikita Khvorov
 * Date: 18.03.13
 */
public class SaveXMLListener extends IOXMLListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GUI.getInstance().runLoading("Saving file");
                    TreeManager.getInstance().serializeOpenedTree();
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    GUI.getInstance().stopLoading();
                }
            }
        }).start();
    }
}
