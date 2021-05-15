package com.ctseducare.oauth2.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MD5Encoder implements PasswordEncoder {
  
  @Override
  public String encode(CharSequence rawPassword) {
    if (rawPassword == null) {
      throw new IllegalArgumentException("Password can't be null");
    }
    return encoder(rawPassword.toString());
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    if (rawPassword == null) {
      throw new IllegalArgumentException("Password can't be null");
    }
    if (encodedPassword == null || encodedPassword.length() == 0) {
      return false;
    }
    return encodedPassword.equals(encoder(rawPassword.toString()));
  }
  
  private String encoder(String password) {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
      md.update(password.getBytes());
      byte[] digest = md.digest();
      
      StringBuilder encodedPassword = new StringBuilder();
      for (int i = 0; i < digest.length; i++) {
        int parteAlta = ((digest[i] >> 4) & 0xf) << 4;
        int parteBaixa = digest[i] & 0xf;
        if (parteAlta == 0) {
          encodedPassword.append('0');
        }
        encodedPassword.append(Integer.toHexString(parteAlta | parteBaixa));
      }
      
      return encodedPassword.toString();
    } catch (NoSuchAlgorithmException e) {
    }
    return null;
  }
  
}
