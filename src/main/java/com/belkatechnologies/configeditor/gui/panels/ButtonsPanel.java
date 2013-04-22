package com.belkatechnologies.configeditor.gui.panels;

import com.belkatechnologies.configeditor.gui.ButtonsStateToggler;

import javax.swing.*;
import java.util.List;

/**
 * Author: Nikita Khvorov
 * Date: 04.04.13
 */
public abstract class ButtonsPanel extends JPanel implements ButtonsStateToggler {
    protected List<JButton> buttons;

    @Override
    public void toggleButtons() {
        if (buttons.get(0).isEnabled()) {
            for (JButton button : buttons) {
                button.setEnabled(false);
            }
        } else {
            for (JButton button : buttons) {
                button.setEnabled(true);
            }
        }
    }

    @Override
    public void enableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
    }

    @Override
    public void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
}
