package com.belkatechnologies.configeditor.listeners.topbuttons;

import com.belkatechnologies.configeditor.gui.GUI;
import com.belkatechnologies.configeditor.logging.Logger;
import com.belkatechnologies.configeditor.managers.TreeManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Author: Nikita Khvorov
 * Date: 15.03.13
 */
public class SaveAsXMLListener extends IOXMLListener {
    public static final String XML_EXTENSION = ".xml";

    private JFileChooser fileChooser;

    public SaveAsXMLListener() {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(CURRENT_DIRECTORY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String path = file.getAbsolutePath();
            if (!path.endsWith(XML_EXTENSION)) {
                fileChooser.setSelectedFile(new File(path + XML_EXTENSION));
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        GUI.getInstance().runLoading("Saving to " + fileChooser.getSelectedFile().getName());
                        TreeManager.getInstance().serializeTree(fileChooser.getSelectedFile());
                    } catch (Exception e1) {
                        Logger.error("SAVE AS", e1);
                    } finally {
                        GUI.getInstance().stopLoading();
                    }
                }
            }).start();
        }
    }
}
