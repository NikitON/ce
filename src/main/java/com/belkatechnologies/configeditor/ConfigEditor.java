package com.belkatechnologies.configeditor;

import com.belkatechnologies.configeditor.listners.topbuttons.*;
import com.belkatechnologies.configeditor.managers.TreeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
        addSeparator(panel);
        addSaveButtons(panel);
        panel.setBackground(new Color(0x9BBCE9));
        return panel;
    }

    private static void addOpenButtons(JPanel panel) {
        addButton(panel, "Open", new OpenXMLListener());
        addButton(panel, "Download Staging", new DownloadXMLListener(true));
        addButton(panel, "Download Production", new DownloadXMLListener(false));
    }

    private static void addSeparator(JPanel panel) {
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(40, 0));
        panel.add(separator);
    }

    private static void addSaveButtons(JPanel panel) {
        addButton(panel, "Save", new SaveXMLListener());
        addButton(panel, "Save As", new SaveAsXMLListener());
        addButton(panel, "Load to Staging", new UploadXMLListener(true));
        addButton(panel, "Load to Production", new UploadXMLListener(false));
    }

    private static void addButton(JPanel panel, String buttonName, ActionListener listener) {
        JButton button = new JButton(buttonName);
        button.addActionListener(listener);
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
