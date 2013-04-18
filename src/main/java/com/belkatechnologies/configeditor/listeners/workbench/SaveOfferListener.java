package com.belkatechnologies.configeditor.listeners.workbench;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.checkers.offer.IDChecker;
import com.belkatechnologies.configeditor.gui.GUI;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.configeditor.managers.TreeManager;
import com.belkatechnologies.configeditor.model.Checker;
import com.belkatechnologies.configeditor.model.Offer;
import com.belkatechnologies.configeditor.model.OfferStep;
import com.belkatechnologies.configeditor.model.Targeting;
import com.belkatechnologies.utils.StringUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nikita Khvorov
 * Date: 09.04.13
 */
public class SaveOfferListener implements ActionListener {
    private InputPanel inputPanel;
    private boolean replace;

    public SaveOfferListener(InputPanel inputPanel, boolean replace) {
        this.inputPanel = inputPanel;
        this.replace = replace;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder sb = new StringBuilder();
        checkAll(sb);
        if (StringUtil.isOkString(sb.toString())) {
            GUI.getInstance().showErrorMessageDialog("Creation error", sb.toString());
        } else {
            if (replace) {
                replaceOffer();
            } else {
                insertOffer();
            }
        }
    }

    private void insertOffer() {
        Offer offer = createOfferFromInputPanel();
        String app = inputPanel.getComboParam("appId");
        TreeManager.getInstance().insertOffer(app, offer);
    }

    private void replaceOffer() {
        Offer offer = createOfferFromInputPanel();
        TreeManager.getInstance().replaceOffer((Offer) inputPanel.getEdited(), offer);
    }

    private Offer createOfferFromInputPanel() {
        return new Offer(getParam("id"), getParam("incrementLevel"), getParam("incrementLevelDateOffset"),
                getParam("minLevel"), getParam("newOnly"), getParam("targetURL"), getParam("targetURLFormat"),
                getParam("referralURL"), (ArrayList<String>) getList("images"), getParam("title"),
                getParam("price"), getParam("shortDescriptions"), getParam("description"), getParam("rewardText"),
                (ArrayList<OfferStep>) getList("steps"), (Targeting) getObject("targeting"),
                (Checker) getObject("checker"), (ArrayList<String>) getList("admins"), getParam("showLimit"),
                getParam("clickLimit"), getParam("startDate"), getParam("endDate"), getParam("length"),
                getParam("sleepTime"), getParam("extraParams"), getParam("gameSlogan"));
    }

    private void checkAll(StringBuilder sb) {
        for (Class<? extends InputChecker> clazz : Offer.getCheckers()) {
            try {
                InputChecker checker = clazz.newInstance();
                if (checker instanceof IDChecker && replace) {
                    new IDChecker(false).check(inputPanel, sb);
                } else {
                    checker.check(inputPanel, sb);
                }
            } catch (InstantiationException | IllegalAccessException ignored) {
            }
        }
    }

    private String getParam(String name) {
        return inputPanel.getParam(name);
    }

    private List getList(String name) {
        return inputPanel.getList(name);
    }

    private Object getObject(String name) {
        return inputPanel.getObject(name);
    }
}
