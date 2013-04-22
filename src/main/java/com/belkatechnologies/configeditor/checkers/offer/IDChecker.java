package com.belkatechnologies.configeditor.checkers.offer;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.configeditor.managers.TreeManager;

/**
 * Author: Nikita Khvorov
 * Date: 15.04.13
 */
public class IDChecker extends InputChecker {
    private boolean checkExistence;

    public IDChecker() {
        this.checkExistence = true;
    }

    public IDChecker(boolean checkExistence) {
        this.checkExistence = checkExistence;
    }

    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        String id = inputPanel.getParam("id");
        String appId = inputPanel.getComboParam("appId");
        if (!checkEmpty(appId, "APP ID", sb) && !checkEmpty(id, "ID", sb) && checkExistence) {
            checkExistence(appId, id, sb);
        }
    }

    private void checkExistence(String appId, String id, StringBuilder sb) {
        if (TreeManager.getInstance().contains(appId, id)) {
            sb.append("ID: Offer with id='").append(id).append("' already exists.\n");
        }
    }
}
