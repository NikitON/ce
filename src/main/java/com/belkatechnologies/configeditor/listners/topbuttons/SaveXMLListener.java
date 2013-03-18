package com.belkatechnologies.configeditor.listners.topbuttons;

import com.belkatechnologies.configeditor.managers.TreeManager;

import java.awt.event.ActionEvent;

/**
 * Author: Nikita Khvorov
 * Date: 18.03.13
 */
public class SaveXMLListener extends IOXMLListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            TreeManager.getInstance().serializeOpenedTree();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
