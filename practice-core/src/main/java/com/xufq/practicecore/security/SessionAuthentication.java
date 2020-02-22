package com.xufq.practicecore.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName SessionAuthentication
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/9/2020 11:34 AM
 * @Version 1.0
 */
public class SessionAuthentication implements Authentication {

    private boolean authenticated;
    private List<GrantedAuthority> authorities = new ArrayList<>();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }
}
