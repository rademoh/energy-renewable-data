package com.energyinvestmentdata.shared.dto;


import com.energyinvestmentdata.entity.RoleEntity;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Rabiu Ademoh
 */
public class UserDto implements Serializable {

    private Long id;

    private String userId;

    private String username;

    private String password;

    private String emailAddress;

    private String token;

    private Collection<RoleEntity> roles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
