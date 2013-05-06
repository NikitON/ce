package com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel;

import com.belkatechnologies.configeditor.listeners.workbench.AddListener;
import com.belkatechnologies.configeditor.listeners.workbench.ComplexEditListener;
import com.belkatechnologies.configeditor.listeners.workbench.RemoveListener;
import com.belkatechnologies.configeditor.listeners.workbench.SimpleEditListener;
import org.apache.commons.beanutils.PropertyUtils;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
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
    protected boolean copying;

    private InputPanel() {
        this.inputCount = new AtomicInteger(0);
        this.saveButton = new JButton("SAVE");
        this.listsMap = new HashMap<String, List<?>>();
        this.inputs = new HashMap<String, JTextComponent>();
        this.comboInputs = new HashMap<String, JComboBox>();
        this.ignored = new ArrayList<String>();
        this.complex = new ArrayList<String>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    protected InputPanel(Object object, boolean copying) {
        this();
        this.edited = object;
        this.copying = copying;
        initListsAndObjects(null);
        add(getInputsPanel());
        initSaveButtonListener(edited != null);
        add(saveButton);
        if (edited != null) {
            refresh(edited);
        }
    }

    public InputPanel(Object object, boolean copying, Object application) {
        this();
        this.edited = object;
        this.copying = copying;
        initListsAndObjects(application);
        add(getInputsPanel());
        initSaveButtonListener(edited != null);
        add(saveButton);
        if (edited != null) {
            refresh(edited);
        }
    }

    protected abstract void initListsAndObjects(Object object);

    protected abstract void initSaveButtonListener(boolean replace);

    protected abstract JPanel getInputsPanel();

    protected void addInputs(JPanel inputsPanel, Field[] fields) {
        for (Field field : fields) {
            String fieldName = field.getName();
            if (!ignored.contains(fieldName)) {
                if (listsMap.containsKey(fieldName)) {
                    ParameterizedType listType = (ParameterizedType) field.getGenericType();
                    Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];
                    addListInput(inputsPanel, fieldName, new AddListener(this, fieldName, listClass),
                            new ComplexEditListener(this, fieldName, listClass),
                            new RemoveListener(this, fieldName));
                } else if (complex.contains(fieldName)) {
                    addSpecialInput(inputsPanel, fieldName);
                } else {
                    addSimpleInput(inputsPanel, fieldName);
                }
            }
        }
    }

    protected void addSimpleInput(JPanel inputsPanel, String name) {
        JTextField textField = new JTextField(20);
        inputs.put(name, textField);
        JButton button = new JButton("Expand");
        button.setPreferredSize(new Dimension(100, 22));
        button.addActionListener(new SimpleEditListener(textField));
        addRow(inputsPanel, name, textField, button);
    }

    protected abstract void addSpecialInput(JPanel inputsPanel, String name);

    protected void addSpecialInput(JPanel inputsPanel, String name, ActionListener listener) {
        JButton button = new JButton("Edit");
        button.addActionListener(listener);
        button.setPreferredSize(new Dimension(100, 22));
        addSpecialInput(inputsPanel, name, button);
    }

    protected void addSpecialInput(JPanel inputsPanel, String name, JComponent component) {
        JTextField textField = new JTextField(20);
        textField.setFocusable(false);
        inputs.put(name, textField);
        addRow(inputsPanel, name, textField, component);
    }

    protected void addListInput(JPanel inputsPanel, String name, ActionListener addListener,
                                ActionListener editListener, ActionListener removeListener) {
        JTextField textField = new JTextField(20);
        textField.setFocusable(false);
        inputs.put(name, textField);
        JButton add = new JButton("Add");
        add.addActionListener(addListener);
        add.setPreferredSize(new Dimension(100, 22));
        JButton edit = new JButton("Edit");
        edit.addActionListener(editListener);
        edit.setPreferredSize(new Dimension(100, 22));
        JButton remove = new JButton("Remove");
        remove.addActionListener(removeListener);
        remove.setPreferredSize(new Dimension(100, 22));
        addRow(inputsPanel, name, textField, add, edit, remove);
    }

    protected void addRow(JPanel holder, String name, JComponent component) {
        addRow(holder, name, component, new JPanel(), new JPanel(), new JPanel());
    }

    protected void addRow(JPanel holder, String name, JComponent component1, JComponent component2) {
        addRow(holder, name, component1, component2, new JPanel(), new JPanel());
    }

    private void addRow(JPanel holder, String name, JComponent component1, JComponent component2,
                        JComponent component3, JComponent component4) {
        JLabel label = new JLabel(name, JLabel.TRAILING);
        label.setLabelFor(component1);
        holder.add(label);
        holder.add(component1);
        holder.add(component2);
        holder.add(component3);
        holder.add(component4);
        inputCount.incrementAndGet();
    }

    public void refresh() {
        for (Map.Entry<String, List<?>> entry : listsMap.entrySet()) {
            inputs.get(entry.getKey()).setText(listToString(entry.getValue()));
        }
        for (String s : complex) {
            inputs.get(s).setText(getObjectString(s));
        }
    }

    public void refresh(Object object) {
        for (Map.Entry<String, JTextComponent> entry : inputs.entrySet()) {
            String fieldName = entry.getKey();
            if (!complex.contains(fieldName)) {
                try {
                    if (!listsMap.containsKey(fieldName)) {
                        inputs.get(fieldName).setText((String) PropertyUtils.getProperty(object, fieldName));
                    } else {
                        listsMap.put(fieldName, (List) PropertyUtils.getProperty(object, fieldName));
                    }
                } catch (InvocationTargetException ignored) {
                } catch (IllegalAccessException ignored) {
                } catch (NoSuchMethodException ignored) {
                }
            }
        }
        refresh();
    }

    public List getList(String name) {
        return listsMap.get(name);
    }

    public String getParam(String name) {
        return inputs.get(name).getText();
    }

    public String getComboParam(String name) {
        Object object = comboInputs.get(name).getSelectedItem();
        return object == null ? null : object.toString();
    }

    public abstract Object getObject(String name);

    private String getObjectString(String name) {
        Object object = getObject(name);
        if (object == null) {
            return "";
        } else {
            return object.toString();
        }
    }

    protected String listToString(List<?> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        return list.toString();
    }

    public Object getEdited() {
        return edited;
    }

    public abstract void setObject(String name, Object object);
}
