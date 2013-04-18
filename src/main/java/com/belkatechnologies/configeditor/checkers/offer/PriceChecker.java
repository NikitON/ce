package com.belkatechnologies.configeditor.checkers.offer;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.utils.StringUtil;

/**
 * Author: Nikita Khvorov
 * Date: 15.04.13
 */
public class PriceChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        String price = inputPanel.getParam("price");
        if (StringUtil.isOkString(price)) {
            checkDouble(price, "Price", sb);
        }
    }
}
