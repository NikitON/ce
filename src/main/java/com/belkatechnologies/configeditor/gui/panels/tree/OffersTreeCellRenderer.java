package com.belkatechnologies.configeditor.gui.panels.tree;

import com.belkatechnologies.configeditor.gui.Colors;
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
    private DefaultTreeCellRenderer defaultTreeCellRenderer = new DefaultTreeCellRenderer();

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (!node.isRoot()) {
            if (leaf) {
                String appId = node.getParent().toString();
                if (TreeManager.getInstance().isActive(appId, node.toString())) {
                    setBackgroundNonSelectionColor(Colors.ACTIVE_NOT_SELECTED);
                    setBackgroundSelectionColor(Colors.ACTIVE_SELECTED);
                    setBorderSelectionColor(Colors.ACTIVE_BORDER);
                    return this;
                }
            } else if (TreeManager.getInstance().isActive(node.toString())) {
                setBackgroundNonSelectionColor(Colors.ACTIVE_NOT_SELECTED);
                setBackgroundSelectionColor(Colors.ACTIVE_SELECTED);
                setBorderSelectionColor(Colors.ACTIVE_BORDER);
                return this;
            }
        }
        return defaultTreeCellRenderer.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
