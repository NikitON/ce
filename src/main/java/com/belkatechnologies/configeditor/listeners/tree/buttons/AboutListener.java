package com.belkatechnologies.configeditor.listeners.tree.buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Nikita Khvorov
 * Date: 29.04.13
 */
public class AboutListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new Label("B - Belka"));
        panel.add(new Label("O - Offers"));
        panel.add(new Label("R - Rotator"));
        panel.add(new Label("S - Simple"));
        panel.add(new Label("C - Config"));
        panel.add(new Label("H - Handler"));
        JOptionPane.showConfirmDialog(null, panel, "BORSCH", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null);
    }
}
