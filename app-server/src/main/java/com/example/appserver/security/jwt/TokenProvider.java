package com.example.appserver.security.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import com.example.appserver.security.config.ApplicationSecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/*
 * (Extracted from jhipster generated code.)
 */
@Component
public class TokenProvider {

  private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

  private static final String AUTHORITIES_KEY = "auth";

  private Key key;

  private long tokenValidityInMilliseconds;

  private long tokenValidityInMillisecondsForRememberMe;

  private final ApplicationSecurityProperties applicationSecurityProperties;

  public TokenProvider(ApplicationSecurityProperties applicationSecurityProperties) {
    this.applicationSecurityProperties = applicationSecurityProperties;
  }

  @PostConstruct
  public void init() {
    byte[] keyBytes = Decoders.BASE64
        .decode(applicationSecurityProperties.getSecurity_Authentication_Jwt_Base64Secret());
    log.debug("Using a Base64-encoded JWT secret key");

    this.key = Keys.hmacShaKeyFor(keyBytes);
    this.tokenValidityInMilliseconds = 1000
        * applicationSecurityProperties.getSecurity_Authentication_Jwt_TokenValidityInSeconds();
    this.tokenValidityInMillisecondsForRememberMe = 1000 * applicationSecurityProperties
        .getSecurity_Authentication_Jwt_TokenValidityInSecondsForRememberMe();
  }

  public String createToken(Authentication authentication, boolean rememberMe) {
    String authorities = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

    long now = (new Date()).getTime();
    Date validity;
    if (rememberMe) {
      validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
    } else {
      validity = new Date(now + this.tokenValidityInMilliseconds);
    }

    return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities)
        .signWith(key, SignatureAlgorithm.HS512).setExpiration(validity).compact();
  }

  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

    Collection<? extends GrantedAuthority> authorities =
        Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
            .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    User principal = new User(claims.getSubject(), "", authorities);

    return new UsernamePasswordAuthenticationToken(principal, token, authorities);
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      log.info("Invalid JWT token.");
      log.trace("Invalid JWT token trace.", e);
    }
    return false;
  }
}
