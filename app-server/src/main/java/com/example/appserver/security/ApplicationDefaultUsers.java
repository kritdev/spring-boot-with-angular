package com.example.appserver.security;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.appserver.security.domain.Authority;
import com.example.appserver.security.domain.User;
import com.example.appserver.security.repository.AuthorityRepository;
import com.example.appserver.security.repository.UserRepository;

@Configuration
public class ApplicationDefaultUsers {

  private final Logger log = LoggerFactory.getLogger(ApplicationDefaultUsers.class);

  private final UserRepository userRepository;
  private final AuthorityRepository authorityRepository;

  public ApplicationDefaultUsers(UserRepository userRepository, AuthorityRepository authorityRepository) {
    this.userRepository = userRepository;
    this.authorityRepository = authorityRepository;
  }

  @Bean
  public void CreateDefaultAdminUser() {
    Authority defaultAuthority = null;

    int authorityCount = authorityRepository.findAll().size();
    if (authorityCount == 0) {
      defaultAuthority = new Authority();
      defaultAuthority.setName(SecurityConstants.ADMIN_ROLE);

      log.info("Creating default admin authority....");
      defaultAuthority = authorityRepository.save(defaultAuthority);
    }

    // If no user in database, create default user
    int userCount =
        userRepository.findAllByLoginNot(null, SecurityConstants.ANONYMOUS_USER).getSize();
    if (userCount == 0) {
      User admin = new User();
      admin.setLogin("admin");
      admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
      admin.setActivated(true);
      admin.setCreatedBy(SecurityConstants.SYSTEM_ACCOUNT);
      admin.setAuthorities(Set.of(defaultAuthority));

      log.info("Creating default admin user....");
      userRepository.save(admin);
    }
  }
}
