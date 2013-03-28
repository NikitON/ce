package com.belkatechnologies.configeditor.gui;

import com.belkatechnologies.configeditor.ConfigEditor;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Author: Nikita Khvorov
 * Date: 22.03.13
 */
public class GUI {
    private static GUI instance = null;

    private final JFrame mainFrame;

    private final TreePanel treePanel;
    private final UpperPanel upperPanel;
    private final StatusBar statusBar;

    private GUI() {
        this.mainFrame = new ConfigEditor();
        this.treePanel = new TreePanel();
        this.upperPanel = new UpperPanel();
        this.statusBar = new StatusBar();
        addComponentsToPane(mainFrame.getContentPane());
        mainFrame.setVisible(true);
    }

    public static GUI getInstance() {
        if (instance == null) {
            instance = new GUI();
        }
        return instance;
    }

    public void notifyWithExceptionMessage(String title, Exception e) {
        showErrorMessageDialog(title, e.getMessage());
    }

    public void notifyWithExceptionTrace(String title, Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        showErrorMessageDialog(title, sw.toString());
    }

    public void showErrorMessageDialog(String title, String message) {
        showMessageDialog(title, message, JOptionPane.ERROR_MESSAGE);
    }

    public void showMessageDialog(String title, String message, int type) {
        JOptionPane.showMessageDialog(mainFrame, message, title, type);
    }

    private void addComponentsToPane(Container contentPane) {
        contentPane.add(treePanel, BorderLayout.LINE_START);
        contentPane.add(upperPanel, BorderLayout.PAGE_START);
        contentPane.add(statusBar, BorderLayout.PAGE_END);
    }

    public void repaintTreePanel(JTree tree) {
        treePanel.replaceScrollPane(tree);
        treePanel.repaint();
        treePanel.revalidate();
    }

    public void runLoading(String message) {
        statusBar.addProgressBar(message);
        statusBar.revalidate();
    }

    public void stopLoading() {
        statusBar.removeProgressBar();
        statusBar.repaint();
        statusBar.revalidate();
    }
}
