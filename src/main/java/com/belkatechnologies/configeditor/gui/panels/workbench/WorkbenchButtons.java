package com.belkatechnologies.configeditor.gui.panels.workbench;

import com.belkatechnologies.configeditor.gui.Colors;
import com.belkatechnologies.configeditor.gui.panels.ButtonsPanel;
import com.belkatechnologies.configeditor.listeners.CopyAppListener;
import com.belkatechnologies.configeditor.listeners.workbench.AddAppListener;
import com.belkatechnologies.configeditor.listeners.workbench.AddOfferListener;
import com.belkatechnologies.configeditor.listeners.workbench.CopyOfferListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Author: Nikita Khvorov
 * Date: 04.04.13
 */
public class WorkbenchButtons extends ButtonsPanel {

    public WorkbenchButtons() {
        setLayout(new BorderLayout());
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        buttonsPanel.setPreferredSize(new Dimension(560, 50));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Colors.LIGHT_BACK, 5));
        buttonsPanel.setBackground(Colors.LIGHT_BACK);
        addButtons(buttonsPanel);
        add(buttonsPanel, BorderLayout.LINE_START);
        JPanel fillPanel = new JPanel();
        fillPanel.setBackground(Colors.LIGHT_BACK);
        add(fillPanel, BorderLayout.CENTER);
    }

    private void addButtons(JPanel buttonsPanel) {
        buttons = new ArrayList<JButton>();
        addButton(buttonsPanel, "   App", "/images/add.png", new AddAppListener());
        addButton(buttonsPanel, "   Offer", "/images/add.png", new AddOfferListener());
        addButton(buttonsPanel, "Copy App", "/images/copy.png", new CopyAppListener());
        addButton(buttonsPanel, "Copy Offer", "/images/copy.png", new CopyOfferListener());
    }

    private void addButton(JPanel panel, String buttonName, String image, ActionListener listener) {
        JButton button = new JButton(buttonName);
        button.setIcon(new ImageIcon(getClass().getResource(image)));
        button.addActionListener(listener);
        panel.add(button);
        buttons.add(button);
    }

}
