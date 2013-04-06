package com.belkatechnologies.configeditor.gui.panels.tree;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: Nikita Khvorov
 * Date: 28.03.13
 */
public class OffersTree extends JTree {
    public OffersTree(TreeNode root) {
        super(root);
        this.setCellRenderer(new OffersTreeCellRenderer());
        this.addMouseListener(new OffersTreeMouseAdapter());
    }

    private class OffersTreeMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int selRow = getRowForLocation(e.getX(), e.getY());
            TreePath selPath = getPathForLocation(e.getX(), e.getY());
            if (selRow != -1) {
                if (e.getClickCount() == 2) {
                }
            }
        }
    }
}
