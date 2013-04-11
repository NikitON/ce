package com.belkatechnologies.configeditor.listeners.workbench;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.GUI;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.configeditor.model.Application;
import com.belkatechnologies.utils.StringUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Nikita Khvorov
 * Date: 09.04.13
 */
public class SaveAppListener implements ActionListener {
    private InputPanel inputPanel;

    public SaveAppListener(InputPanel inputPanel) {
        this.inputPanel = inputPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder sb = new StringBuilder();
        for (Class<? extends InputChecker> clazz : Application.getCheckers()) {
            try {
                InputChecker checker = clazz.newInstance();
                checker.check(inputPanel, sb);
            } catch (InstantiationException | IllegalAccessException ignored) {
            }
        }
        if (StringUtil.isOkString(sb.toString())) {
            GUI.getInstance().showErrorMessageDialog("Creation error", sb.toString());
        }
    }
}
