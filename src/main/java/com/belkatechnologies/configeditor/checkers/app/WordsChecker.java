package com.belkatechnologies.configeditor.checkers.app;

import com.belkatechnologies.configeditor.checkers.InputChecker;
import com.belkatechnologies.configeditor.gui.panels.workbench.mainPanel.InputPanel;
import com.belkatechnologies.configeditor.model.RewardWord;
import com.belkatechnologies.utils.StringUtil;

import java.util.List;

/**
 * Author: Nikita Khvorov
 * Date: 12.04.13
 */
public class WordsChecker extends InputChecker {
    @Override
    public void check(InputPanel inputPanel, StringBuilder sb) {
        List<RewardWord> words = inputPanel.getList("words");
        if (words == null || words.isEmpty()) {
            sb.append("WORDS: Should contain at least 1 word.\n");
        } else {
            for (RewardWord word : words) {
                if (!StringUtil.isOkString(word.getId())) {
                    sb.append("WORDS: Empty word!\n");
                }
                if (!StringUtil.isOkString(word.getForm1()) || !StringUtil.isOkString(word.getForm2()) ||
                        !StringUtil.isOkString(word.getForm3())) {
                    sb.append("WORDS: Empty word's form!\n");
                }
            }
        }
    }
}
