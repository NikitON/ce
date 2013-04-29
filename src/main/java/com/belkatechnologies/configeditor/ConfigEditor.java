package com.belkatechnologies.configeditor;

import com.belkatechnologies.configeditor.gui.GUI;
import com.belkatechnologies.configeditor.logging.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public class ConfigEditor extends JFrame {
    private static final String LOG_CONFIG_NAME = "log4j.properties";

    public ConfigEditor() {
        setTitle("BORSCH");
        setMinimumSize(new Dimension(1275, 475));
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                InputStream inputStream = ConfigEditor.class.getClassLoader()
                        .getResourceAsStream(LOG_CONFIG_NAME);
                Logger.initializeProperties(inputStream);
                GUI.getInstance();
            }
        });
    }
}
