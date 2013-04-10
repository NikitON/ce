package com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel;

import com.belkatechnologies.configeditor.listeners.workbench.SaveAppListener;
import com.belkatechnologies.configeditor.model.Application;
import com.belkatechnologies.utils.SpringUtil;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Author: Nikita Khvorov
 * Date: 05.04.13
 */
public class AppInputPanel extends InputPanel {
    @Override
    protected void initLists() {
        listsMap.put("words", new ArrayList<>());
        ignored.add("offers");
    }

    @Override
    protected void initSaveButtonListener() {
        saveButton.addActionListener(new SaveAppListener(this));
    }

    @Override
    protected JPanel getInputsPanel() {
        JPanel inputsPanel = new JPanel(new SpringLayout());
        Field[] fields = Application.class.getDeclaredFields();
        addInputs(inputsPanel, fields);
        SpringUtil.makeCompactGrid(inputsPanel, inputCount.get(), 4, 0, 0, 7, 7);
        return inputsPanel;
    }
}
