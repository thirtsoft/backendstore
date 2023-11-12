package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.enums.RoleName;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Role;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.repository.RoleRepository;
import com.wokite.net.backendstore.repository.UtilisateurRepository;
import com.wokite.net.backendstore.services.UtilisateurService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    private final RoleRepository roleRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur updateUtilisateur(Long idUser, Utilisateur utilisateur) {
        return null;
    }

    @Override
    public boolean updateUsernameOfUtilisateur(String username, String newUsername) {
        Optional<Utilisateur> existsUser = this.utilisateurRepository.findByUsername(username);
        Utilisateur user;
        if (existsUser.isPresent()) {
            user = existsUser.get();
            user.setUsername(newUsername);
            this.utilisateurRepository.save(user);
            return true;
        }

        return false;
    }

    @Override
    public boolean updatePasswordofUtilisateur(String username, String oldPass, String newPass) {
        Optional<Utilisateur> existsUser = this.utilisateurRepository.findByUsername(username);
        Utilisateur user;
        if (existsUser.isPresent()) {
            user = existsUser.get();
        }
        return false;
    }

    @Override
    public Utilisateur activatedUser(String isActive, String id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(Long.valueOf(id));
        Utilisateur utilisateur = optionalUtilisateur.get();
        utilisateur.setActive(Boolean.valueOf(isActive));
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Optional<Utilisateur> findUtilisateurById(Long idUser) {
        if (!utilisateurRepository.existsById(idUser)) {
            throw new ResourceNotFoundException("User N ° " + idUser + "not found");
        }

        return utilisateurRepository.findById(idUser);
    }

    @Override
    public Utilisateur findUtilisateurByUsername(String username) {
        return utilisateurRepository.findByUsername(username).get();
    }

    @Override
    public void addRoleToUser(String username, RoleName roleName) {
        Role role = roleRepository.findByName(roleName).get();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username).get();
        utilisateur.getRoles().add(role);
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public List<Utilisateur> findAllUtilisateursOrderDesc() {
        return utilisateurRepository.findByOrderByIdDesc();
    }


    @Override
    public void deleteUtilisateur(Long idUser) {
        if (!utilisateurRepository.existsById(idUser)) {
            throw new ResourceNotFoundException("User N ° " + idUser + "not found");
        }
        Utilisateur utilisateur = utilisateurRepository.findById(idUser).get();
        utilisateur.setActif(false);
        utilisateurRepository.save(utilisateur);
    }
}
