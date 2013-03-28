package com.belkatechnologies.configeditor;

import com.belkatechnologies.configeditor.gui.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public class ConfigEditor extends JFrame {
    public ConfigEditor() {
        setTitle("Config Editor");
        setMinimumSize(new Dimension(900, 200));
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI.getInstance();
            }
        });
    }
}
