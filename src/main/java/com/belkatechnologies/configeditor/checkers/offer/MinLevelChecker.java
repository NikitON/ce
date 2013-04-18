package com.belkatechnologies.configeditor.checkers.offer;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.utils.StringUtil;

/**
 * Author: Nikita Khvorov
 * Date: 15.04.13
 */
public class MinLevelChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        String minLevel = inputPanel.getParam("minLevel");
        if (StringUtil.isOkString(minLevel)) {
            checkInteger(minLevel, "Min Level", sb);
        }
    }
}
