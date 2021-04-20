package com.example.appserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer {

  @Value("${endpoints.cors.allowed-origins}")
  private String allowedOrigins;

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        if (allowedOrigins == null || allowedOrigins.isBlank())
          return;

        System.out.println("*** WebMvcConfigurer allowedOrigins = " + allowedOrigins);
        registry.addMapping("/**").allowedOrigins(allowedOrigins);
      }
    };
  }
}
