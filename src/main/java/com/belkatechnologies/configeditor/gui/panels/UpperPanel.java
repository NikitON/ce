package com.belkatechnologies.configeditor.gui.panels;

import com.belkatechnologies.configeditor.gui.ButtonsStateToggler;
import com.belkatechnologies.configeditor.gui.Colors;
import com.belkatechnologies.configeditor.listeners.topbuttons.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nikita Khvorov
 * Date: 22.03.13
 */
public class UpperPanel extends JPanel implements ButtonsStateToggler {
    private List<JButton> saveButtons;

    public UpperPanel() {
        setLayout(new FlowLayout());
        addOpenButtons();
        addSeparator();
        addSaveButtons();
        setBackground(Colors.DARK_BACK);
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Colors.BORDER));
    }

    private void addOpenButtons() {
        addButton("Open Old", new OpenOldXMLListener());
        addButton("Open", new OpenXMLListener());
        addButton("Download Staging", new DownloadXMLListener(true));
        addButton("Download Production", new DownloadXMLListener(false));
    }

    private void addSeparator() {
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(40, 0));
        add(separator);
    }

    private void addSaveButtons() {
        saveButtons = new ArrayList<>();
        addButton("Save", new SaveXMLListener(), true);
        addButton("Save As", new SaveAsXMLListener(), true);
        addButton("Upload Staging", new UploadXMLListener(true), true);
        addButton("Upload Production", new UploadXMLListener(false), true);
    }

    private void addButton(String buttonName, ActionListener listener) {
        addButton(buttonName, listener, false);
    }

    private void addButton(String buttonName, ActionListener listener, boolean saveButton) {
        JButton button = new JButton(buttonName);
        button.addActionListener(listener);
        add(button);
        if (saveButton) {
            saveButtons.add(button);
        }
    }

    @Override
    public void toggleButtons() {
        if (saveButtons.get(0).isEnabled()) {
            for (JButton saveButton : saveButtons) {
                saveButton.setEnabled(false);
            }
        } else {
            for (JButton saveButton : saveButtons) {
                saveButton.setEnabled(true);
            }
        }
    }

    @Override
    public void enableButtons() {
        for (JButton saveButton : saveButtons) {
            saveButton.setEnabled(true);
        }
    }

    @Override
    public void disableButtons() {
        for (JButton saveButton : saveButtons) {
            saveButton.setEnabled(false);
        }
    }
}
