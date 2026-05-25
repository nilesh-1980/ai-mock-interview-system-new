package com.example.interview.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.interview.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}