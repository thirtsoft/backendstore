package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Creance;
import com.wokite.net.backendstore.models.HistoriqueCreance;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.repository.HistoriqueCreanceRepository;
import com.wokite.net.backendstore.services.CreanceService;
import com.wokite.net.backendstore.services.HistoriqueCreanceService;
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
public class HistoriqueCreanceServiceImpl implements HistoriqueCreanceService {

    private final HistoriqueCreanceRepository historiqueCreanceRepository;

    private final UtilisateurService utilisateurService;

    private final CreanceService creanceService;

    @Autowired
    public HistoriqueCreanceServiceImpl(HistoriqueCreanceRepository historiqueCreanceRepository,
                                        UtilisateurService utilisateurService, CreanceService creanceService) {
        this.historiqueCreanceRepository = historiqueCreanceRepository;
        this.utilisateurService = utilisateurService;
        this.creanceService = creanceService;
    }

    @Override
    public HistoriqueCreance saveHistoriqueCreance(HistoriqueCreance historiqueCreance) {
        return historiqueCreanceRepository.save(historiqueCreance);
    }

    @Override
    public HistoriqueCreance saveHistoriqueCommandeWithConnectedUserAndCreanceID(HistoriqueCreance historiqueCreance,
                                                                                 Long userId, Long creanceId) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(userId);
        Utilisateur utilisateur = optionalUtilisateur.get();

        Optional<Creance> optionalCreance = creanceService.findCreanceById(creanceId);
        Creance creance = optionalCreance.get();

        historiqueCreance.setUtilisateur(utilisateur);
        historiqueCreance.setCreance(creance);
        historiqueCreance.setCreatedDate(new Date());

        return historiqueCreanceRepository.save(historiqueCreance);
    }

    @Override
    public Optional<HistoriqueCreance> findHistoriqueCreanceById(Long id) {
        if (!historiqueCreanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("HistoriqueCreance that id is" + id + "not found");
        }
        return historiqueCreanceRepository.findById(id);
    }

    @Override
    public List<HistoriqueCreance> findAllHistoriqueCreances() {
        return historiqueCreanceRepository.findAll();
    }

    @Override
    public List<HistoriqueCreance> findAllHistoriqueCreancesOrderDesc() {
        return historiqueCreanceRepository.findByOrderByIdDesc();
    }

    @Override
    public BigDecimal countNumberOfHistoriqueCreances() {
        return historiqueCreanceRepository.countNumberOfHistoriqueCreances();
    }

    @Override
    public void deleteHistoriqueCreance(Long id) {
        if (!historiqueCreanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("HistoriqueCreance that id is" + id + "not found");
        }
        historiqueCreanceRepository.deleteById(id);
    }

}
