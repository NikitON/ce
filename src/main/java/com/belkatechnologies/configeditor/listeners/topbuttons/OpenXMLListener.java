package com.belkatechnologies.configeditor.listeners.topbuttons;

import com.belkatechnologies.configeditor.gui.GUI;
import com.belkatechnologies.configeditor.managers.TreeManager;
import org.simpleframework.xml.core.ElementException;
import org.simpleframework.xml.core.PersistenceException;

import javax.swing.*;
import javax.xml.stream.XMLStreamException;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public class OpenXMLListener extends IOXMLListener {
    private JFileChooser fileChooser;

    public OpenXMLListener() {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(CURRENT_DIRECTORY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    GUI.getInstance().runLoading("Opening file " + fileChooser.getSelectedFile().getName());
                    deserializeXML(fileChooser.getSelectedFile());
                }
            }).start();
        }
    }

    protected void deserializeXML(File file) {
        try {
            TreeManager.getInstance().setOpenedFile(file);
            deserializeXML(new FileInputStream(file));
            GUI.getInstance().enableButtons();
        } catch (FileNotFoundException e) {
            notifyWithExceptionMessage(e);
        } finally {
            GUI.getInstance().stopLoading();
        }
    }

    protected void deserializeXML(InputStream inputStream) {
        try {
            TreeManager.getInstance().deserializeXML(inputStream);
        } catch (XMLStreamException e1) {
            notifyWithExceptionMessage(e1);
        } catch (ElementException e1) {
            String message = e1.getMessage();
            int classBegin = message.indexOf("com.belkatechnologies");
            int classEnd = message.indexOf(' ', classBegin);
            int className = message.lastIndexOf('.', classEnd);
            showXMLErrorMessageDialog(message.substring(0, classBegin) + message.substring(className + 1));
        } catch (PersistenceException e1) {
            notifyWithExceptionMessage(e1);
        } catch (Exception e1) {
            notifyWithExceptionTrace(e1);
        }
    }
}
