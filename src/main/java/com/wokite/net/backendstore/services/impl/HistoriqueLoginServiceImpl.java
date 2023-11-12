package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueLogin;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.repository.HistoriqueLoginRepository;
import com.wokite.net.backendstore.services.HistoriqueLoginService;
import com.wokite.net.backendstore.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HistoriqueLoginServiceImpl implements HistoriqueLoginService {

    private final HistoriqueLoginRepository historiqueLoginRepository;

    private final UtilisateurService utilisateurService;

    public HistoriqueLoginServiceImpl(HistoriqueLoginRepository historiqueLoginRepository,
                                      UtilisateurService utilisateurService) {
        this.historiqueLoginRepository = historiqueLoginRepository;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public HistoriqueLogin saveHistoriqueLogin(HistoriqueLogin historiqueLogin) {
        return historiqueLoginRepository.save(historiqueLogin);
    }

    @Override
    public HistoriqueLogin saveHistoriqueLoginWithConnectedUser(HistoriqueLogin historiqueLogin, Long userId) {

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(userId);
        Utilisateur utilisateur = optionalUtilisateur.get();

        historiqueLogin.setUtilisateur(utilisateur);
        historiqueLogin.setCreatedDate(new Date());

        return historiqueLoginRepository.save(historiqueLogin);
    }

    @Override
    public Optional<HistoriqueLogin> findHistoriqueLoginById(Long id) {
        if (!historiqueLoginRepository.existsById(id)) {
            throw new ResourceNotFoundException("HistoriqueLogin that id is" + id + "not found");
        }
        return historiqueLoginRepository.findById(id);
    }

    @Override
    public List<HistoriqueLogin> findAllHistoriqueLogins() {
        return historiqueLoginRepository.findAll();
    }

    @Override
    public List<HistoriqueLogin> findAllHistoriqueLoginsOrderDesc() {
        return historiqueLoginRepository.findByOrderByIdDesc();
    }

    @Override
    public BigDecimal countNumberOfHistoriqueLogins() {
        return historiqueLoginRepository.countNumberOfHistoriqueLogins();
    }

    @Override
    public void deleteHistoriqueLogin(Long id) {
        if (!historiqueLoginRepository.existsById(id)) {
            throw new ResourceNotFoundException("HistoriqueLogin that id is" + id + "not found");
        }
        HistoriqueLogin historiqueLogin = historiqueLoginRepository.findById(id).get();
        historiqueLogin.setActif(false);
        historiqueLoginRepository.save(historiqueLogin);
    }

}
