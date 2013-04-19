package com.belkatechnologies.configeditor.checkers.offer;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.configeditor.model.Checker;
import com.belkatechnologies.utils.StringUtil;

/**
 * Author: Nikita Khvorov
 * Date: 15.04.13
 */
public class CheckerChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        Checker checker = (Checker) inputPanel.getObject("checker");
        if (checker == null || !StringUtil.isOkString(checker.getStrategy())) {
            sb.append("Checker: Strategy: Should not be empty.\n");
        }
    }
}
