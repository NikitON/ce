package com.belkatechnologies.configeditor.listeners.workbench;

import com.belkatechnologies.configeditor.gui.GUI;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.utils.StringUtil;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Author: Nikita Khvorov
 * Date: 08.04.13
 */
public class AddListener<E> implements ActionListener {
    private InputPanel inputPanel;
    private Constructor<E> constructor;
    private String name;
    private JComponent[] dialogInputs;

    public AddListener(InputPanel inputPanel, String name, Class<E> clazz) {
        this.inputPanel = inputPanel;
        this.name = name;
        if (clazz.equals(String.class)) {
            initStringClass(clazz);
        } else {
            initObjectClass(clazz);
        }
    }

    private void initObjectClass(Class<E> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        this.dialogInputs = new JComponent[fields.length * 2];
        for (int i = 0; i < fields.length; i++) {
            this.dialogInputs[2 * i] = new JLabel(fields[i].getName());
            this.dialogInputs[2 * i + 1] = (i == 0) ? getTextField() : getTextArea();
        }
        Class[] classes = new Class[fields.length];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = String.class;
        }
        try {
            this.constructor = clazz.getConstructor(classes);
        } catch (NoSuchMethodException ignored) {
        }
    }

    private void initStringClass(Class<E> clazz) {
        this.dialogInputs = new JComponent[]{new JLabel("ID"), getTextField()};
        try {
            this.constructor = clazz.getConstructor(String.class);
        } catch (NoSuchMethodException ignored) {
        }
    }

    private JTextField getTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(350, 20));
        return textField;
    }

    private JTextArea getTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(350, 50));
        textArea.setLineWrap(true);
        return textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String singular = StringUtil.getSingular(name);
        int result = JOptionPane.showConfirmDialog(null, dialogInputs, "Add " + singular,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        if (result == JOptionPane.OK_OPTION) {
            if (isListContains(getIdFromInputs())) {
                GUI.getInstance().showAttentionMessageDialog(singular + " already exists");
            } else {
                try {
                    inputPanel.getList(name).add(constructor.newInstance(getValuesFromInputs()));
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException ignored) {
                }
            }
        }
        inputPanel.refresh();
    }

    private Object[] getValuesFromInputs() {
        int fieldsCount = dialogInputs.length / 2;
        Object[] values = new Object[fieldsCount];
        for (int i = 0; i < fieldsCount; i++) {
            values[i] = getInputTextByIndex(i);
        }
        return values;
    }

    private String getIdFromInputs() {
        return getInputTextByIndex(0);
    }

    private String getInputTextByIndex(int index) {
        return ((JTextComponent) dialogInputs[2 * index + 1]).getText();
    }

    private boolean isListContains(String id) {
        for (Object entry : inputPanel.getList(name)) {
            if (entry.toString().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
