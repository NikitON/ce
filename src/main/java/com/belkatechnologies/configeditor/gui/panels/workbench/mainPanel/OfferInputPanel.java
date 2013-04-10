package com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel;

import com.belkatechnologies.configeditor.listeners.workbench.SaveOfferListener;
import com.belkatechnologies.configeditor.model.Offer;
import com.belkatechnologies.utils.SpringUtil;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Author: Nikita Khvorov
 * Date: 05.04.13
 */
public class OfferInputPanel extends InputPanel {
    @Override
    protected void initLists() {
        listsMap.put("admins", new ArrayList<>());
        listsMap.put("images", new ArrayList<>());
        listsMap.put("steps", new ArrayList<>());
        complex.add("targeting");
        complex.add("checker");
    }

    @Override
    protected void initSaveButtonListener() {
        saveButton.addActionListener(new SaveOfferListener());
    }

    @Override
    protected JPanel getInputsPanel() {
        JPanel inputsPanel = new JPanel(new SpringLayout());
        addSimpleInput(inputsPanel, "APP ID");
        Field[] fields = Offer.class.getDeclaredFields();
        addInputs(inputsPanel, fields);
        SpringUtil.makeCompactGrid(inputsPanel, inputCount.get(), 4, 0, 0, 7, 7);
        return inputsPanel;
    }
}
