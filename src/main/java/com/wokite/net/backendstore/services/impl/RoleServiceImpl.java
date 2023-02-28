package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Role;
import com.wokite.net.backendstore.repository.RoleRepository;
import com.wokite.net.backendstore.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired private RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long idRole, Role role) {
        if (!roleRepository.existsById(idRole)) {
            throw new ResourceNotFoundException("Role not found");
        }
        Optional<Role> optionalRole = roleRepository.findById(idRole);
        if (!optionalRole.isPresent()) {
            throw new ResourceNotFoundException("Role not found");
        }
        Role roleResult = optionalRole.get();
        roleResult.setName(role.getName());

        return roleRepository.save(roleResult);
    }

    @Override
    public Optional<Role> findRoleById(Long idRole) {
        if (!roleRepository.existsById(idRole)) {
            throw new ResourceNotFoundException("Role not found");
        }
        return roleRepository.findById(idRole);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRole(Long idRole) {
        if (!roleRepository.existsById(idRole)) {
            throw new ResourceNotFoundException("Role not found");
        }
        roleRepository.deleteById(idRole);
    }
}
