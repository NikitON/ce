package com.belkatechnologies.configeditor.gui.panels;

import com.belkatechnologies.configeditor.gui.Colors;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Nikita Khvorov
 * Date: 02.04.13
 */
public class SelectPanel extends JPanel {
    public SelectPanel() {
        setPreferredSize(new Dimension(300, 500));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Colors.BORDER));
    }
}
