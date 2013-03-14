package com.belkatechnologies.configeditor;

import javax.swing.*;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public class ConfigEditor extends JFrame {

    public ConfigEditor() {
        setTitle("Config Editor");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ConfigEditor ce = new ConfigEditor();
                ce.setVisible(true);
            }
        });
    }
}
