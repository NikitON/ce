package com.belkatechnologies.configeditor.listeners.workbench;

import com.belkatechnologies.configeditor.managers.TreeManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Nikita Khvorov
 * Date: 22.04.13
 */
public class CopyOfferListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            TreeManager.getInstance().copySelectedOffer();
        } catch (CloneNotSupportedException ignored) {
        }
    }
}
