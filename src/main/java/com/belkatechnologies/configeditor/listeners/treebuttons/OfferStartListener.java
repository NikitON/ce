package com.belkatechnologies.configeditor.listeners.treebuttons;

import com.belkatechnologies.configeditor.managers.TreeManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Nikita Khvorov
 * Date: 03.04.13
 */
public class OfferStartListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        TreeManager.getInstance().startOffers();
    }
}
