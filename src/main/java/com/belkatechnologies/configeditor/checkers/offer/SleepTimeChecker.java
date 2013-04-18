package com.belkatechnologies.configeditor.checkers.offer;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;

/**
 * Author: Nikita Khvorov
 * Date: 15.04.13
 */
public class SleepTimeChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        String sleepTime = inputPanel.getParam("sleepTime");
        if (!checkEmpty(sleepTime, "Sleep Time", sb)) {
            checkInteger(sleepTime, "Sleep Time", sb);
        }
    }
}
