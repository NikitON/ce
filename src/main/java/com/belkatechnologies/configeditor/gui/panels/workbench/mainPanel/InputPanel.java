package com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel;

import com.belkatechnologies.configeditor.listeners.workbench.AddListener;
import com.belkatechnologies.configeditor.listeners.workbench.RemoveListener;
import org.apache.commons.beanutils.PropertyUtils;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
    protected Map<String, JTextComponent> inputs;
    protected Map<String, JComboBox> comboInputs;
    protected JButton saveButton;
    protected Object edited;

    protected InputPanel() {
        this.inputCount = new AtomicInteger(0);
        this.saveButton = new JButton("SAVE");
        this.listsMap = new HashMap<>();
        this.inputs = new HashMap<>();
        this.comboInputs = new HashMap<>();
        this.ignored = new ArrayList<>();
        this.complex = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initLists();
        add(getInputsPanel());
        add(saveButton);
    }

    protected InputPanel(Object object) {
        this();
        if (object != null) {
            this.edited = object;
            fillInputs();
            initSaveButtonListener(true);
        } else {
            initSaveButtonListener(false);
        }
    }

    protected abstract void fillInputs();

    protected abstract void initLists();

    protected abstract void initSaveButtonListener(boolean replace);

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
                    addSpecialInput(inputsPanel, fieldName);
                } else {
                    addSimpleInput(inputsPanel, fieldName);
                }
            }
        }
    }

    protected void fillInputs(Object object, Field[] fields) {
        for (Field field : fields) {
            String fieldName = field.getName();
            if (!ignored.contains(fieldName)) {
                try {
                    if (listsMap.containsKey(fieldName)) {
                        List list = (List) PropertyUtils.getProperty(object, fieldName);
                        inputs.get(fieldName).setText(listToString(list));
                        listsMap.put(fieldName, list);
                    } else if (!complex.contains(fieldName)) {
                        inputs.get(fieldName).setText((String) PropertyUtils.getProperty(object, fieldName));
                    }
                } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException ignored) {
                }
            }
        }
    }

    protected void addSimpleInput(JPanel inputsPanel, String name) {
        JTextField textField = new JTextField(20);
        inputs.put(name, textField);
        addRow(inputsPanel, name, textField, new JPanel(), new JPanel());
    }

    protected void addSpecialInput(JPanel inputsPanel, String name) {
        addSpecialInput(inputsPanel, name, new JButton("Modify"));
    }

    protected void addSpecialInput(JPanel inputsPanel, String name, JComponent component) {
        addRow(inputsPanel, name, component, new JPanel(), new JPanel());
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

    public String getParam(String name) {
        return inputs.get(name).getText();
    }

    public String getComboParam(String name) {
        return comboInputs.get(name).getSelectedItem().toString();
    }

    public abstract Object getObject(String name);

    protected String listToString(List<?> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        return list.toString();
    }

    public Object getEdited() {
        return edited;
    }
}
