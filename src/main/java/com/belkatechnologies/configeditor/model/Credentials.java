package com.belkatechnologies.configeditor.model;

/**
 * Author: Nikita Khvorov
 * Date: 19.03.13
 */
public class Credentials {
    private String username;
    private String password;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
