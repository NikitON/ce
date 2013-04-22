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

    public AddAppView(Application app, boolean copying) {
        inputPanel = new AppInputPanel(app, copying);
        add(inputPanel, BorderLayout.CENTER);
    }

    @Override
    public Dimension getPreferredSize() {
        return inputPanel.getPreferredSize();
    }
}
