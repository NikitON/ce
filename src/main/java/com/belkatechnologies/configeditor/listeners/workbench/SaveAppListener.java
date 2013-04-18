package com.belkatechnologies.configeditor.listeners.workbench;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.GUI;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.configeditor.managers.TreeManager;
import com.belkatechnologies.configeditor.model.Application;
import com.belkatechnologies.configeditor.model.RewardWord;
import com.belkatechnologies.utils.StringUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
        } else {
            Object[] apps = TreeManager.getInstance().getApps().toArray();
            Object result = JOptionPane.showInputDialog(null, "Insert after: ", "Saving", JOptionPane.PLAIN_MESSAGE,
                    null, apps, apps[0]);
            if (result != null) {
                List<Application> appList = TreeManager.getInstance().getApps();
                int index = appList.indexOf(result);
                Application app = new Application(getParam("id"), getParam("explicitRewards"), getParam("link"),
                        getParam("defaultRewardValue"), getParam("defaultRewardType"),
                        (ArrayList<RewardWord>) inputPanel.getList("words"), getParam("oldUsersTable"), null);
                TreeManager.getInstance().insertApp(index + 1, app);
            }
        }
    }

    private String getParam(String name) {
        return inputPanel.getParam(name);
    }
}
