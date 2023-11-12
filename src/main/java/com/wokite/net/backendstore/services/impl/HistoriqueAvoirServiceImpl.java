package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Avoir;
import com.wokite.net.backendstore.models.HistoriqueAvoir;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.repository.HistoriqueAvoirRepository;
import com.wokite.net.backendstore.services.AvoirService;
import com.wokite.net.backendstore.services.HistoriqueAvoirService;
import com.wokite.net.backendstore.services.UtilisateurService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HistoriqueAvoirServiceImpl implements HistoriqueAvoirService {

    private final HistoriqueAvoirRepository historiqueAvoirRepository;

    private final UtilisateurService utilisateurService;

    private final AvoirService avoirService;

    public HistoriqueAvoirServiceImpl(HistoriqueAvoirRepository historiqueAvoirRepository,
                                      UtilisateurService utilisateurService,
                                      AvoirService avoirService) {
        this.historiqueAvoirRepository = historiqueAvoirRepository;
        this.utilisateurService = utilisateurService;
        this.avoirService = avoirService;
    }

    @Override
    public HistoriqueAvoir saveHistoriqueAvoir(HistoriqueAvoir historiqueAvoir) {
        return historiqueAvoirRepository.save(historiqueAvoir);
    }

    @Override
    public HistoriqueAvoir saveHistoriqueAvoirWithConnectedUserAndAvoirID(HistoriqueAvoir historiqueAvoir, Long userId, Long avoirId) {

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(userId);
        Utilisateur utilisateur = optionalUtilisateur.get();

        Optional<Avoir> optionalAvoir = avoirService.findAvoirById(avoirId);
        Avoir avoir = optionalAvoir.get();

        historiqueAvoir.setUtilisateur(utilisateur);
        historiqueAvoir.setAvoir(avoir);
        historiqueAvoir.setCreatedDate(new Date());

        return historiqueAvoirRepository.save(historiqueAvoir);
    }

    @Override
    public Optional<HistoriqueAvoir> findHistoriqueAvoirById(Long id) {
        if (!historiqueAvoirRepository.existsById(id)) {
            throw new ResourceNotFoundException("Historique " + id + "not found");
        }
        return historiqueAvoirRepository.findById(id);
    }

    @Override
    public List<HistoriqueAvoir> findAllHistoriqueAvoirs() {
        return historiqueAvoirRepository.findAll();
    }

    @Override
    public List<HistoriqueAvoir> findAllHistoriqueAvoirsOrderDesc() {
        return historiqueAvoirRepository.findByOrderByIdDesc();
    }

    @Override
    public BigDecimal countNumberOfHistoriqueAvoirs() {
        return historiqueAvoirRepository.countNumberOfHistoriqueAvoirs();
    }

    @Override
    public void deleteHistoriqueAvoir(Long id) {
        if (!historiqueAvoirRepository.existsById(id)) {
            throw new ResourceNotFoundException("Historique " + id + "not found");
        }
        HistoriqueAvoir historiqueAvoir = historiqueAvoirRepository
                .findById(id).get();
        historiqueAvoir.setActif(false);
        historiqueAvoirRepository.save(historiqueAvoir);
    }
}
