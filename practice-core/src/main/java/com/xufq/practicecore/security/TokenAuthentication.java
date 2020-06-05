package com.xufq.practicecore.security;

import com.xufq.practicecore.vo.UserVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName TokenAuthentication
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/9/2020 11:34 AM
 * @Version 1.0
 */
public class TokenAuthentication implements Authentication {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private String token;
    private boolean authenticated;
    private List<GrantedAuthority> authorities = new ArrayList<>();
    private UserVo user;

    public TokenAuthentication(String token) {
        this.token = token;
    }

    public TokenAuthentication(UserVo user) {
        this.user = user;
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            this.user.getRoles().forEach(roleVo -> authorities.add(new SimpleGrantedAuthority(roleVo.getRoleCode())));
        }
    }

    @Override
    public String getName() {
        return Objects.nonNull(user) ? user.getAccountName() : token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    private class SimpleGrantedAuthority implements GrantedAuthority {
        private String role;

        public SimpleGrantedAuthority(String role) {
            this.role = role;
        }

        @Override
        public String getAuthority() {
            return role;
        }
    }
}
