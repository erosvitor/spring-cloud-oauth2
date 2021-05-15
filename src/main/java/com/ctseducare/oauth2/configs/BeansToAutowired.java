package com.ctseducare.oauth2.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.ctseducare.oauth2.utils.MD5Encoder;

@Configuration
public class BeansToAutowired {

  @Bean
  public MD5Encoder md5Utils() {
    return new MD5Encoder();
  }
  
  @Value("${jwt.secret}")
  private String jwtSecret;

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
    tokenConverter.setSigningKey(jwtSecret);  // key to sign the token
    return tokenConverter;
  }

  @Bean
  public JwtTokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());  // Reads token information
  }

}
