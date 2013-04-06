package com.belkatechnologies.configeditor.listeners.workbench;

import com.belkatechnologies.configeditor.gui.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Nikita Khvorov
 * Date: 04.04.13
 */
public class AddOfferListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        GUI.getInstance().showAddOfferView();
    }
}
