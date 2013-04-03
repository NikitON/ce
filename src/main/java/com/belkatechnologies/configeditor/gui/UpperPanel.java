package com.belkatechnologies.configeditor.gui;

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
        setBackground(new Color(0x9BBCE9));
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x184686)));
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
        saveButtons = new ArrayList<JButton>();
        addButton("Save", new SaveXMLListener(), true);
        addButton("Save As", new SaveAsXMLListener(), true);
        addButton("Load to Staging", new UploadXMLListener(true), true);
        addButton("Load to Production", new UploadXMLListener(false), true);
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
