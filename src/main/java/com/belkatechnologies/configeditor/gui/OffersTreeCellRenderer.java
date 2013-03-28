package com.belkatechnologies.configeditor.gui;

import com.belkatechnologies.configeditor.managers.TreeManager;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * Author: Nikita Khvorov
 * Date: 28.03.13
 */
public class OffersTreeCellRenderer extends DefaultTreeCellRenderer {
    private static final Color ACTIVE_COLOR = new Color(0x9DFA80);
    private DefaultTreeCellRenderer defaultTreeCellRenderer = new DefaultTreeCellRenderer();

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (!node.isRoot()) {
            if (leaf) {
                String appId = node.getParent().toString();
                if (TreeManager.getInstance().isActive(appId, node.toString())) {
                    setBackgroundNonSelectionColor(ACTIVE_COLOR);
                    return this;
                }
            } else {
                if (TreeManager.getInstance().isActive(node.toString())) {
                    setBackgroundNonSelectionColor(ACTIVE_COLOR);
                    return this;
                }
            }
        }
        return defaultTreeCellRenderer.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
