package com.belkatechnologies.configeditor.managers;

import com.belkatechnologies.configeditor.model.BORConfig;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public class TreeManager {
    private static TreeManager ourInstance = new TreeManager();

    private JPanel treePanel;
    private BORConfig borConfig;
    private String fileName;

    public static TreeManager getInstance() {
        return ourInstance;
    }

    private TreeManager() {
        treePanel = new JPanel();
        treePanel.setPreferredSize(new Dimension(300, 500));
        treePanel.setBackground(new Color(0xD1E0F5));
    }

    public JPanel getTreePanel() {
        return treePanel;
    }

    public String getFileName() {
        return fileName;
    }

    public void serializeTree(File file) throws IOException {

    }

    public void buildXMLTree(File file) throws Exception {
        fileName = file.getAbsolutePath();
        buildXMLTree(new FileInputStream(file));
    }

    private void buildXMLTree(InputStream inputStream) throws Exception {
        Serializer serializer = new Persister();
        borConfig = serializer.read(BORConfig.class, inputStream);
        System.out.println(1);
    }
}
