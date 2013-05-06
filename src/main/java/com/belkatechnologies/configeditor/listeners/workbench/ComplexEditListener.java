package com.belkatechnologies.configeditor.listeners.workbench;

import com.belkatechnologies.configeditor.gui.GUI;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.utils.StringUtil;
import org.apache.commons.beanutils.PropertyUtils;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Author: Nikita Khvorov
 * Date: 06.05.13
 */
public class ComplexEditListener<E> implements ActionListener {
    private InputPanel inputPanel;
    private Constructor<E> constructor;
    private String name;
    private JComponent[] dialogInputs;

    public ComplexEditListener(InputPanel inputPanel, String name, Class<E> clazz) {
        this.inputPanel = inputPanel;
        this.name = name;
        if (clazz.equals(String.class)) {
            initStringClass(clazz);
        } else {
            initObjectClass(clazz);
        }
    }

    protected void initObjectClass(Class<E> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        this.dialogInputs = new JComponent[fields.length * 2];
        for (int i = 0; i < fields.length; i++) {
            this.dialogInputs[2 * i] = new JLabel(fields[i].getName());
            this.dialogInputs[2 * i + 1] = (i == 0) ? getTextField() : getTextArea();
        }
        this.dialogInputs[1].setEnabled(false);
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

    private void refreshDialogs(Object object) {
        if (object != null) {
            if (object instanceof String) {
                ((JTextField) dialogInputs[1]).setText(object.toString());
            } else {
                for (int i = 0; i < dialogInputs.length / 2; i++) {
                    String fieldName = ((JLabel) dialogInputs[2 * i]).getText();
                    try {
                        ((JTextComponent) dialogInputs[2 * i + 1]).setText((String) PropertyUtils.getProperty(object,
                                fieldName));
                    } catch (IllegalAccessException ignored) {
                    } catch (NoSuchMethodException ignored) {
                    } catch (InvocationTargetException ignored) {
                    }
                }
            }
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

    private int removeFromList(Object object) {
        List<?> list = inputPanel.getList(name);
        for (Object entry : list) {
            if (entry.equals(object)) {
                int index = list.indexOf(entry);
                list.remove(entry);
                return index;
            }
        }
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<?> list = inputPanel.getList(name);
        if (list.isEmpty()) {
            GUI.getInstance().showAttentionMessageDialog("Nothing to edit");
        } else {
            Object[] possibilities = list.toArray();
            String singular = StringUtil.getSingular(name);
            Object result = JOptionPane.showInputDialog(null, "Select " + singular + " to edit:",
                    "Edit " + singular, JOptionPane.PLAIN_MESSAGE, null, possibilities, list.get(0));
            if (result != null) {
                performEditing(result);
            }
        }
        inputPanel.refresh();
    }

    private void performEditing(Object object) {
        refreshDialogs(object);
        String singular = StringUtil.getSingular(name);
        int result = JOptionPane.showConfirmDialog(null, dialogInputs, "Edit " + singular,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        if (result == JOptionPane.OK_OPTION) {
            int index = removeFromList(object);
            try {
                inputPanel.getList(name).add(index, constructor.newInstance(getValuesFromInputs()));
            } catch (InstantiationException ignored) {
            } catch (IllegalAccessException ignored) {
            } catch (InvocationTargetException ignored) {
            }
        }
        inputPanel.refresh();
    }
}
