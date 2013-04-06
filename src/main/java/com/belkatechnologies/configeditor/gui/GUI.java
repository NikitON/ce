package com.belkatechnologies.configeditor.gui;

import com.belkatechnologies.configeditor.ConfigEditor;
import com.belkatechnologies.configeditor.gui.panels.SelectPanel;
import com.belkatechnologies.configeditor.gui.panels.StatusBar;
import com.belkatechnologies.configeditor.gui.panels.UpperPanel;
import com.belkatechnologies.configeditor.gui.panels.tree.TreePanel;
import com.belkatechnologies.configeditor.gui.panels.workbench.WorkbenchPanel;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Author: Nikita Khvorov
 * Date: 22.03.13
 */
public class GUI implements ButtonsStateToggler {
    private static GUI instance = null;

    private final JFrame mainFrame;

    private final TreePanel treePanel;
    private final UpperPanel upperPanel;
    private final StatusBar statusBar;
    private final SelectPanel selectPanel;
    private final WorkbenchPanel workbenchPanel;

    private GUI() {
        this.mainFrame = new ConfigEditor();
        this.treePanel = new TreePanel();
        this.upperPanel = new UpperPanel();
        this.selectPanel = new SelectPanel();
        this.statusBar = new StatusBar();
        this.workbenchPanel = new WorkbenchPanel();
        addComponentsToPane(mainFrame.getContentPane());
        disableButtons();
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

    public void showAttentionMessageDialog(String message) {
        showMessageDialog("Hey!", message, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showMessageDialog(String title, String message, int type) {
        JOptionPane.showMessageDialog(mainFrame, message, title, type);
    }

    private void addComponentsToPane(Container contentPane) {
        contentPane.add(treePanel, BorderLayout.LINE_START);
        contentPane.add(selectPanel, BorderLayout.LINE_END);
        contentPane.add(upperPanel, BorderLayout.PAGE_START);
        contentPane.add(statusBar, BorderLayout.PAGE_END);
        contentPane.add(workbenchPanel, BorderLayout.CENTER);
    }

    public void replaceTreePanel(JTree tree) {
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

    @Override
    public void disableButtons() {
        upperPanel.disableButtons();
        treePanel.disableButtons();
        workbenchPanel.disableButtons();
    }

    @Override
    public void enableButtons() {
        upperPanel.enableButtons();
        treePanel.enableButtons();
        workbenchPanel.enableButtons();
    }

    @Override
    public void toggleButtons() {
        upperPanel.toggleButtons();
        treePanel.toggleButtons();
        workbenchPanel.toggleButtons();
    }

    public void showAddAppView() {
        workbenchPanel.showAddAppView();
    }

    public void showAddOfferView() {
        workbenchPanel.showAddOfferView();
    }
}
