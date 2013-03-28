package com.belkatechnologies.configeditor.gui;

import com.belkatechnologies.configeditor.listners.topbuttons.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Author: Nikita Khvorov
 * Date: 22.03.13
 */
public class UpperPanel extends JPanel {
    public UpperPanel() {
        setLayout(new FlowLayout());
        addOpenButtons();
        addSeparator();
        addSaveButtons();
        setBackground(new Color(0x9BBCE9));
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x184686)));
    }

    private void addOpenButtons() {
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
        addButton("Save", new SaveXMLListener());
        addButton("Save As", new SaveAsXMLListener());
        addButton("Load to Staging", new UploadXMLListener(true));
        addButton("Load to Production", new UploadXMLListener(false));
    }

    private void addButton(String buttonName, ActionListener listener) {
        JButton button = new JButton(buttonName);
        button.addActionListener(listener);
        add(button);
    }
}
