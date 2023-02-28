package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.enums.RoleName;
import com.wokite.net.backendstore.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);

}
