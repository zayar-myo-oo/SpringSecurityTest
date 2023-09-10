package com.security.springsecurity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.springsecurity.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
