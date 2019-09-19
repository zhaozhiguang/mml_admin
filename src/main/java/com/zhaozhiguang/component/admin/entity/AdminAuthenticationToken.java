package com.zhaozhiguang.component.admin.entity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 后台登录Authentication
 * @author zhaozhiguang
 */
public class AdminAuthenticationToken implements Authentication {

    private UserDetails sysUser;

    private boolean authenticated;

    public AdminAuthenticationToken() {

    }

    public AdminAuthenticationToken(UserDetails sysUser, boolean authenticated) {
        this.sysUser = sysUser;
        this.authenticated = authenticated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return sysUser.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return sysUser.getPassword();
    }

    @Override
    public Object getDetails() {
        return sysUser;
    }

    @Override
    public Object getPrincipal() {
        return sysUser;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return sysUser.getUsername();
    }

}
