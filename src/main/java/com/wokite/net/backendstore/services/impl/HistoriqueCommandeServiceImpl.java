package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Commande;
import com.wokite.net.backendstore.models.HistoriqueCommande;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.repository.HistoriqueCommandeRepository;
import com.wokite.net.backendstore.services.CommandeService;
import com.wokite.net.backendstore.services.HistoriqueCommandeService;
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
public class HistoriqueCommandeServiceImpl implements HistoriqueCommandeService {

    private final HistoriqueCommandeRepository historiqueCommandeRepository;

    private final UtilisateurService utilisateurService;

    private final CommandeService commandeService;

    @Autowired
    public HistoriqueCommandeServiceImpl(HistoriqueCommandeRepository historiqueCommandeRepository,
                                         UtilisateurService utilisateurService,
                                         CommandeService commandeService) {
        this.historiqueCommandeRepository = historiqueCommandeRepository;
        this.utilisateurService = utilisateurService;
        this.commandeService = commandeService;
    }

    @Override
    public HistoriqueCommande saveHistoriqueCommande(HistoriqueCommande historiqueCommande) {
        return historiqueCommandeRepository.save(historiqueCommande);
    }

    @Override
    public HistoriqueCommande saveHistoriqueCommandeWithConnectedUserAndComID(HistoriqueCommande historiqueCommande,
                                                                              Long userId, Long comId) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(userId);
        Utilisateur utilisateur = optionalUtilisateur.get();

        Optional<Commande> optionalCommande = commandeService.findCommandeById(comId);
        Commande commandeClient = optionalCommande.get();

        historiqueCommande.setUtilisateur(utilisateur);
        historiqueCommande.setCommande(commandeClient);
        historiqueCommande.setCreatedDate(new Date());

        return historiqueCommandeRepository.save(historiqueCommande);
    }

    @Override
    public Optional<HistoriqueCommande> findHistoriqueCommandeById(Long id) {
        if (!historiqueCommandeRepository.existsById(id)) {
            throw new ResourceNotFoundException("HistoriqueCommande that id is" + id + "not found");
        }
        return historiqueCommandeRepository.findById(id);
    }

    @Override
    public List<HistoriqueCommande> findAllHistoriqueCommandes() {
        return historiqueCommandeRepository.findAll();
    }

    @Override
    public List<HistoriqueCommande> findAllHistoriqueCommandesOrderDesc() {
        return historiqueCommandeRepository.findByOrderByIdDesc();
    }

    @Override
    public BigDecimal countNumberOfHistoriqueCommandes() {
        return historiqueCommandeRepository.countNumberOfHistoriqueCommandes();
    }

    @Override
    public void deleteHistoriqueCommande(Long id) {
        if (!historiqueCommandeRepository.existsById(id)) {
            throw new ResourceNotFoundException("HistoriqueCommande that id is" + id + "not found");
        }
        historiqueCommandeRepository.deleteById(id);
    }

}
