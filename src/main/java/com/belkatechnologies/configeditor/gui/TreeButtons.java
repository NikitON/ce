package com.belkatechnologies.configeditor.gui;

import com.belkatechnologies.configeditor.listeners.treebuttons.OfferDownListener;
import com.belkatechnologies.configeditor.listeners.treebuttons.OfferStartListener;
import com.belkatechnologies.configeditor.listeners.treebuttons.OfferStopListener;
import com.belkatechnologies.configeditor.listeners.treebuttons.OfferUpListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Author: Nikita Khvorov
 * Date: 02.04.13
 */
public class TreeButtons extends JPanel implements ButtonsStateToggler {
    private java.util.List<JButton> buttons;

    public TreeButtons() {
        setPreferredSize(new Dimension(55, 500));
        setLayout(new BorderLayout());
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(55, 215));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonsPanel.setLayout(new GridLayout(4, 1, 5, 5));
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
    }

    private void addButton(JPanel panel, String image, ActionListener listener) {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(getClass().getResource(image)));
        button.addActionListener(listener);
        panel.add(button);
        buttons.add(button);
    }

    @Override
    public void toggleButtons() {
        if (buttons.get(0).isEnabled()) {
            for (JButton button : buttons) {
                button.setEnabled(false);
            }
        } else {
            for (JButton button : buttons) {
                button.setEnabled(true);
            }
        }
    }

    @Override
    public void enableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
    }

    @Override
    public void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
}
