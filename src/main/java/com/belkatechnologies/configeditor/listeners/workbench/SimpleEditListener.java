package com.belkatechnologies.configeditor.listeners.workbench;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Nikita Khvorov
 * Date: 19.04.13
 */
public class SimpleEditListener implements ActionListener {
    private JTextField appropriateField;
    private JTextArea textArea;

    public SimpleEditListener(JTextField appropriateField) {
        this.appropriateField = appropriateField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int result = JOptionPane.showConfirmDialog(null, getSimplePanel(), "Edit",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        if (result == JOptionPane.OK_OPTION) {
            appropriateField.setText(textArea.getText());
        }
    }

    private JPanel getSimplePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        initTextArea();
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(getButtonsPanel(textArea), BorderLayout.LINE_END);
        return panel;
    }

    private JPanel getButtonsPanel(JTextArea textArea) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
        JButton level = new JButton("LEVEL");
        level.addActionListener(new InsertTextListener(textArea, "%R_LEVEL%"));
        JButton value = new JButton("VALUE");
        value.addActionListener(new InsertTextListener(textArea, "%R_VALUE%"));
        JButton type = new JButton("TYPE");
        type.addActionListener(new InsertTextListener(textArea, "%R_TYPE%"));
        panel.add(level);
        panel.add(value);
        panel.add(type);
        return panel;
    }

    private void initTextArea() {
        textArea = new JTextArea();
        textArea.setText(appropriateField.getText());
        textArea.setLineWrap(true);
        textArea.setPreferredSize(new Dimension(350, 100));
    }

    private static class InsertTextListener implements ActionListener {
        private JTextArea textArea;
        private String text;

        private InsertTextListener(JTextArea textArea, String text) {
            this.textArea = textArea;
            this.text = text;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.insert(text, textArea.getCaretPosition());
        }
    }
}
