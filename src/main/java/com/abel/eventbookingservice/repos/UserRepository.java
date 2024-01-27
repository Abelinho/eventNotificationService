package com.abel.eventbookingservice.repos;

import com.abel.eventbookingservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	boolean existsByName(String name);

	boolean existsByEmail(String email);

	Optional<User> findByNameOrEmail(String name, String email);
}
