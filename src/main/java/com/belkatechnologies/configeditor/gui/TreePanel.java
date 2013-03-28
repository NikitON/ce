package com.belkatechnologies.configeditor.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Nikita Khvorov
 * Date: 22.03.13
 */
public class TreePanel extends JPanel {
    private JScrollPane scrollPane;

    public TreePanel() {
        setPreferredSize(new Dimension(250, 500));
        setLayout(new BorderLayout());
        scrollPane = new JScrollPane();
        scrollPane.setBorder(TreeBorderFactory.getBorder());
        add(scrollPane);
    }

    public void replaceScrollPane(JTree tree) {
        remove(scrollPane);
        scrollPane = new JScrollPane(tree);
        scrollPane.setBorder(TreeBorderFactory.getBorder());
        add(scrollPane, BorderLayout.CENTER);
    }
}
