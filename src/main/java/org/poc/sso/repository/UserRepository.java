package org.poc.sso.repository;

import org.poc.sso.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

 
/**
 * @author SPrassanna
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
