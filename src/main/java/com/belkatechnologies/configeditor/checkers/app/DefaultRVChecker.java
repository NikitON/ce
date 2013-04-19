package com.belkatechnologies.configeditor.checkers.app;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;

/**
 * Author: Nikita Khvorov
 * Date: 12.04.13
 */
public class DefaultRVChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        String defaultRewardValue = inputPanel.getParam("defaultRewardValue");
        if (!checkEmpty(defaultRewardValue, "Reward Value", sb)) {
            checkInteger(defaultRewardValue, "Reward Value", sb);
        }
    }
}
