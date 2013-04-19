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

    public SimpleEditListener(JTextField appropriateField) {
        this.appropriateField = appropriateField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea textArea = new JTextArea();
        textArea.setText(appropriateField.getText());
        textArea.setLineWrap(true);
        textArea.setPreferredSize(new Dimension(350, 100));
        int result = JOptionPane.showConfirmDialog(null, textArea, "Edit",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        if (result == JOptionPane.OK_OPTION) {
            appropriateField.setText(textArea.getText());
        }
    }
}
