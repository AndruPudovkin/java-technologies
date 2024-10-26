package com.example.javaprojectpudovkin.persistent.repository;

import com.example.javaprojectpudovkin.persistent.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
