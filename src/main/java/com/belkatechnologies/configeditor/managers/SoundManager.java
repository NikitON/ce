package com.belkatechnologies.configeditor.managers;

/**
 * Author: Nikita Khvorov
 * Date: 03.04.13
 */
public class SoundManager {
    private static SoundManager ourInstance = new SoundManager();

    public static SoundManager getInstance() {
        return ourInstance;
    }

    private SoundManager() {

    }

}
