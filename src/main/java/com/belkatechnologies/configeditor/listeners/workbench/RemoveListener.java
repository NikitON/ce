package com.belkatechnologies.configeditor.listeners.workbench;

import com.belkatechnologies.configeditor.gui.GUI;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.utils.StringUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Author: Nikita Khvorov
 * Date: 08.04.13
 */
public class RemoveListener implements ActionListener {
    private InputPanel inputPanel;
    private String name;

    public RemoveListener(InputPanel inputPanel, String name) {
        this.inputPanel = inputPanel;
        this.name = name;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<?> list = inputPanel.getList(name);
        if (list.isEmpty()) {
            GUI.getInstance().showAttentionMessageDialog("Nothing to remove");
        } else {
            Object[] possibilities = list.toArray();
            String singular = StringUtil.getSingular(name);
            Object result = JOptionPane.showInputDialog(null, "Select " + singular + " to remove:",
                    "Remove " + singular, JOptionPane.PLAIN_MESSAGE, null, possibilities, list.get(0));
            if (result != null) {
                list.remove(result);
            }
        }
        inputPanel.refresh();
    }
}
