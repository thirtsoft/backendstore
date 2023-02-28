package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.enums.RoleName;
import com.wokite.net.backendstore.models.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    Utilisateur saveUtilisateur(Utilisateur utilisateur);

    Utilisateur updateUtilisateur(Long idUser, Utilisateur utilisateur);

    boolean updateUsernameOfUtilisateur(String username, String newUsername);

    boolean updatePasswordofUtilisateur(String username, String oldPass, String newPass);

    Utilisateur activatedUser(String isActive, String id);

    Optional<Utilisateur> findUtilisateurById(Long idUser);

    Utilisateur findUtilisateurByUsername(String username);

    void addRoleToUser(String username, RoleName roleName);

    List<Utilisateur> findAllUtilisateurs();

    List<Utilisateur> findAllUtilisateursOrderDesc();

    void deleteUtilisateur(Long idUser);


}
