package com.diary.demo.authentication.jwt;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private String name;
    private String email;
    private String userPhotoName;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = accessToken;
        this.username = username;
        this.authorities = authorities;
    }

    public JwtResponse(String accessToken, String username, String name, String email, String userPhotoName,
                       Collection<? extends GrantedAuthority> authorities) {
        this.token = accessToken;
        this.username = username;
        this.name = name;
        this.email = email;
        this.userPhotoName = userPhotoName;
        this.authorities = authorities;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPhotoName() {
        return userPhotoName;
    }

    public void setUserPhotoName(String userPhotoName) {
        this.userPhotoName = userPhotoName;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}

