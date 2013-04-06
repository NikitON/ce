package com.belkatechnologies.configeditor.gui.panels.tree;

import com.belkatechnologies.configeditor.gui.panels.ButtonsPanel;
import com.belkatechnologies.configeditor.listeners.tree.buttons.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Author: Nikita Khvorov
 * Date: 02.04.13
 */
public class TreeButtons extends ButtonsPanel {
    public TreeButtons() {
        setPreferredSize(new Dimension(55, 500));
        setLayout(new BorderLayout());
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(55, 255));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonsPanel.setLayout(new GridLayout(5, 1, 5, 5));
        addButtons(buttonsPanel);
        add(buttonsPanel, BorderLayout.PAGE_START);
        add(new JPanel(), BorderLayout.CENTER);
    }

    private void addButtons(JPanel buttonsPanel) {
        buttons = new ArrayList<JButton>();
        addButton(buttonsPanel, "/images/up.png", new OfferUpListener());
        addButton(buttonsPanel, "/images/down.png", new OfferDownListener());
        addButton(buttonsPanel, "/images/start.png", new OfferStartListener());
        addButton(buttonsPanel, "/images/stop.png", new OfferStopListener());
        addButton(buttonsPanel, "/images/delete.png", new OfferDeleteListener());
    }

    private void addButton(JPanel panel, String image, ActionListener listener) {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(getClass().getResource(image)));
        button.addActionListener(listener);
        panel.add(button);
        buttons.add(button);
    }
}
