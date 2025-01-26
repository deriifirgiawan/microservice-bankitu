package com.bankitu.user_service.repository;

import com.bankitu.user_service.entity.role.Role;
import com.bankitu.user_service.entity.role.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findUserByRoleType(RoleType roleType);
}
