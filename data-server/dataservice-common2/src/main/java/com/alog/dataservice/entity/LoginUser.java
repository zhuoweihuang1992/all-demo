package com.alog.dataservice.entity;

import java.io.Serializable;

public class LoginUser {
    private String username;
    private String password;
    //private Integer rememberMe;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
