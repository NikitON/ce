package com.belkatechnologies.configeditor.gui.panels.tree;

import com.belkatechnologies.configeditor.listeners.tree.OffersTreeMouseListener;

import javax.swing.*;
import javax.swing.tree.TreeNode;

/**
 * Author: Nikita Khvorov
 * Date: 28.03.13
 */
public class OffersTree extends JTree {
    public OffersTree(TreeNode root) {
        super(root);
        this.setCellRenderer(new OffersTreeCellRenderer());
        this.addMouseListener(new OffersTreeMouseListener());
    }
}
