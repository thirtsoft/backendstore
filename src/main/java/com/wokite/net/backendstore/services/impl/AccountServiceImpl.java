package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.models.Role;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.repository.RoleRepository;
import com.wokite.net.backendstore.repository.UtilisateurRepository;
import com.wokite.net.backendstore.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired private UtilisateurRepository utilisateurRepository;

    @Autowired private RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUtilisateur(String username, String roleName) {

    }

    @Override
    public Utilisateur findUtilisateurByUsername(String username) {
        return null;
    }
}
