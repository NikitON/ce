package com.belkatechnologies.configeditor.listeners.workbench;

import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import org.apache.commons.beanutils.PropertyUtils;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Author: Nikita Khvorov
 * Date: 19.04.13
 */
public class EditListener<E> implements ActionListener {
    protected InputPanel inputPanel;
    protected Constructor<E> constructor;
    private String name;
    protected JComponent[] dialogInputs;

    public EditListener(InputPanel inputPanel, String name, Class<E> clazz) {
        this.inputPanel = inputPanel;
        this.name = name;
        initClass(clazz);
    }

    protected void initClass(Class<E> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        this.dialogInputs = new JComponent[fields.length * 2];
        for (int i = 0; i < fields.length; i++) {
            this.dialogInputs[2 * i] = new JLabel(fields[i].getName());
            this.dialogInputs[2 * i + 1] = new JTextField();
        }
        refreshDialogs();
        Class[] classes = new Class[fields.length];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = String.class;
        }
        try {
            this.constructor = clazz.getConstructor(classes);
        } catch (NoSuchMethodException ignored) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        refreshDialogs();
        int result = JOptionPane.showConfirmDialog(null, dialogInputs, "Edit",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        if (result == JOptionPane.OK_OPTION) {
            try {
                inputPanel.setObject(name, constructor.newInstance(getValuesFromInputs()));
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException ignored) {
            }
        }
        inputPanel.refresh();
    }

    private void refreshDialogs() {
        Object object = inputPanel.getObject(name);
        if (object != null) {
            for (int i = 0; i < dialogInputs.length / 2; i++) {
                String fieldName = ((JLabel) dialogInputs[2 * i]).getText();
                try {
                    ((JTextField) dialogInputs[2 * i + 1]).setText((String) PropertyUtils.getProperty(object,
                            fieldName));
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
                }
            }
        }
    }

    private Object[] getValuesFromInputs() {
        int fieldsCount = dialogInputs.length / 2;
        Object[] values = new Object[fieldsCount];
        for (int i = 0; i < fieldsCount; i++) {
            values[i] = getInputTextByIndex(i);
        }
        return values;
    }

    private String getInputTextByIndex(int index) {
        return ((JTextComponent) dialogInputs[2 * index + 1]).getText();
    }
}
