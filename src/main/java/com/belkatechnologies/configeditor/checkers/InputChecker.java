package com.belkatechnologies.configeditor.checkers;

import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;

/**
 * Author: Nikita Khvorov
 * Date: 09.04.13
 */
public abstract class InputChecker {
    public abstract void check(InputPanel inputPanel, StringBuilder sb);
}
