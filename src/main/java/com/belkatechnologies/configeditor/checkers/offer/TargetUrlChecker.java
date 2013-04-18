package com.belkatechnologies.configeditor.checkers.offer;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;

/**
 * Author: Nikita Khvorov
 * Date: 15.04.13
 */
public class TargetURLChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        String url = inputPanel.getParam("targetURL");
        checkEmpty(url, "Target URL", sb);
    }
}
