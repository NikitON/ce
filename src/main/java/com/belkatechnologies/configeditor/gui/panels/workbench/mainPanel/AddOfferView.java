package com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel;

import com.belkatechnologies.configeditor.model.Application;
import com.belkatechnologies.configeditor.model.Offer;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Nikita Khvorov
 * Date: 04.04.13
 */
public class AddOfferView extends JPanel {
    private OfferInputPanel inputPanel;

    public AddOfferView(Offer offer, boolean copying) {
        inputPanel = new OfferInputPanel(offer, copying);
        add(inputPanel, BorderLayout.CENTER);
    }

    public AddOfferView(Offer offer, boolean copying, Application application) {
        inputPanel = new OfferInputPanel(offer, copying, application);
        add(inputPanel, BorderLayout.CENTER);
    }

    @Override
    public Dimension getPreferredSize() {
        return inputPanel.getPreferredSize();
    }
}
