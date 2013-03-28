package com.belkatechnologies.configeditor.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

/**
 * Author: Nikita Khvorov
 * Date: 22.03.13
 */
public class TreeBorderFactory {
    public static Border getBorder() {
        Border background = BorderFactory.createLineBorder(new Color(0xD1E0F5), 10);
        Border outer = BorderFactory.createLineBorder(new Color(0x184686), 2);
        Border inner = BorderFactory.createLineBorder(new Color(0xFFFFFF), 3);
        return new CompoundBorder(new CompoundBorder(background, outer), inner);
    }
}
