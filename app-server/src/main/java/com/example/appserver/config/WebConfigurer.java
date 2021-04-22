package com.example.appserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfigurer {

  private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

  @Value("${endpoints.cors.allowed-origins}")
  private String allowedOrigins;

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

    if (allowedOrigins != null && !allowedOrigins.isBlank()) {
      log.info("Registering CORS filter ({})", allowedOrigins);
      CorsConfiguration config = new CorsConfiguration();
      config.addAllowedOrigin(allowedOrigins);
      source.registerCorsConfiguration("/api/**", config);
    }

    return new CorsFilter(source);
  }
}
