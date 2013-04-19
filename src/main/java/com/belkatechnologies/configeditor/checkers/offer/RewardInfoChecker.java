package com.belkatechnologies.configeditor.checkers.offer;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.configeditor.managers.TreeManager;
import com.belkatechnologies.configeditor.model.Application;
import com.belkatechnologies.configeditor.model.OfferStep;
import com.belkatechnologies.utils.StringUtil;

import java.util.List;

/**
 * Author: Nikita Khvorov
 * Date: 15.04.13
 */
public class RewardInfoChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        List<OfferStep> steps = inputPanel.getList("steps");
        if (steps == null || steps.isEmpty()) {
            sb.append("STEPS: Should contain at least 1 step.\n");
        } else {
            checkLevels(steps, sb);
            checkDescriptions(steps, inputPanel.getParam("description"), sb);
            checkRewards(steps, inputPanel.getParam("rewardText"), sb);
            checkValues(steps, sb);
            checkTypes(steps, inputPanel.getParam("appId"), sb);
        }
    }

    private void checkTypes(List<OfferStep> steps, String appId, StringBuilder sb) {
        Application app = TreeManager.getInstance().getAppById(appId);
        for (OfferStep step : steps) {
            if (StringUtil.isOkString(step.getRewardType())) {
                if (!app.containsWord(step.getRewardType())) {
                    sb.append("Steps Types: Should be from application's words.\n");
                }
            }
        }
    }

    private void checkValues(List<OfferStep> steps, StringBuilder sb) {
        for (OfferStep step : steps) {
            if (StringUtil.isOkString(step.getRewardValue())) {
                if (!checkInteger(step.getRewardValue(), "Steps Values", sb)) {
                    break;
                }
            }
        }
    }

    private void checkRewards(List<OfferStep> steps, String rewardText, StringBuilder sb) {
        if (StringUtil.isOkString(rewardText)) {
            checkReplacers(rewardText, "Reward Text", sb);
        } else {
            for (OfferStep step : steps) {
                if (!StringUtil.isOkString(step.getRewardText())) {
                    sb.append("Steps Texts: Should not be empty if global reward text is empty.\n");
                    break;
                }
            }
        }
    }

    private void checkDescriptions(List<OfferStep> steps, String description, StringBuilder sb) {
        if (StringUtil.isOkString(description)) {
            checkReplacers(description, "Description", sb);
        } else {
            for (OfferStep step : steps) {
                if (!StringUtil.isOkString(step.getDescription())) {
                    sb.append("Steps Descriptions: Should not be empty if global description is empty.\n");
                    break;
                }
            }
        }
    }

    private void checkReplacers(String text, String field, StringBuilder sb) {
        if (!text.contains("%R_LEVEL%") || !text.contains("%R_TYPE%") || !text.contains("%R_VALUE%")) {
            sb.append(field).append(": Should contain %R_LEVEL%, %R_VALUE% and %R_TYPE%");
        }
    }

    private void checkLevels(List<OfferStep> steps, StringBuilder sb) {
        for (OfferStep step : steps) {
            if (!checkInteger(step.getLevel(), "Steps Levels", sb)) {
                break;
            }
        }
    }
}
