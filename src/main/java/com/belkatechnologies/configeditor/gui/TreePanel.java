package com.belkatechnologies.configeditor.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Nikita Khvorov
 * Date: 22.03.13
 */
public class TreePanel extends JPanel implements ButtonsStateToggler {
    private JScrollPane scrollPane;
    private TreeButtons buttonsPanel;

    public TreePanel() {
        setPreferredSize(new Dimension(350, 500));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(0x184686)));
        scrollPane = new JScrollPane();
        scrollPane.setBorder(TreeBorderFactory.getBorder());
        add(scrollPane, BorderLayout.CENTER);
        buttonsPanel = new TreeButtons();
        add(buttonsPanel, BorderLayout.LINE_END);
    }

    public void replaceScrollPane(JTree tree) {
        remove(scrollPane);
        scrollPane = new JScrollPane(tree);
        scrollPane.setBorder(TreeBorderFactory.getBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void enableButtons() {
        buttonsPanel.enableButtons();
    }

    @Override
    public void disableButtons() {
        buttonsPanel.disableButtons();
    }

    @Override
    public void toggleButtons() {
        buttonsPanel.toggleButtons();
    }
}
