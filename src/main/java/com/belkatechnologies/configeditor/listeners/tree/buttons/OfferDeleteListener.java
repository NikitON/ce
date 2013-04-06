package com.belkatechnologies.configeditor.listeners.tree.buttons;

import com.belkatechnologies.configeditor.managers.TreeManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Nikita Khvorov
 * Date: 04.04.13
 */
public class OfferDeleteListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        TreeManager.getInstance().deleteOffers();
    }
}
