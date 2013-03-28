package com.belkatechnologies.configeditor.gui;

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
    }
}
