package com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel;

import com.belkatechnologies.configeditor.model.Application;
import com.belkatechnologies.utils.SpringUtil;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nikita Khvorov
 * Date: 05.04.13
 */
public class AppInputPanel extends JPanel {
    private Map<String, JTextField> inputs;

    public AppInputPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(getInputsPanel());
        add(new JButton("SAVE"));
    }

    private JPanel getInputsPanel() {
        JPanel inputsPanel = new JPanel(new SpringLayout());
        inputs = new HashMap<String, JTextField>();
        Field[] fields = Application.class.getDeclaredFields();
        for (Field field : Application.class.getDeclaredFields()) {
            if (!"offers".equals(field.getName())) {
                JLabel label = new JLabel(field.getName(), JLabel.TRAILING);
                inputsPanel.add(label);
                JTextField textField = new JTextField(20);
                label.setLabelFor(textField);
                inputsPanel.add(textField);
                if (field.getName().equals("words")) {
                    textField.setFocusable(false);
                    inputsPanel.add(new JButton("Add"));
                    inputsPanel.add(new JButton("Remove"));
                } else {
                    inputsPanel.add(new JPanel());
                    inputsPanel.add(new JPanel());
                }
                inputs.put(field.getName(), textField);
            }
        }
        SpringUtil.makeCompactGrid(inputsPanel, fields.length - 1, 4, 0, 0, 7, 7);
        return inputsPanel;
    }
}
