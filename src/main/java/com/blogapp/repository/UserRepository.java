package com.blogapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapp.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
