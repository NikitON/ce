package com.belkatechnologies.configeditor.listeners.tree.buttons;

import com.belkatechnologies.configeditor.managers.TreeManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Nikita Khvorov
 * Date: 29.04.13
 */
public class RefreshTreeListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        TreeManager.getInstance().refreshTree();
    }
}
