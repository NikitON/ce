package com.belkatechnologies.configeditor.listeners.workbench;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.checkers.app.IDChecker;
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
    private boolean replace;

    public SaveAppListener(InputPanel inputPanel, boolean replace) {
        this.inputPanel = inputPanel;
        this.replace = replace;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder sb = new StringBuilder();
        checkAll(sb);
        if (StringUtil.isOkString(sb.toString())) {
            GUI.getInstance().showErrorMessageDialog("Creation error", sb.toString());
        } else {
            if (replace) {
                replaceApp();
            } else {
                insertApp();
            }
        }
    }

    private void insertApp() {
        Object[] apps = TreeManager.getInstance().getApps().toArray();
        Object result = JOptionPane.showInputDialog(null, "Insert after: ", "Saving", JOptionPane.PLAIN_MESSAGE,
                null, apps, apps[0]);
        if (result != null) {
            List<Application> appList = TreeManager.getInstance().getApps();
            int index = appList.indexOf(result);
            Application app = createAppFromPanel();
            TreeManager.getInstance().insertApp(index + 1, app);
        }
    }

    private void replaceApp() {
        Application app = createAppFromPanel();
        TreeManager.getInstance().replaceApp((Application) inputPanel.getEdited(), app);
    }

    private Application createAppFromPanel() {
        return new Application(getParam("id"), getParam("explicitRewards"), getParam("link"),
                getParam("defaultRewardValue"), getParam("defaultRewardType"),
                (ArrayList<RewardWord>) inputPanel.getList("words"), getParam("oldUsersTable"), null);
    }

    private void checkAll(StringBuilder sb) {
        for (Class<? extends InputChecker> clazz : Application.getCheckers()) {
            try {
                InputChecker checker = clazz.newInstance();
                if (checker instanceof IDChecker && replace) {
                    new IDChecker(false).check(inputPanel, sb);
                } else {
                    checker.check(inputPanel, sb);
                }
            } catch (InstantiationException ignored) {
            } catch (IllegalAccessException ignored) {

            }
        }
    }

    private String getParam(String name) {
        return inputPanel.getParam(name);
    }
}
