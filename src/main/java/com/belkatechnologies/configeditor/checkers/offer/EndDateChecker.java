package com.belkatechnologies.configeditor.checkers.offer;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;

/**
 * Author: Nikita Khvorov
 * Date: 15.04.13
 */
public class EndDateChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        String date = inputPanel.getParam("endDate");
        if (!checkEmpty(date, "End Date", sb)) {
            checkDate(date, "End Date", sb);
        }
    }
}
