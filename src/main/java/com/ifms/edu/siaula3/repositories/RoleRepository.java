package com.ifms.edu.siaula3.repositories;

import com.ifms.edu.siaula3.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);
}
