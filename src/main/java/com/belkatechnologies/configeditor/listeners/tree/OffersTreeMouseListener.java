package com.belkatechnologies.configeditor.listeners.tree;

import com.belkatechnologies.configeditor.managers.TreeManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: Nikita Khvorov
 * Date: 04.04.13
 */
public class OffersTreeMouseListener extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getClickCount() == 2) {
            try {
                TreeManager.getInstance().editSelected();
            } catch (NullPointerException ignored) {
            }
        }
    }
}
