package com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel;

import com.belkatechnologies.configeditor.listeners.workbench.EditListener;
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

    public OfferInputPanel(Object object, boolean copying) {
        super(object, copying);
    }

    @Override
    protected void initListsAndObjects() {
        listsMap.put("admins", new ArrayList<Object>());
        listsMap.put("images", new ArrayList<Object>());
        listsMap.put("steps", new ArrayList<Object>());
        complex.add("targeting");
        complex.add("checker");
        ignored.add("CHECKERS");
        Offer offer = (Offer) edited;
        if (edited != null) {
            this.targeting = offer.getTargeting();
            this.checker = offer.getChecker();
        }
    }

    @Override
    protected void initSaveButtonListener() {
        saveButton.addActionListener(new SaveOfferListener(this, !copying));
    }

    @Override
    protected JPanel getInputsPanel() {
        JPanel inputsPanel = new JPanel(new SpringLayout());
        addAppIdInput(inputsPanel);
        if (edited != null) {
            Offer offer = (Offer) edited;
            comboInputs.get("appId").setSelectedItem(TreeManager.getInstance().getAppByOffer(offer));
            comboInputs.get("appId").setEnabled(copying);
        }
        Field[] fields = Offer.class.getDeclaredFields();
        addInputs(inputsPanel, fields);
        SpringUtil.makeCompactGrid(inputsPanel, inputCount.get(), 4, 0, 0, 7, 7);
        return inputsPanel;
    }

    private void addAppIdInput(JPanel inputsPanel) {
        List<Application> apps = TreeManager.getInstance().getApps();
        JComboBox<Application> comboBox = new JComboBox<Application>(apps.toArray(new Application[apps.size()]));
        comboInputs.put("appId", comboBox);
        addRow(inputsPanel, "APP ID", comboBox);
    }

    @Override
    protected void addSpecialInput(JPanel inputsPanel, String name) {
        if (name.equals("targeting")) {
            addSpecialInput(inputsPanel, name, new EditListener<Targeting>(this, name, Targeting.class));
        } else if (name.equals("checker")) {
            addSpecialInput(inputsPanel, name, new EditListener<Checker>(this, name, Checker.class));
        }
    }

    @Override
    public Object getObject(String name) {
        if (name.equals("targeting")) {
            return targeting;
        } else if (name.equals("checker")) {
            return checker;
        } else {
            return null;
        }
    }

    @Override
    public void setObject(String name, Object object) {
        if (name.equals("targeting")) {
            targeting = (Targeting) object;
        } else if (name.equals("checker")) {
            checker = (Checker) object;
        }
    }
}
