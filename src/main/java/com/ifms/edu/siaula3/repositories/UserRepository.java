package com.ifms.edu.siaula3.repositories;

import com.ifms.edu.siaula3.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
