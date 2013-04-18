package com.belkatechnologies.configeditor.checkers.app;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.configeditor.model.RewardWord;

import java.util.List;

/**
 * Author: Nikita Khvorov
 * Date: 12.04.13
 */
public class DefaultRTChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        String defaultRewardType = inputPanel.getParam("defaultRewardType");
        if (!checkEmpty(defaultRewardType, "Reward Type", sb)) {
            checkExists(sb, defaultRewardType, inputPanel.getList("words"));
        }
    }

    private void checkExists(StringBuilder sb, String defaultRewardType, List words) {
        if (words != null) {
            for (Object word : words) {
                if (((RewardWord) word).getId().equals(defaultRewardType)) {
                    return;
                }
            }
        }
        sb.append("Reward Type: Should be one of Words.\n");
    }
}
