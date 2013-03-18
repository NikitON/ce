package com.belkatechnologies.configeditor.managers;

import com.belkatechnologies.configeditor.model.BORConfig;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public class TreeManager {
    private static TreeManager ourInstance = new TreeManager();

    private JPanel treePanel;
    private BORConfig borConfig;
    private File openedFile;

    public static TreeManager getInstance() {
        return ourInstance;
    }

    private TreeManager() {
        treePanel = new JPanel();
        treePanel.setPreferredSize(new Dimension(400, 500));
        treePanel.setBackground(new Color(0xD1E0F5));
    }

    public JPanel getTreePanel() {
        return treePanel;
    }

    public void serializeOpenedTree() throws Exception {
        serializeTree(openedFile);
    }

    public void serializeTree(File file) throws Exception {
        serializeTree(new FileOutputStream(file));
    }

    public void serializeTree(OutputStream outputStream) throws Exception {
        Format format = new Format(4);
        Serializer serializer = new Persister(format);
        serializer.write(borConfig, outputStream);
    }

    public void deserializeXML(File file) throws Exception {
        openedFile = file;
        deserializeXML(new FileInputStream(file));
    }

    public void deserializeXML(InputStream inputStream) throws Exception {
        Serializer serializer = new Persister();
        borConfig = serializer.read(BORConfig.class, inputStream);
    }
}
