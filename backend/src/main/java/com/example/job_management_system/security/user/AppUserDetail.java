package com.example.job_management_system.security.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;


//@JsonIgnoreProperties(ignoreUnknown = true)
public class AppUserDetail implements UserDetails {

    private AppUser appUser;

    public AppUserDetail(AppUser appUser) {
        this.appUser = appUser;
    }

    public AppUserDetail(){
        // for jackson deserialization
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appUser.getRole().getGrantedAuthorities();
    }


    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return appUser.getUsername();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
