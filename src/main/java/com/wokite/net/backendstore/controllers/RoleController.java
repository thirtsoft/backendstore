package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.RoleApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Role;
import com.wokite.net.backendstore.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class RoleController implements RoleApi {

    @Autowired
    private RoleService roleService;

    @Override
    public ResponseEntity<Role> createRole(Role role) {
        Role roleResult = roleService.saveRole(role);
        return new ResponseEntity<>(roleResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Role> updateRole(Long idRole, Role role) {
        role.setId(idRole);
        Role roleResult = roleService.saveRole(role);
        return new ResponseEntity<>(roleResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Role> getRoleById(Long idRole) throws ResourceNotFoundException {
        Role roleResult = roleService.findRoleById(idRole)
                .orElseThrow(() -> new ResourceNotFoundException("Role " + idRole + "not found"));
        return new ResponseEntity<>(roleResult, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roleList = roleService.findAllRoles();
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteRole(Long idRole) {
        roleService.deleteRole(idRole);
        return ResponseEntity.ok()
                .build();
    }
}
