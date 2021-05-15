package com.ctseducare.oauth2.models;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ctseducare.oauth2.entities.Role;
import com.ctseducare.oauth2.entities.User;

public class UserDetailsCustom implements UserDetails {

  private static final long serialVersionUID = 7979568419364461050L;
  
  private User user;
  
  public UserDetailsCustom(User user) {
    this.user = user;  
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // Version 01
    // Attention! The prefix 'ROLE_' is required by Spring Security
    /*
    return user
        .getRoles()
        .stream()
        .map(x -> new SimpleGrantedAuthority("ROLE_" + x.getName()))
        .collect(Collectors.toList());
    */

    // Version 02
    // Attention! The prefix 'ROLE_' is required by Spring Security
    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    for (Role role : user.getRoles()) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getName());
        grantedAuthorities.add(grantedAuthority);
    }
    return grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getLogin();
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
