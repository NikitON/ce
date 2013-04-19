package com.belkatechnologies.configeditor.checkers;

import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.utils.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Author: Nikita Khvorov
 * Date: 09.04.13
 */
public abstract class InputChecker {
    public abstract void check(InputPanel inputPanel, StringBuilder sb);

    protected boolean checkEmpty(String str, String field, StringBuilder sb) {
        if (!StringUtil.isOkString(str)) {
            sb.append(field).append(": Should not be empty.\n");
            return true;
        }
        return false;
    }

    protected void checkBoolean(String str, String field, StringBuilder sb) {
        if (StringUtil.isOkString(str) && (!"true".equals(str) && !"false".equals(str))) {
            sb.append(field).append(": Should be \"true\", \"false\" or empty.\n");
        }
    }

    protected boolean checkInteger(String str, String field, StringBuilder sb) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            sb.append(field).append(": Should be correct number(s).\n");
            return false;
        }
    }

    protected void checkDouble(String str, String field, StringBuilder sb) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            sb.append(field).append(": Should be correct number.\n");
        }
    }

    protected void checkList(List list, String field, StringBuilder sb) {
        if (list == null || list.isEmpty()) {
            sb.append(field).append(": Should contain at least 1 element.\n");
        }
    }

    protected void checkDate(String str, String field, StringBuilder sb) {
        try {
            new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str);
        } catch (ParseException e) {
            sb.append(field).append(": Should be \"yyyy-MM-dd HH:mm\"-formatted date.\n");
        }
    }
}
