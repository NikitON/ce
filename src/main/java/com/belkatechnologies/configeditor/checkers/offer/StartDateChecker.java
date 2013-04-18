package com.belkatechnologies.configeditor.checkers.offer;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;

/**
 * Author: Nikita Khvorov
 * Date: 15.04.13
 */
public class StartDateChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        String date = inputPanel.getParam("startDate");
        if (!checkEmpty(date, "Start Date", sb)) {
            checkDate(date, "Start Date", sb);
        }
    }
}
