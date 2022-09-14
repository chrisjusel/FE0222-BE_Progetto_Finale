package it.energyservice.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.energyservice.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findById(Long id);

	public Optional<User> findByUsername(String username);

	public boolean existsByEmail(String email);

	public boolean existsByUsername(String username);

}
