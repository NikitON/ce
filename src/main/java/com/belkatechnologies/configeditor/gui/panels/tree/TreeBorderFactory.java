package com.belkatechnologies.configeditor.gui.panels.tree;

import com.belkatechnologies.configeditor.gui.Colors;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

/**
 * Author: Nikita Khvorov
 * Date: 22.03.13
 */
public class TreeBorderFactory {
    public static Border getBorder() {
        Border splitter = BorderFactory.createMatteBorder(0, 0, 0, 1, Colors.BORDER);
        Border background = BorderFactory.createLineBorder(Colors.LIGHT_BACK, 10);
        Border outer = BorderFactory.createLineBorder(Colors.BORDER, 2);
        Border inner = BorderFactory.createLineBorder(Colors.WHITE, 3);
        return new CompoundBorder(splitter, new CompoundBorder(background, new CompoundBorder(outer, inner)));
    }
}
