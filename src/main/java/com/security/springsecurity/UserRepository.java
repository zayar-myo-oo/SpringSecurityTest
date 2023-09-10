package com.security.springsecurity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.springsecurity.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUserName(String userName);
}
