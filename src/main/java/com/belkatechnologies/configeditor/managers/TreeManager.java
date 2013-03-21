package com.belkatechnologies.configeditor.managers;

import com.belkatechnologies.configeditor.model.BORConfig;
import com.belkatechnologies.configeditor.model.Credentials;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
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
        getDefaultSerializer().write(borConfig, file);
    }

    public void uploadTree(String server, String path, Credentials credentials) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, 21);
            ftpClient.login(credentials.getUsername(), credentials.getPassword());
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            OutputStream outputStream = ftpClient.storeFileStream(path);
            try {
                getDefaultSerializer().write(borConfig, outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deserializeXML(File file) throws Exception {
        openedFile = file;
        deserializeXML(new FileInputStream(file));
    }

    public void deserializeXML(InputStream inputStream) throws Exception {
        Serializer serializer = new Persister();
        borConfig = serializer.read(BORConfig.class, inputStream);
    }

    private static Serializer getDefaultSerializer() {
        return new Persister(new Format(4));
    }
}
