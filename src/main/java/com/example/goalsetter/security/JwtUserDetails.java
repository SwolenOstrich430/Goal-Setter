package com.example.goalsetter.security;

import com.example.goalsetter.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class JwtUserDetails implements UserDetails {

    private Set<GrantedAuthority> authorities;
    private String password;
    private String username;
    private Long id;
    private boolean isAccountExpired;
    private boolean isAccountLocked;
    private boolean areCredentialsExpired;
    private boolean isEnabled;

    public JwtUserDetails(User user) {
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.id = user.getId();
        this.isAccountExpired = false;
        this.areCredentialsExpired = false;
        this.isEnabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isAccountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !areCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setAccountExpired(boolean accountExpired) {
        isAccountExpired = accountExpired;
    }

    public void setAccountLocked(boolean accountLocked) {
        isAccountLocked = accountLocked;
    }

    public void setAreCredentialsExpired(boolean areCredentialsExpired) {
        this.areCredentialsExpired = areCredentialsExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Long getId() {
        return id;
    }
}
