package com.belkatechnologies.configeditor.gui.panels;

import com.belkatechnologies.configeditor.gui.Colors;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Nikita Khvorov
 * Date: 28.03.13
 */
public class StatusBar extends JPanel {
    private JProgressBar progressBar;
    private JLabel messageLabel;

    public StatusBar() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(20, 30));
        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Colors.BORDER));
        messageLabel = new JLabel("");
    }

    public void addProgressBar(String message) {
        setMessage(message);
        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(250, 20));
        progressBar.setIndeterminate(true);
        add(progressBar, BorderLayout.LINE_END);
    }

    public void setMessage(String message) {
        remove(messageLabel);
        messageLabel = new JLabel(message);
        add(messageLabel, BorderLayout.LINE_START);
    }

    public void removeProgressBar() {
        remove(progressBar);
        setMessage("Done");
    }
}
