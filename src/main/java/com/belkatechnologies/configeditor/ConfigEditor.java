package com.belkatechnologies.configeditor;

import com.belkatechnologies.configeditor.listners.topbuttons.OpenXMLListener;
import com.belkatechnologies.configeditor.listners.topbuttons.SaveAsXMLListener;
import com.belkatechnologies.configeditor.managers.TreeManager;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public class ConfigEditor extends JFrame {

    public ConfigEditor() {
        setTitle("Config Editor");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private static void createAndShowGUI() {
        JFrame frame = new ConfigEditor();
        addComponentsToPane(frame.getContentPane());
        frame.setVisible(true);
    }

    private static void addComponentsToPane(Container contentPane) {
        contentPane.add(getLeftPanel(), BorderLayout.LINE_START);
        contentPane.add(getTopPanel(), BorderLayout.PAGE_START);
    }

    private static JPanel getTopPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        addOpenButtons(panel);
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(20, 0));
        panel.add(separator);
        addSaveButtons(panel);
        panel.setBackground(new Color(0x9BBCE9));
        return panel;
    }

    private static void addOpenButtons(JPanel panel) {
        addOpenLocalButton(panel);
        JButton downloadStaging = new JButton("Download Staging");
        JButton downloadProduction = new JButton("Download Production");
        panel.add(downloadStaging);
        panel.add(downloadProduction);

    }

    private static void addSaveButtons(JPanel panel) {
        JButton save = new JButton("Save");
        addSaveAsLocalButton(panel);
        JButton loadToStaging = new JButton("Load to Staging");
        JButton loadToProduction = new JButton("Load to Production");
        panel.add(save);
        panel.add(loadToStaging);
        panel.add(loadToProduction);
    }

    private static void addOpenLocalButton(JPanel panel) {
        JButton button = new JButton("Open XML");
        button.addActionListener(new OpenXMLListener());
        panel.add(button);
    }

    private static void addSaveAsLocalButton(JPanel panel) {
        JButton button = new JButton("Save As");
        button.addActionListener(new SaveAsXMLListener());
        panel.add(button);
    }

    private static JPanel getLeftPanel() {
        return TreeManager.getInstance().getTreePanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
