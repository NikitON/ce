package com.belkatechnologies.configeditor.checkers.offer;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.configeditor.model.Targeting;
import com.belkatechnologies.utils.StringUtil;

/**
 * Author: Nikita Khvorov
 * Date: 15.04.13
 */
public class TargetingChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        Targeting targeting = (Targeting) inputPanel.getObject("targeting");
        if (targeting != null) {
            checkSex(targeting.getSex(), sb);
            checkInterval(targeting.getAge(), "Targeting: Age", sb);
            checkList(targeting.getCountries(), "Targeting: Countries", sb);
            checkList(targeting.getCities(), "Targeting: Cities", sb);
            checkList(targeting.getGroups(), "Targeting: Groups", sb);
            checkInterval(targeting.getIdEnds(), "Targeting: Id Ends", sb);
        }
    }

    private void checkInterval(String line, String field, StringBuilder sb) {
        if (StringUtil.isOkString(line)) {
            if (line.contains("-")) {
                String[] bounds = line.split("-");
                checkInteger(bounds[0], field + " From", sb);
                checkInteger(bounds[1], field + " To", sb);
            } else {
                sb.append(field).append(": Should contain two \"-\"-separated numbers.\n");
            }
        }
    }

    private void checkSex(String sex, StringBuilder sb) {
        if (StringUtil.isOkString(sex)) {
            switch (sex) {
                case "male":
                case "female":
                case "all":
                case "1":
                case "2":
                    return;
                default:
                    sb.append("Targeting: Sex: Should be female(1), male(2), all, or empty.\n");
            }
        }
    }

    private void checkList(String line, String field, StringBuilder sb) {
        if (StringUtil.isOkString(line)) {
            for (String s : line.split(",")) {
                try {
                    Long.parseLong(s);
                } catch (NumberFormatException e) {
                    sb.append(field).append(": Contains wrong numbers.\n");
                    break;
                }
            }
        }
    }
}
