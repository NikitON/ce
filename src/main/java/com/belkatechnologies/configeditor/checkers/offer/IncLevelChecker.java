package com.belkatechnologies.configeditor.checkers.offer;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;

/**
 * Author: Nikita Khvorov
 * Date: 15.04.13
 */
public class IncLevelChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        String incLevel = inputPanel.getParam("incrementLevel");
        checkBoolean(incLevel, "Increment Level", sb);
    }
}