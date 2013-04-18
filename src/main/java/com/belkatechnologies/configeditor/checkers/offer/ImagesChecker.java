package com.belkatechnologies.configeditor.checkers.offer;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;

import java.util.List;

/**
 * Author: Nikita Khvorov
 * Date: 15.04.13
 */
public class ImagesChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        List<String> images = inputPanel.getList("images");
        checkList(images, "Images", sb);
    }
}
