package com.varundublish.omnirio.customerservice.repository;

import com.varundublish.omnirio.customerservice.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleCode(String role);
}
