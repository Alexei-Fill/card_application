package org.card.application.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetail implements UserDetails {

    ApplicationUser applicationUser;
    public UserDetail(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(applicationUser.getRole().toString()));
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return applicationUser.getPassword();
    }

    @Override
    public String getUsername() {
        return applicationUser.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
