package com.belkatechnologies.configeditor.managers;

import com.belkatechnologies.configeditor.gui.GUI;
import com.belkatechnologies.configeditor.model.Application;
import com.belkatechnologies.configeditor.model.BORConfig;
import com.belkatechnologies.configeditor.model.Credentials;
import com.belkatechnologies.configeditor.model.Offer;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public class TreeManager {
    private static TreeManager ourInstance = new TreeManager();

    private JTree tree;
    private BORConfig borConfig;
    private File openedFile;

    public static TreeManager getInstance() {
        return ourInstance;
    }

    private TreeManager() {
    }

    public void setOpenedFile(File openedFile) {
        this.openedFile = openedFile;
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

    public void deserializeXML(InputStream inputStream) throws Exception {
        Serializer serializer = new Persister();
        borConfig = serializer.read(BORConfig.class, inputStream);
        rebuildPanelTree();
    }

    private void rebuildPanelTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("bor");
        for (Application application : borConfig.getApps()) {
            DefaultMutableTreeNode appNode = new DefaultMutableTreeNode(application.getId());
            for (Offer offer : application.getOffers()) {
                appNode.add(new DefaultMutableTreeNode(offer.getId()));
            }
            root.add(appNode);
        }
        tree = new JTree(root);
        GUI.getInstance().repaintTreePanel(tree);
    }

    private Serializer getDefaultSerializer() {
        return new Persister(new Format(4));
    }
}
