package com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel;

import com.belkatechnologies.configeditor.model.Application;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Nikita Khvorov
 * Date: 04.04.13
 */
public class AddAppView extends JPanel {
    private AppInputPanel inputPanel;

    public AddAppView(Application app) {
        inputPanel = new AppInputPanel(app);
        add(inputPanel, BorderLayout.CENTER);
    }

    @Override
    public Dimension getPreferredSize() {
        return inputPanel.getPreferredSize();
    }
}
