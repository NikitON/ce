package com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel;

import com.belkatechnologies.configeditor.listeners.workbench.SaveOfferListener;
import com.belkatechnologies.configeditor.managers.TreeManager;
import com.belkatechnologies.configeditor.model.Application;
import com.belkatechnologies.configeditor.model.Checker;
import com.belkatechnologies.configeditor.model.Offer;
import com.belkatechnologies.configeditor.model.Targeting;
import com.belkatechnologies.utils.SpringUtil;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nikita Khvorov
 * Date: 05.04.13
 */
public class OfferInputPanel extends InputPanel {
    private Targeting targeting;
    private Checker checker;

    @Override
    protected void initLists() {
        listsMap.put("admins", new ArrayList<>());
        listsMap.put("images", new ArrayList<>());
        listsMap.put("steps", new ArrayList<>());
        complex.add("targeting");
        complex.add("checker");
        ignored.add("CHECKERS");
    }

    @Override
    protected void initSaveButtonListener() {
        saveButton.addActionListener(new SaveOfferListener(this));
    }

    @Override
    protected JPanel getInputsPanel() {
        JPanel inputsPanel = new JPanel(new SpringLayout());
        addAppIdInput(inputsPanel);
        Field[] fields = Offer.class.getDeclaredFields();
        addInputs(inputsPanel, fields);
        SpringUtil.makeCompactGrid(inputsPanel, inputCount.get(), 4, 0, 0, 7, 7);
        return inputsPanel;
    }

    private void addAppIdInput(JPanel inputsPanel) {
        List<Application> apps = TreeManager.getInstance().getApps();
        JComboBox<Application> comboBox = new JComboBox<>(apps.toArray(new Application[apps.size()]));
        comboInputs.put("appId", comboBox);
        addSpecialInput(inputsPanel, "APP ID", comboBox);
    }

    @Override
    public Object getObject(String name) {
        switch (name) {
            case "targeting":
                return targeting;
            case "checker":
                return checker;
            default:
                return null;
        }
    }
}
