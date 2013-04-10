package com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel;

import com.belkatechnologies.configeditor.listeners.workbench.AddListener;
import com.belkatechnologies.configeditor.listeners.workbench.RemoveListener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: Nikita Khvorov
 * Date: 08.04.13
 */
public abstract class InputPanel extends JPanel {
    protected AtomicInteger inputCount;
    protected Map<String, List<?>> listsMap;
    protected List<String> ignored;
    protected List<String> complex;
    protected Map<String, JTextField> inputs;
    protected JButton saveButton;

    protected InputPanel() {
        this.inputCount = new AtomicInteger(0);
        this.saveButton = new JButton("SAVE");
        this.listsMap = new HashMap<>();
        this.inputs = new HashMap<>();
        this.ignored = new ArrayList<>();
        this.complex = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initLists();
        add(getInputsPanel());
        initSaveButtonListener();
        add(saveButton);
    }

    protected abstract void initLists();

    protected abstract void initSaveButtonListener();

    protected abstract JPanel getInputsPanel();

    protected void addInputs(JPanel inputsPanel, Field[] fields) {
        for (Field field : fields) {
            String fieldName = field.getName();
            if (!ignored.contains(fieldName)) {
                if (listsMap.containsKey(fieldName)) {
                    ParameterizedType listType = (ParameterizedType) field.getGenericType();
                    Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];
                    addListInput(inputsPanel, fieldName,
                            new AddListener<>(this, fieldName, listClass),
                            new RemoveListener(this, fieldName));
                } else if (complex.contains(fieldName)) {
                    addComplexInput(inputsPanel, fieldName);
                } else {
                    addSimpleInput(inputsPanel, fieldName);
                }
            }
        }
    }

    protected void addSimpleInput(JPanel inputsPanel, String name) {
        JTextField textField = new JTextField(20);
        inputs.put(name, textField);
        addRow(inputsPanel, name, textField, new JPanel(), new JPanel());
    }

    protected void addComplexInput(JPanel inputsPanel, String name) {
        addRow(inputsPanel, name, new JButton("Modify"), new JPanel(), new JPanel());
    }

    protected void addListInput(JPanel inputsPanel, String name, ActionListener addListener,
                                ActionListener removeListener) {
        JTextField textField = new JTextField(20);
        textField.setFocusable(false);
        inputs.put(name, textField);
        JButton add = new JButton("Add");
        add.addActionListener(addListener);
        JButton remove = new JButton("Remove");
        remove.addActionListener(removeListener);
        addRow(inputsPanel, name, textField, add, remove);
    }

    protected void addRow(JPanel holder, String name, JComponent component1, JComponent component2,
                          JComponent component3) {
        JLabel label = new JLabel(name, JLabel.TRAILING);
        label.setLabelFor(component1);
        holder.add(label);
        holder.add(component1);
        holder.add(component2);
        holder.add(component3);
        inputCount.incrementAndGet();
    }

    public void refresh() {
        for (Map.Entry<String, List<?>> entry : listsMap.entrySet()) {
            inputs.get(entry.getKey()).setText(listToString(entry.getValue()));
        }
    }

    public List getList(String name) {
        return listsMap.get(name);
    }

    protected String listToString(List<?> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        return list.toString();
    }
}
