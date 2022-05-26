package com.code.social.repository;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.code.social.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(@NotBlank String email);

	Boolean existsByUsername(@NotBlank String username);

	Boolean existsByEmail(@NotBlank String email);
	default User getUser(UserDetails currentUser) {
		return getUserByName(currentUser.getUsername());
	}
	
	User findByUsername(@NotBlank String username);
	  default User getUserByName(String username) {
			return findByUsername(username);
		}
}
