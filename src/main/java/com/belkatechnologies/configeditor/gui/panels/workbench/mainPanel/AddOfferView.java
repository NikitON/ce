package com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Nikita Khvorov
 * Date: 04.04.13
 */
public class AddOfferView extends JPanel {
    private OfferInputPanel inputPanel;

    public AddOfferView() {
        inputPanel = new OfferInputPanel();
        add(inputPanel, BorderLayout.CENTER);
    }

    @Override
    public Dimension getPreferredSize() {
        return inputPanel.getPreferredSize();
    }
}
