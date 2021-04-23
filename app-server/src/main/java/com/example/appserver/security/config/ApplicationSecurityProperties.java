package com.example.appserver.security.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationSecurityProperties {

  public String getSecurityAuthenticationJwtSecret() {
    return null;
  }

  // .security:
  // ...authentication:
  // .....jwt:
  // .......# This token must be encoded using Base64 and be at least 256 bits long
  // .......# (you can type `openssl rand -base64 64` on your command line to generate a 512 bits
  // one)
  // .......base64-secret:
  // YWZiYmU5YWYyNDI5Njk3MGZjNTgxYzhiNzE3NDA0MTVkNGJkZWZmYTU5NTQwNWQ0M2NhYmUxYTBhZmY1NzUxOWMxODhhOWFjNTcwM2U2NDkzYzNkNjBlNjVhNGQ4MWY3N2ZhNTZkMTE5ZDliZDIxZWUwOGNiOGQwMzc5Yzg1ODQ=
  // .......# Token is valid 24 hours
  // .......token-validity-in-seconds: 86400
  // .......token-validity-in-seconds-for-remember-me: 2592000
  public String getSecurity_Authentication_Jwt_Base64Secret() {
    return "YWZiYmU5YWYyNDI5Njk3MGZjNTgxYzhiNzE3NDA0MTVkNGJkZWZmYTU5NTQwNWQ0M2NhYmUxYTBhZmY1NzUxOWMxODhhOWFjNTcwM2U2NDkzYzNkNjBlNjVhNGQ4MWY3N2ZhNTZkMTE5ZDliZDIxZWUwOGNiOGQwMzc5Yzg1ODQ=";
  }

  public int getSecurity_Authentication_Jwt_TokenValidityInSeconds() {
    return 86400;
  }

  public int getSecurity_Authentication_Jwt_TokenValidityInSecondsForRememberMe() {
    return 2592000;
  }

}
