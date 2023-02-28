package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Role;
import com.wokite.net.backendstore.models.Utilisateur;

public interface AccountService {

    Role saveRole(Role role);

    void addRoleToUtilisateur(String username, String roleName);

    Utilisateur findUtilisateurByUsername(String username);
}
