package com.belkatechnologies.configeditor.checkers.app;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.utils.StringUtil;

/**
 * Author: Nikita Khvorov
 * Date: 12.04.13
 */
public class LinkChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        String link = inputPanel.getParam("link");
        if (!StringUtil.isOkString(link)) {
            sb.append("LINK: Please specify the link! Or use \"nolink\" instead.\n");
        }
    }
}
