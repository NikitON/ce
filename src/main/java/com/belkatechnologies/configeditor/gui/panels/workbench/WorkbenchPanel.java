package com.belkatechnologies.configeditor.gui.panels.workbench;

import com.belkatechnologies.configeditor.gui.ButtonsStateToggler;
import com.belkatechnologies.configeditor.gui.Colors;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.AddAppView;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.AddOfferView;
import com.belkatechnologies.configeditor.model.Application;
import com.belkatechnologies.configeditor.model.Offer;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Nikita Khvorov
 * Date: 04.04.13
 */
public class WorkbenchPanel extends JPanel implements ButtonsStateToggler {
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private WorkbenchButtons buttonsPanel;

    public WorkbenchPanel() {
        setLayout(new BorderLayout());
        mainPanel = new JPanel();
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Colors.BORDER));
        add(scrollPane, BorderLayout.CENTER);
        buttonsPanel = new WorkbenchButtons();
        add(buttonsPanel, BorderLayout.PAGE_END);
    }

    public void showAddAppView(Application app) {
        showAddAppView(app, false);
    }

    public void showAddAppView(Application app, boolean copying) {
        showView(new AddAppView(app, copying));
    }

    public void showAddOfferView(Offer offer) {
        showAddOfferView(offer, false);
    }

    public void showAddOfferView(Offer offer, Application app) {
        showView(new AddOfferView(offer, false, app));
    }

    public void showAddOfferView(Offer offer, boolean copying) {
        showView(new AddOfferView(offer, copying));
    }

    private void showView(JPanel panel) {
        remove(scrollPane);
        mainPanel = panel;
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Colors.BORDER));
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    @Override
    public void enableButtons() {
        buttonsPanel.enableButtons();
    }

    @Override
    public void disableButtons() {
        buttonsPanel.disableButtons();
    }

    @Override
    public void toggleButtons() {
        buttonsPanel.toggleButtons();
    }
}
