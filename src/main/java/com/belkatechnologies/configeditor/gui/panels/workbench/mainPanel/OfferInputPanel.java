package com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel;

import com.belkatechnologies.configeditor.model.Offer;
import com.belkatechnologies.utils.SpringUtil;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nikita Khvorov
 * Date: 05.04.13
 */
public class OfferInputPanel extends JPanel {
    private Map<String, JTextField> inputs;
    private JPanel inputsPanel;

    public OfferInputPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputsPanel = getInputsPanel();
        add(inputsPanel);
        add(new JButton("SAVE"));
    }

    public JPanel getInputsPanel() {
        JPanel inputsPanel = new JPanel(new SpringLayout());
        inputs = new HashMap<String, JTextField>();
        addSimpleInput(inputsPanel, "APP ID");
        Field[] fields = Offer.class.getDeclaredFields();
        for (Field field : Offer.class.getDeclaredFields()) {
            String fieldName = field.getName();
            switch (fieldName) {
                case "images":
                case "steps":
                case "admins":
                    addListInput(inputsPanel, fieldName);
                    break;
                case "targeting":
                case "checker":
                    addComplexInput(inputsPanel, fieldName);
                    break;
                default:
                    addSimpleInput(inputsPanel, fieldName);
            }
        }
        SpringUtil.makeCompactGrid(inputsPanel, fields.length + 1, 4, 0, 0, 7, 7);
        return inputsPanel;
    }

    private void addSimpleInput(JPanel inputsPanel, String name) {
        JTextField textField = new JTextField(20);
        inputs.put(name, textField);
        addRow(inputsPanel, name, textField, new JPanel(), new JPanel());
    }

    private void addComplexInput(JPanel inputsPanel, String name) {
        addRow(inputsPanel, name, new JButton("Modify"), new JPanel(), new JPanel());
    }

    private void addListInput(JPanel inputsPanel, String name) {
        JTextField textField = new JTextField(20);
        textField.setFocusable(false);
        addRow(inputsPanel, name, textField, new JButton("Add"), new JButton("Remove"));
    }

    private void addRow(JPanel holder, String name, JComponent component1, JComponent component2, JComponent component3) {
        JLabel label = new JLabel(name, JLabel.TRAILING);
        label.setLabelFor(component1);
        holder.add(label);
        holder.add(component1);
        holder.add(component2);
        holder.add(component3);
    }
}
