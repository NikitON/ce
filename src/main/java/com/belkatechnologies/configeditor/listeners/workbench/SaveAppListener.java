package com.belkatechnologies.configeditor.listeners.workbench;

import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Nikita Khvorov
 * Date: 09.04.13
 */
public class SaveAppListener implements ActionListener {
    private InputPanel inputPanel;

    public SaveAppListener(InputPanel inputPanel) {
        this.inputPanel = inputPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
