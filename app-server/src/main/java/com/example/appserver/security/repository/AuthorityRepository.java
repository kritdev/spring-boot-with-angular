package com.example.appserver.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.appserver.security.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 * 
 * (Extracted from jhipster generated code.)
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
