package com.example.appserver.rest;

import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

/**
 * Utility class for ResponseEntity creation.
 * 
 * (Extracted from jhipster generated code)
 */
public interface AppResponseUtil {

  static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> response) {
    return wrapOrNotFound(response, null);
  }

  static <X> ResponseEntity<X> wrapOrNotFound(X response) {
    return wrapOrNotFound(Optional.of(response), null);
  }

  /**
   * Wrap the optional into a org.springframework.http.ResponseEntity with an
   * org.springframework.http.HttpStatus#OK status with the headers, or if it's empty, throws a
   * org.springframework.web.server.ResponseStatusException with status
   * org.springframework.http.HttpStatus#NOT_FOUND.
   */
  static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> response, HttpHeaders header) {
    return response.map(res -> ResponseEntity.ok().headers(header).body(res))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

}
