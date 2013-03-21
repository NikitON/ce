package com.belkatechnologies.configeditor.listners.topbuttons;

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

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(CURRENT_DIRECTORY);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String path = file.getAbsolutePath();
            if (!path.endsWith(XML_EXTENSION)) {
                fileChooser.setSelectedFile(new File(path + XML_EXTENSION));
            }
            try {
                TreeManager.getInstance().serializeTree(fileChooser.getSelectedFile());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
