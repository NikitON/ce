package com.belkatechnologies.configeditor.checkers.app;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;

/**
 * Author: Nikita Khvorov
 * Date: 12.04.13
 */
public class ExplicitRewardsChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        String explicitRewards = inputPanel.getParam("explicitRewards");
        checkBoolean(explicitRewards, "Explicit Rewards", sb);
    }
}
