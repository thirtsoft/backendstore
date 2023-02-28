package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Depense;
import com.wokite.net.backendstore.models.HistoriqueDepense;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.repository.HistoriqueDepenseRepository;
import com.wokite.net.backendstore.services.DepenseService;
import com.wokite.net.backendstore.services.HistoriqueDepenseService;
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
public class HistoriqueDepenseServiceImpl implements HistoriqueDepenseService {

    @Autowired   private HistoriqueDepenseRepository historiqueDepenseRepository;

    @Autowired  private UtilisateurService utilisateurService;

    @Autowired  private DepenseService depenseService;


    @Override
    public HistoriqueDepense saveHistoriqueCharge(HistoriqueDepense historiqueDepense) {
        return historiqueDepenseRepository.save(historiqueDepense);
    }

    @Override
    public HistoriqueDepense saveHistoriqueChargeWithConnectedUserAndChargeID(HistoriqueDepense historiqueDepense, Long userId, Long chargeId) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(userId);
        Utilisateur utilisateur = optionalUtilisateur.get();

        Optional<Depense> optionalCharge = depenseService.findChargeById(chargeId);
        Depense depense = optionalCharge.get();

        historiqueDepense.setUtilisateur(utilisateur);
        historiqueDepense.setDepense(depense);
        historiqueDepense.setCreatedDate(new Date());

        return historiqueDepenseRepository.save(historiqueDepense);
    }

    @Override
    public Optional<HistoriqueDepense> findHistoriqueChargeById(Long id) {
        if (!historiqueDepenseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Historique chqrge " + id + "not found");
        }
        return historiqueDepenseRepository.findById(id);
    }

    @Override
    public List<HistoriqueDepense> findAllHistoriqueCharges() {
        return historiqueDepenseRepository.findAll();
    }

    @Override
    public List<HistoriqueDepense> findAllHistoriqueChargesOrderDesc() {
        return historiqueDepenseRepository.findByOrderByIdDesc();
    }

    @Override
    public BigDecimal countNumberOfHistoriqueCharges() {
        return historiqueDepenseRepository.countNumberOfHistoriqueCharges();
    }

    @Override
    public void deleteHistoriqueCharge(Long id) {
        if (!historiqueDepenseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Historique chqrge " + id + "not found");
        }
        historiqueDepenseRepository.deleteById(id);
    }
}
