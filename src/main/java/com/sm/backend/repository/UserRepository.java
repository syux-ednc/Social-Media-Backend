package com.sm.backend.repository;

import com.sm.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    Page<User> findAllByRole(Pageable pageable, String role);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
