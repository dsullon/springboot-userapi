package com.gradmi.usersapp.users_backend.repositories;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gradmi.usersapp.users_backend.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
   Page<User> findAll(Pageable pageable);
   Optional<User> findByUsername(String name);
}
