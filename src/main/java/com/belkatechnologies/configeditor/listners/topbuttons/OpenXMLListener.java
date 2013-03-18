package com.belkatechnologies.configeditor.listners.topbuttons;

import com.belkatechnologies.configeditor.managers.TreeManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public class OpenXMLListener extends IOXMLListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(CURRENT_DIRECTORY);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                TreeManager.getInstance().deserializeXML(fileChooser.getSelectedFile());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
