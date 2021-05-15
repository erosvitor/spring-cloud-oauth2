package com.ctseducare.oauth2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ctseducare.oauth2.entities.User;
import com.ctseducare.oauth2.models.UserDetailsCustom;
import com.ctseducare.oauth2.repositories.UserRepository;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
  
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User user = userRepository.findByLogin(login);
    if (user == null) {
      throw new UsernameNotFoundException(String.format("The user %s not exist in system.", login));
    }
    return new UserDetailsCustom(user);
  }

}
