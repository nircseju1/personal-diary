package com.diary.demo.authentication;

import lombok.Data;

@Data
public class LoginInfo {

    private String username;
    private String password;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}

