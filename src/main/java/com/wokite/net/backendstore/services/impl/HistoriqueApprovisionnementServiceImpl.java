package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Approvisionnement;
import com.wokite.net.backendstore.models.HistoriqueApprovisionnement;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.repository.HistoriqueApprovisionnementRepository;
import com.wokite.net.backendstore.services.ApprovisionnementService;
import com.wokite.net.backendstore.services.HistoriqueApprovisionnementService;
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
public class HistoriqueApprovisionnementServiceImpl implements HistoriqueApprovisionnementService {

    @Autowired private HistoriqueApprovisionnementRepository historiqueApprovisionnementRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ApprovisionnementService approvisionnementService;

    @Override
    public HistoriqueApprovisionnement saveHistoriqueApprovisionnement(HistoriqueApprovisionnement historiqueApprovisionnement) {
        return historiqueApprovisionnementRepository.save(historiqueApprovisionnement);
    }

    @Override
    public HistoriqueApprovisionnement saveHistoriqueApprovisionnementWithConnectedUserAndApproID(HistoriqueApprovisionnement historiqueApprovisionnement, Long userId, Long approId) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(userId);
        Utilisateur utilisateur = optionalUtilisateur.get();

        Optional<Approvisionnement> optionalApprovisionnement = approvisionnementService.findApprovisionnementById(approId);
        Approvisionnement approvisionnement = optionalApprovisionnement.get();

        historiqueApprovisionnement.setUtilisateur(utilisateur);
        historiqueApprovisionnement.setApprovisionnement(approvisionnement);
        historiqueApprovisionnement.setCreatedDate(new Date());

        return historiqueApprovisionnementRepository.save(historiqueApprovisionnement);

    }

    @Override
    public Optional<HistoriqueApprovisionnement> findHistoriqueApprovisionnementById(Long id) {
        if (!historiqueApprovisionnementRepository.existsById(id)) {
            throw new ResourceNotFoundException("HistoriqueApprovisionnement that id is" + id + "not found");
        }
        return historiqueApprovisionnementRepository.findById(id);
    }

    @Override
    public List<HistoriqueApprovisionnement> findAllHistoriqueApprovisionnements() {
        return historiqueApprovisionnementRepository.findAll();
    }

    @Override
    public List<HistoriqueApprovisionnement> findAllHistoriqueApprovisionnementsOrderDesc() {
        return historiqueApprovisionnementRepository.findByOrderByIdDesc();
    }

    @Override
    public BigDecimal countNumberOfHistoriqueApprovisionnements() {
        return historiqueApprovisionnementRepository.countNumberOfHistoriqueApprovisionnements();
    }

    @Override
    public void deleteHistoriqueApprovisionnement(Long id) {
        if (!historiqueApprovisionnementRepository.existsById(id)) {
            throw new ResourceNotFoundException("HistoriqueApprovisionnement is not found");
        }
        historiqueApprovisionnementRepository.deleteById(id);
    }
}
