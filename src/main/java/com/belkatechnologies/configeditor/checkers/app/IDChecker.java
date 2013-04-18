package com.belkatechnologies.configeditor.checkers.app;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.configeditor.managers.TreeManager;

/**
 * Author: Nikita Khvorov
 * Date: 09.04.13
 */
public class IDChecker extends InputChecker {
    private static final String[] SOCIAL_NETWORKS = new String[]{"_vk", "_ok", "_mr", "_fs", "_fb", "_jp"};

    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        String id = inputPanel.getParam("id");
        checkExistence(id, sb);
        checkSN(id, sb);
    }

    private void checkExistence(String id, StringBuilder sb) {
        if (TreeManager.getInstance().contains(id)) {
            sb.append("ID: Application with id='").append(id).append("' already exists.\n");
        }
    }

    private void checkSN(String id, StringBuilder sb) {
        for (String socialNetwork : SOCIAL_NETWORKS) {
            if (id.contains(socialNetwork)) {
                return;
            }
        }
        sb.append("ID: Application id should contain Social Network.\n    ( ");
        for (String socialNetwork : SOCIAL_NETWORKS) {
            sb.append(socialNetwork).append(" ");
        }
        sb.append(")\n");
    }
}
