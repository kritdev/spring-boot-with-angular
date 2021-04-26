package com.example.appserver.security.rest;


import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.appserver.security.service.UserService;
import com.example.appserver.security.service.dto.UserDTO;

/**
 * REST controller for managing the current user's account.
 * 
 * (Extracted from jhipster generated code.)
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

  private static class AccountResourceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private AccountResourceException(String message) {
      super(message);
    }
  }

  private final Logger log = LoggerFactory.getLogger(AccountResource.class);

  private final UserService userService;

  public AccountResource(UserService userService) {
    this.userService = userService;
  }

  /**
   * {@code GET  /authenticate} : check if the user is authenticated, and return its login.
   *
   * @param request the HTTP request.
   * @return the login if the user is authenticated.
   */
  @GetMapping("/authenticate")
  public String isAuthenticated(HttpServletRequest request) {
    log.debug("REST request to check if the current user is authenticated");
    return request.getRemoteUser();
  }

  /**
   * {@code GET  /account} : get the current user.
   *
   * @return the current user.
   * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be returned.
   */
  @GetMapping("/account")
  public UserDTO getAccount() {
    return userService.getUserWithAuthorities().map(UserDTO::new)
        .orElseThrow(() -> new AccountResourceException("User could not be found"));
  }


}
